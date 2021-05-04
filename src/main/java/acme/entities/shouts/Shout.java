/*
 * Shout.java
 *
 * Copyright (C) 2020-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shout extends DomainEntity {

	// Serialisation identifier ---------------------------------
	
	protected static final long serialVersionUID = 1L;
	
	// Attributes ----------------------------------------------
	
	@Temporal (TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date moment;
	
	@NotBlank
	@Size(min=5, max = 25)
	@NotNull
	protected String author;
	
	@NotBlank
	@Size(max = 100)
	protected String text;
	
	@URL
	protected String info;
	
	// Derived attributes -------------------------------------
	
	
	// Relationships ------------------------------------------
		
}
