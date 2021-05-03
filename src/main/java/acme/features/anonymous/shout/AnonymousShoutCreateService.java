package acme.features.anonymous.shout;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.entities.spam.Spam;
import acme.features.administrator.spam.AdminSpamCreateService;
import acme.features.administrator.spam.AdminSpamRepository;
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
	protected AdminSpamCreateService	spamService;
		
		
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
		result.setAuthor("Jhon Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");
		
		return result;
	}
	
	@Override
	public void validate (final Request<Shout> request,final Shout entity, final Errors errors) {
		assert request !=null;
		assert entity !=null;
		assert errors != null;
		
		int textLength;
		textLength = request.getModel().getString("text").length();
		
		int authorLength;
		authorLength = request.getModel().getString("author").length();
		
		final List<Spam> spam= (List<Spam>) this.spamRepository.findSpam();
		final Boolean censuraAuthor = this.spamService.censura(entity.getAuthor(), spam);
		final Boolean censuraText = this.spamService.censura(entity.getText(), spam);
		final Boolean censuraInfo = this.spamService.censura(entity.getInfo(), spam);
		if(Boolean.TRUE.equals(censuraAuthor)  || authorLength <= 5 || authorLength >= 25) {
			errors.add("author", "this is spam or wrong length");
		}
		if(Boolean.TRUE.equals(censuraText) || textLength >= 100) {
			errors.add("text", "this text is spam or wrong length");
		}
	}
		
	@Override
	
	public void create(final Request<Shout> request, final Shout entity) {
		assert request !=null;
		assert entity !=null;
		
		Date moment;
		
		moment = new Date(System.currentTimeMillis() -1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}
		
		
}