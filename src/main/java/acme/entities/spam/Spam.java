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
//	
//	public Boolean censura(final String campo) {
//		Boolean res= false;
//		final String[] palabras=campo.split(" ");
//		int cont=0;
//		int i=0;
//		while(i < palabras.length) {
//			if(this.spam.contains(palabras[i])) {
//				cont++;
//			}	
//			i++;
//		}
//		if(this.treshold<(cont/palabras.length)*100) {
//			res=true;
//		}
//		return res;
//	}

}
