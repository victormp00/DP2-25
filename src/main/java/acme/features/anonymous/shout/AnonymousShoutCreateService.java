
package acme.features.anonymous.shout;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Maolet;
import acme.entities.shouts.Shout;
import acme.entities.spam.Spam;
import acme.entities.spam.Threshold;
import acme.features.administrator.spam.AdminSpamCreateService;
import acme.features.administrator.spam.AdminSpamRepository;
import acme.features.administrator.threshold.ThresholdRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state ------------------------------------------------------

	@Autowired
	private AnonymousShoutRepository	repository;

	@Autowired
	private AdminSpamRepository			spamRepository;

	@Autowired
	protected AdminSpamCreateService	spamService;

	@Autowired
	protected ThresholdRepository		thresholdRepository;
	// AbstractCreateService<Administrator, Shout> interface --------------------------------------------------------


	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info", "maolet.tiplet", "maolet.budget", "maolet.important");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		final Shout result;
		final Date moment;
		final Maolet maolet;

		maolet = new Maolet();
		moment = new Date(System.currentTimeMillis() - 1);

		result = new Shout();
		result.setMoment(moment);
		result.setMaolet(maolet);

		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final List<Spam> spam = (List<Spam>) this.spamRepository.findSpam();
		final Threshold threshold = this.thresholdRepository.findSpamEntity();
		final boolean censuraAuthor = Threshold.censura(entity.getAuthor(), spam, threshold.getThreshold());
		final boolean censuraText = Threshold.censura(entity.getText(), spam, threshold.getThreshold());
		final Boolean censuraLink = Threshold.censura(entity.getInfo(), spam, threshold.getThreshold());

		if (!errors.hasErrors("author")) {
			errors.state(request, !censuraAuthor, "author", "anonymous.shout.spam.author.crea");
		}
		if (!errors.hasErrors("text")) {
			errors.state(request, !censuraText, "text", "anonymous.shout.spam.text.crea");
		}
		if (!errors.hasErrors("info")) {
			errors.state(request, !censuraLink, "info", "anonymous.shout.spam.url.crea");
		}

		//control check

		if (!errors.hasErrors("maolet.budget")) {
			errors.state(request, entity.getMaolet().getBudget().getAmount() >= 0, "maolet.budget", "anonymous.maolet.budget.error");

			final String currency = entity.getMaolet().getBudget().getCurrency();

			//Only $ and €
			errors.state(request, (currency.equals("EUR") || currency.equals("USD")), "maolet.budget", "anonymous.maolet.budget.currency.error");
		}

		if (!errors.hasErrors("maolet.tiplet")) {
			Boolean res = true;
			final Date currentDate = new Date();
			final Integer day = Integer.valueOf(entity.getMaolet().getTiplet().indexOf(-1,-2));
			final Integer month = Integer.valueOf(entity.getMaolet().getTiplet().indexOf(-3,-4));
			final Integer year = Integer.valueOf(entity.getMaolet().getTiplet().indexOf(-6,-7));

			if (!(day.equals(currentDate.getDate()) && month.equals(currentDate.getMonth() + 1) && year.equals(currentDate.getYear()))) {
				res = false;
			}

			final List<Shout> shouts = this.repository.findMany().stream().collect(Collectors.toList());
			Boolean unique = true;
			for (final Shout s : shouts) {
				if (s.getMaolet() != null && s.getMaolet().getTiplet().equals(entity.getMaolet().getTiplet())) {
					unique = false;
				}
			}

			errors.state(request, res, "maolet.tiplet", "anonymous.maolet.tiplet.error");
			errors.state(request, unique, "maolet.tiplet", "anonymous.maolet.tiplet.unique.error");
		}

	}

	@Override

	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		Date deadline;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		deadline = new Date((long) (System.currentTimeMillis() + 6.048e+8));

		entity.getMaolet().setDeadline(deadline);
		this.repository.save(entity.getMaolet());
		this.repository.save(entity);
	}

}
