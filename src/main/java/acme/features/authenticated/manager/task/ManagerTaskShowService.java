package acme.features.authenticated.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager, Task>{
	@Autowired
	protected ManagerTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null; 
		Task result;
		Principal principal;
		int id;
		Manager manager;
		boolean puedeVer;
		
		principal = request.getPrincipal();
		id =  request.getModel().getInteger("id");
		result= this.repository.findOneTaskById(id);
		manager= result.getManager();
		puedeVer=manager.getUserAccount().getId() == principal.getAccountId();
		return puedeVer;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		//hay que poner todos los atributos de la clase o solo los que se van a usar?
		request.unbind(entity, model, "title", "creation", "finish", "workload", "description", "link", "publico");
		
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request!=null;
		
		Task result;
		int id;
		
		id=request.getModel().getInteger("id");
		result= this.repository.findOneTaskById(id);
		return result;
	}


	
}
