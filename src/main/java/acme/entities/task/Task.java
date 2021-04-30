
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
	public Double				executionTime;

	public Double getExecutionTime() {
		final Task task = new Task();
		final Date start = task.getCreation();
		final Date end = task.getFinish();
		final Long ms = end.getTime() - start.getTime();
		final Integer n = ms.intValue();
		final Double d = n.doubleValue();
		return (d / 36*(Math.pow(10, 7)));
	}
}
