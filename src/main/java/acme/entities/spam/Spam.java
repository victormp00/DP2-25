package acme.entities.spam;

import javax.persistence.Entity;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Spam extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected String spamEn; 
	
	protected String spamEs; 
}
