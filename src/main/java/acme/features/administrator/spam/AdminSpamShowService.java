package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdminSpamShowService implements AbstractShowService<Administrator, Spam>{


	@Autowired
	protected AdminSpamRepository repository;

	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null; 
		
		return true;
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		request.unbind(entity, model, "spamEn", "spamEs", "threshold");
		
		
	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request!=null;
		
		Spam result;
		int id;
		
		id=request.getModel().getInteger("id");
		result= this.repository.findOneSpamWordById(id);
		return result;
	}
	

}
