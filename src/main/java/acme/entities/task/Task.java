
package acme.entities.task;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

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
	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Length(min = 1, max = 60)
	private String				title;

	//sino funciona, probar con localdatetieme
	@Temporal(TemporalType.TIMESTAMP)
	private Date				creation;

	@Temporal(TemporalType.TIMESTAMP)
	private Date				finish;

	@Digits(fraction = 2, integer = 2)
	private Double				workload;

	@NotBlank
	@Length(min = 1, max = 500)
	private String				description;

	@URL
	private String				link;

	//misc
	private Boolean				publico;

	private Boolean				finished;

	// Derived attributes -----------------------------------------------------
	private Double			executionTime;

	public void setExecutionTime(final Double executionTime) {
		System.out.println("HOLI HE ENTRADO");
		System.out.println("FINAL: "+this.finish.getTime());
		System.out.println("CREATION: "+this.creation.getTime());
		final Long ms = this.finish.getTime() - this.creation.getTime();
		this.executionTime = (ms.doubleValue() / (36 * (Math.pow(10, 5))));
		System.out.println(this.executionTime);
	}
}
