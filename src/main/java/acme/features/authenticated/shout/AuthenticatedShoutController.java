package acme.features.authenticated.shout;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.shouts.Shout;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/shout/")
public class AuthenticatedShoutController extends AbstractController<Authenticated,Shout>{

	// Internal state ------------------------------------------------------
	@Autowired
	private AuthenticatedShoutListService listService;
	
	@Autowired
	private AuthenticatedShoutCreateService createService;
	
	@Autowired
	protected AuthenticatedShoutShowService showService;
	
	// Constructors --------------------------------------------------------
	
	@PostConstruct
	private void inicialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
