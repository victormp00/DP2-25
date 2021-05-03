package acme.entities.roles;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import acme.entities.task.Task;
import acme.framework.entities.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Manager extends UserRole {

	// Serialisation identifier -----------------------------------------------
	
	@OneToMany(mappedBy = "manager")
	private  Collection<@Valid Task> tasks;
	
	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	protected String			company;

	@NotBlank
	protected String			sector;

	// Derived attributes -----------------------------------------------------

	// Relationships ----------------------------------------------------------

}
