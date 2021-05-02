package acme.entities.spam;

import java.util.Arrays;
import java.util.List;

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
	
	public static Boolean censura(final String campo, final List<Spam> spam, final int treshold) {
		Boolean res= false;
		final String[] palabras=campo.split(" ");
		final List<String> palabrasSep = Arrays.asList(palabras);
		int i=0;
		for(final String p : palabrasSep) {
			for(final Spam s: spam) {
				if(s.getSpamEn().equals(s.getSpamEs())) {
					if(p.equals(s.getSpamEs())){
						i++;
					}
				}else {
					if(p.equals(s.getSpamEn())){
						i++;
					}
					if(p.equals(s.getSpamEs())){
						i++;
					}
				}
			}
		}
		if(treshold<((double)i/palabras.length)*100) {
			res=true;
		}
		return res;
	}

}
