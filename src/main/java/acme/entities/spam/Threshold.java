
package acme.entities.spam;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
	private static final long	serialVersionUID	= 1L;
	@OneToMany
	protected List<Spam>		spamWords;

	@Min(0)
	@Max(100)
	@NotNull
	protected Double			threshold;


	public static Boolean censura(final String txt, final List<Spam> spamWords, final Double threshold) {
		final Integer nSpam = Threshold.getSpamInText(txt, spamWords);
		final Integer nTxt = txt.length();
		Boolean res = false;
		if (threshold < (double) nSpam / nTxt * 100.00) {
			res = true;
		}
		return res;
	}

	public static Integer getSpamInText(String txt, final List<Spam> spamWords) {
		final String str = txt.toLowerCase();
		for (final Spam s : spamWords) {
			if (txt.toLowerCase().contains(s.getSpamEs().toLowerCase())) {
				txt = txt.toLowerCase().replaceAll(s.getSpamEs().toLowerCase(), "");
			}
			if (txt.toLowerCase().contains(s.getSpamEn().toLowerCase())) {
				txt = txt.toLowerCase().replaceAll(s.getSpamEn().toLowerCase(), "");
			}

		}
		return str.length() - txt.toLowerCase().length();
	}

}
