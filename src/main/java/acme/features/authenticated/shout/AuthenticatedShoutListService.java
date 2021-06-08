package acme.features.authenticated.shout;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Shout;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedShoutListService implements AbstractListService<Authenticated, Shout> {
	
	// Internal state ------------------------------------------------------
	
	@Autowired
	private AuthenticatedShoutRepository shoutRepository;
		
		
	// AbstractListService<Administrator, Shout> interface --------------------------------------------------------
		
		
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request !=null;
		return true;
	}
	
	
	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request !=null;
		assert entity !=null;
		assert model !=null;

		request.unbind(entity,model,"author","text","moment","xxx.xxxdate","xxx.xxxmoment","xxx.xxxamount.currency","xxx.xxxamount.amount","xxx.xxxboolean");
	}
	
	@Override
	public Collection<Shout> findMany (final Request<Shout> request){
		assert request != null;
		
		Collection<Shout> result;
		
		result= this.shoutRepository.findByLastMonth(
			Date.valueOf(LocalDate.now().plusDays(1)),
			Date.valueOf(LocalDate.now().plusMonths(-1)));
		
		
		
		return result;
	}
		
		
}
