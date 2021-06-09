package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class XXX extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy = "xxx")
	protected Shout shout;
	
	@NotBlank
	//@Pattern(regexp = "([12]\\d{3}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01]))", message = "default.error.conversion")
	//^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$
	@Pattern(regexp = "[0-9]{2}/[0-9]{2}/[0-9]{4}/[a-zA-Z0-9.>%<+]*")
	@Column(unique = true)
	protected String xxxdate;
	
	@Temporal (TemporalType.TIMESTAMP)
	protected Date xxxmoment;
	
	@Valid
	@NotNull
	protected Money xxxamount;
	
	protected Boolean xxxboolean;
		
}
