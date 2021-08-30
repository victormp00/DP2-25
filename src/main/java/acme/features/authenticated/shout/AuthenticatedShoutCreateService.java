package acme.features.authenticated.shout;

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

		request.unbind(entity,model,"author","text","info","maolet.tiplet", "maolet.budget", "maolet.important");
	}
	
	
	@Override
	public Shout instantiate (final Request<Shout> request) {
	assert request !=null;
		
		final Shout result;
		final Date moment;
		final Maolet maolet;
		
		maolet= new Maolet();
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Shout();		
		result.setMoment(moment);	
		result.setMaolet(maolet);
		
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
        if(!errors.hasErrors("maolet.budget")) {
			errors.state(request, entity.getMaolet().getBudget().getAmount()>=0, "maolet.budget", "authenticated.maolet.budget.error");
		
			final String currency = entity.getMaolet().getBudget().getCurrency();
			
			//Only $ and €
			errors.state(request, (currency.equals("EUR") || currency.equals("USD")), "maolet.budget", "authenticated.maolet.budget.currency.error");	
		}
        if(!errors.hasErrors("maolet.deadline")){
			Boolean res= true;
			final Date currentDate= new Date();
			final Integer day = Integer.valueOf(entity.getMaolet().getTiplet().indexOf(-2,-3));
			final Integer month = Integer.valueOf(entity.getMaolet().getTiplet().indexOf(-4,-5));
			final Integer year = Integer.valueOf(entity.getMaolet().getTiplet().indexOf(-7,-8));
			
			if(!(day.equals(currentDate.getDate()) && month.equals(currentDate.getMonth()+1) && year.equals(currentDate.getYear()+1900))) {
				res = false;
			}
			final List<Shout> shouts = this.shoutRepository.findMany().stream().collect(Collectors.toList());
			Boolean unique= true;
			for(final Shout s: shouts) {
				if(s.getMaolet()!=null&& s.getMaolet().getDeadline().equals(entity.getMaolet().getDeadline())) {
					unique=false;
				}
			}
			
			errors.state(request, res,  "maolet.tiplet", "anonymous.maolet.tiplet.error");
			errors.state(request, unique,  "maolet.tiplet", "anonymous.maolet.tiplet.unique.error");
		}

	}
	
	@Override
	
	public void create(final Request<Shout> request, final Shout entity) {
		assert request !=null;
		assert entity !=null;
		
		Date moment;
		Date deadline;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		deadline = new Date((long) (System.currentTimeMillis() + 6.048e+8));
		
		entity.setMoment(moment);
		entity.getMaolet().setDeadline(deadline);
		this.shoutRepository.save(entity.getMaolet());
		this.shoutRepository.save(entity);
	}
		
		
}
