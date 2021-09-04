
package acme.entities.task;

import java.time.Duration;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Manager;
import acme.framework.entities.DomainEntity;
import lombok.AccessLevel;
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

	@ManyToOne
	protected Manager			manager;

	@NotBlank
	@Length(min = 5, max = 80)
	protected String			title;

	//sino funciona, probar con localdatetieme
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				creation;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date				finish;

	@NotNull
	@Min(0)
	@Max(100)
	@Digits(fraction = 2, integer = 5)
	protected Double			workload;

	@NotBlank
	@Length(min = 1, max = 500)
	@Column(length = 500)
	protected String			description;

	@URL
	protected String			link;

	protected Boolean			publico;

	protected Boolean			finished;

	@Getter(AccessLevel.NONE)
	private Double				executionTime;


	public Double getExecutionTime() {
		return ((this.finish.getTime() - this.creation.getTime()) / (36 * (Math.pow(10, 5))));
	}

	public Boolean isFit() {
		boolean resultado = true;
		final Duration duration = Duration.between(this.creation.toInstant(), this.finish.toInstant());
		final long diff = Math.abs(duration.toHours());
		final long diff1 = Math.abs(duration.toMinutes());
		final Double d1 = Double.valueOf(diff);
		final Double d2 = Double.valueOf(diff1);
		final double numerodeminutosquesobrandelashoras = (d2 % 60) / 100;
		final double tiempoEnMedio = d1 + numerodeminutosquesobrandelashoras;
		if (this.workload > tiempoEnMedio) {
			resultado = false;
		}
		return resultado;
	}

	public static Boolean workloadOK(final Double workload) {
		final double number = workload;
		final int iPart = (int) number;
		final Integer fPart = (int) Math.round((number - iPart) * 100);
		boolean res = true;
		if (fPart > 59) {
			res = false;
		}
		return res;

	}

	public Boolean datefit() {
		boolean resultado = true;
		if (this.creation.after(this.finish) || this.creation.equals(this.finish)) {
			resultado = false;
		}
		return resultado;

	}

	public Boolean creationBeforeNow() {
		boolean resultado = true;
		final Date d = new Date();
		if (this.creation.after(d)) {
			resultado = false;
		}
		return resultado;

	}
}