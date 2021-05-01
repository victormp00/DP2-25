package acme.entities.task;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Task extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	protected Manager manager;
	
	@NotBlank
	@Length(min=1,max=60)
	protected String title;
	
	//sino funciona, probar con localdatetieme
	@Temporal(TemporalType.TIMESTAMP)
	protected Date creation;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date finish;
	
	@Digits(fraction = 2, integer = 2)
	protected Double workload;
	
	@NotBlank
	@Length(min=1,max=500)
	protected String description;

	@URL
	protected String link;
	
	protected Boolean publico; 
	
	protected Boolean finished;
	
	public Double getWorkloadDerived() {
		Double dif = (double)this.finish.getTime()-(double) this.creation.getTime();
		dif=dif / (1000.00 * 60.00 * 60.00);
		final String str=String.format("%.2f", dif);
		final String e []=str.split(",");
		final Integer decimal=Integer.valueOf(e[1]);
		final Integer decRes=decimal*60/100;		
		final String numFin = e[0]+"."+decRes;
		return 	Double.valueOf(numFin);
	}
}
