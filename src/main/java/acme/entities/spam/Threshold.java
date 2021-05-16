package acme.entities.spam;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Threshold extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany
	protected List<Spam> spamWords;
	
	protected Double threshold;
	
	public static Boolean censura(final String txt,final List<Spam> spamWords,final Double threshold) {
		final Integer nSpam= Threshold.getSpamInText(txt,spamWords);
		final Integer nTxt=txt.length();
		Boolean res= false;
		if(threshold < (double)nSpam/nTxt*100.00) {
			res=true;
		}
		return res;
	}
	
	public static Integer getSpamInText(String txt,final List<Spam> spamWords) {
		final String str= txt;
		for(final Spam s : spamWords) {
			
			if(txt.contains(s.getSpamEs())) {
				txt=txt.replaceAll(s.getSpamEs(), "");
			}
			if(txt.contains(s.getSpamEn())) {
				txt=txt.replaceAll(s.getSpamEn(), "");
			}

		}
		return str.length()-txt.length();
	}
	
	

//	public static Boolean censura( final String campo, final List<Spam> spam, final Double threshold) {
//		final Integer ispam=Threshold.censuraAux(campo,spam);
//		Boolean res= false;
//		if(threshold<=((double)ispam/campo.length())*100.00) {
//			res=true;
//		}
//		return res;
//	}
//	
//	
//	private static Integer censuraAux(final String campo, final List<Spam> spam) {
//		
//		
//		int cont=0;
//		int contFin=0;
//		
//		for(final Spam s : spam) {
//			int contEs=0;
//			int contEn=0;
//			final String en= s.getSpamEn();
//			final String es= s.getSpamEs();
//			if(en.equals(es)) {	
//				cont=Threshold.contador(campo, en, cont);
//				}
//			else {
//				if(campo.contains(es)) {
//					contEs=contEs+Threshold.contador(campo, es, 0);
//				}
//				if(campo.contains(en)) {					
//					contEn=contEn+Threshold.contador(campo, en, 0);	
//				}
//				if(en.contains(es)) {
//					contEn=contEn-contEs;
//				
//				}
//				if(es.contains(en)) {
//					
//					contEs=contEs-contEn;
//					if(contEs<0) {
//						contEs=0;
//					}
//				}
//				contFin=contFin+contEs+contEn;
//			}
//		}
//		
//		return cont+contFin+1;
//		
//	}
//	
//	private static Integer contador(String campo,final String spamWord ,final int i) {
//		final Integer index=campo.indexOf(spamWord);
//		if(index!=-1) {	
//			campo=campo.substring(index+spamWord.length());
//			return Threshold.contador(campo, spamWord, i+spamWord.length());
//		}
//		return i;
//		
//	}
		
}