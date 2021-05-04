package acme.features.anonymous.shout;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.shouts.Shout;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/shout/")
public class AnonymousShoutController extends AbstractController<Anonymous,Shout>{

	// Internal state ------------------------------------------------------
	@Autowired
	private AnonymousShoutListService listService;
	
	@Autowired
	private AnonymousShoutCreateService createService;
	
	// Constructors --------------------------------------------------------
	
	@PostConstruct
	private void inicialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}
}
