package acme.features.anonymous.shout;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.entities.shouts.XXX;
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
	private AnonymousShoutRepository repository;
		
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

		request.unbind(entity,model,"author","text","info","xxx.xxxdate","xxx.xxxmoment","xxx.xxxamount","xxx.xxxboolean");
	}
	
	
	@Override
	public Shout instantiate (final Request<Shout> request) {
		assert request !=null;
		
		final Shout result;
		final Date moment;
		final XXX xxx;
		
		xxx= new XXX();
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Shout();		
		result.setMoment(moment);	
		result.setXxx(xxx);
		
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
			errors.state(request,!censuraAuthor, "author", "anonymous.shout.spam.author.crea");
		}
		if(!errors.hasErrors("text")) {
            errors.state(request,!censuraText, "text", "anonymous.shout.spam.text.crea");
        }
        if (!errors.hasErrors("info")) {
            errors.state(request,!censuraLink, "info", "anonymous.shout.spam.url.crea");
        }
		
		//control check
        
        if (entity.getXxx().getXxxmoment()!=null&&!errors.hasErrors("xxx.xxxmoment")) {
			final Date xxxmoment=entity.getXxx().getXxxmoment();
			final Date actual= new Date();
			actual.setDate(actual.getDate()+7);
			errors.state(request, actual.before(xxxmoment), "xxx.xxxmoment", "error");
			
		}
		
		if(!errors.hasErrors("xxx.xxxamount")) {
			errors.state(request, entity.getXxx().getXxxamount().getAmount()>=0, "xxx.xxxamount", "anonymous.xxx.xxxamount.error");
		
			final String currency = entity.getXxx().getXxxamount().getCurrency();
			
			//Only $ and € and BGP
			errors.state(request, (currency.equals("EUR") || currency.equals("USD") || currency.equals("BGP")), "xxx.xxxamount", "anonymous.xxx.xxxamount.currency.error");	
		}
		
		if(!errors.hasErrors("xxx.xxxdate")){
			Boolean res= true;
			final Date currentDate= new Date();
			final String[] e=entity.getXxx().getXxxdate().split("/");
			final Integer day= Integer.valueOf(e[0]);
			final Integer month= Integer.valueOf(e[1]);
			final Integer year= Integer.valueOf(e[2]);
			
			if(!(day.equals(currentDate.getDate()) && month.equals(currentDate.getMonth()+1) && year.equals(currentDate.getYear()+1900))) {
				res = false;
			}
			
			final List<Shout> shouts = this.repository.findMany().stream().collect(Collectors.toList());
			Boolean unique= true;
			for(final Shout s: shouts) {
				if(s.getXxx()!=null&& s.getXxx().getXxxdate().equals(entity.getXxx().getXxxdate())) {
					unique=false;
				}
			}
			
			errors.state(request, res,  "xxx.xxxdate", "anonymous.xxx.xxxdate.error");
			errors.state(request, unique,  "xxx.xxxdate", "anonymous.xxx.xxxdate.unique.error");
		}
		
	}
	
	@Override
	
	public void create(final Request<Shout> request, final Shout entity) {
		assert request !=null;
		assert entity !=null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() -1);
		entity.setMoment(moment);
		
		this.repository.save(entity.getXxx());
		this.repository.save(entity);
	}
		
		
}
