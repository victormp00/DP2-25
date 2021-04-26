package acme.features.authenticated.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.task.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/task/")
public class AuthenticatedListTasksController extends AbstractController<Authenticated, Task>{
	
	@Autowired
	protected AuthenticatedTaskListService listService;
	
	@Autowired
	protected AuthenticatedTaskShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		
	}
	

}
