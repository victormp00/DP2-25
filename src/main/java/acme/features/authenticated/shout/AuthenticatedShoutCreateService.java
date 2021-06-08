package acme.features.authenticated.shout;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.entities.spam.Spam;
import acme.entities.spam.Threshold;
import acme.features.administrator.spam.AdminSpamCreateService;
import acme.features.administrator.spam.AdminSpamRepository;
import acme.features.administrator.threshold.ThresholdRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;


@Service
public class AuthenticatedShoutCreateService implements AbstractCreateService<Authenticated, Shout> {
	
	// Internal state ------------------------------------------------------
	
	@Autowired
	private AuthenticatedShoutRepository shoutRepository;
		
	@Autowired
	private AdminSpamRepository spamRepository;
	
	@Autowired
	protected AdminSpamCreateService spamService;
	
	@Autowired
	protected ThresholdRepository	thresholdRepository;
	// AbstractCreateService<Administrator, Shout> interface --------------------------------------------------------
		
		
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request !=null;
		
		return true;
	}
	
	
	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request !=null;
		assert entity !=null;
		assert errors !=null;

		request.bind(entity,errors);
	}
	
	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request !=null;
		assert entity !=null;
		assert model !=null;

		request.unbind(entity,model,"author","text","info");
	}
	
	
	@Override
	public Shout instantiate (final Request<Shout> request) {
		assert request !=null;
		
		final Shout result;
		final Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Shout();
		result.setMoment(moment);
		
		return result;
	}
	
	@Override
	public void validate (final Request<Shout> request,final Shout entity, final Errors errors) {
		assert request !=null;
		assert entity !=null;
		assert errors != null;
		
		final List<Spam> spam= (List<Spam>) this.spamRepository.findSpam();
		final Threshold threshold=this.thresholdRepository.findSpamEntity();
		final boolean censuraAuthor = Threshold.censura(entity.getAuthor(), spam, threshold.getThreshold());
		final boolean censuraText = Threshold.censura(entity.getText(), spam, threshold.getThreshold());
		final Boolean censuraLink = Threshold.censura(entity.getInfo(), spam, threshold.getThreshold());
		
		if(!errors.hasErrors("author")) {
			errors.state(request,!censuraAuthor, "author", "authenticated.shout.spam.author.crea");
		}
		if(!errors.hasErrors("text")) {
			errors.state(request,!censuraText, "text", "authenticated.shout.spam.text.crea");
		}
		if (!errors.hasErrors("info")) {
			errors.state(request,!censuraLink, "info", "authenticated.shout.spam.url.crea");
		}

	}
	
	@Override
	
	public void create(final Request<Shout> request, final Shout entity) {
		assert request !=null;
		assert entity !=null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() -1);
		entity.setMoment(moment);
		this.shoutRepository.save(entity);
	}
		
		
}
