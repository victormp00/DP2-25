package acme.entities.task;

import java.time.Duration;
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
	
	public Boolean isFit() {
		final Duration duration= Duration.between(this.creation.toInstant(), this.finish.toInstant()); 
		final long diff = Math.abs(duration.toHours());
		final long diff1 = Math.abs(duration.toMinutes());
		final Double d1=Double.valueOf(diff);
		final Double d2=Double.valueOf(diff1);
		return true;
	}
}
