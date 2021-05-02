package acme.features.authenticated.manager.task;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;
@Service
public class ManagerTaskCreate implements AbstractCreateService<Manager,Task> {
	@Autowired
	protected ManagerTaskRepository repository;

	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"title", "creation", "finish", "workload", "description", "link", "publico");
		model.setAttribute("taskId", entity.getId());
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		
		
		Task result;
		Manager manager;
		Date creation;
		String username;
		
		username= request.getPrincipal().getUsername();
		manager = this.repository.findManager(username);
		creation= new Date();
		
		
		result = new Task();
		result.setManager(manager);
		result.setCreation(creation);
		result.setPublico(true);
		result.setDescription("Escribe una descripcion ");
		result.setTitle("Escribe un titulo");
		result.setFinish(null);
		result.setWorkload(null);
		result.setFinished(false);
		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if(entity.getFinish() !=null && entity.getCreation()!=null ) {
			if(Boolean.FALSE.equals(entity.datefit())) {
				errors.add("creation", "creation is after finish");
			}
			}
		if(entity.getWorkload() !=null && entity.getCreation()!=null ) {
			if(Boolean.FALSE.equals(entity.isFit())) {
				errors.add("workload", "workload does not fit");
			}
			}
		
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
		Date creation;
		
		creation= new Date();
		
		entity.setCreation(creation);
		this.repository.save(entity);
	}
	@Override
	public void onSuccess(final Request<Task> request, final Response<Task> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}


}
