package acme.features.administrator.spam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.spam.Spam;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/spam/")
public class AdminSpamController  extends AbstractController<Administrator, Spam>{
	@Autowired
	protected AdminSpamListService listService;
	
	@Autowired
	protected AdminSpamShowService showService;
	
	@Autowired
	protected AdminSpamCreateService createService;
		
	@Autowired
	protected AdminSpamUpdateService updateService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);	
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		
	}
	
}
