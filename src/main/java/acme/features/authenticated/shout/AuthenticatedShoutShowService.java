package acme.features.authenticated.shout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedShoutShowService implements AbstractShowService<Authenticated, Shout> {

	@Autowired
	protected AuthenticatedShoutRepository repository;

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null; 
		
		return true;
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		request.unbind(entity, model, "author","text","info","maolet.tiplet","maolet.budget","maolet.important");
		
		
	}

	@Override
	public Shout findOne(final Request<Shout> request) {
		assert request!=null;
		
		Shout result;
		int id;
		
		id=request.getModel().getInteger("id");
		result= this.repository.findOneShoutById(id);
		return result;
	}
	
	
}
