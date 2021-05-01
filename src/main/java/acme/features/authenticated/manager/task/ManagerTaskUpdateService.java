package acme.features.authenticated.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {
	

	@Autowired
	protected ManagerTaskRepository repository;
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		Task result;
		Principal principal;
		int id;
		Manager manager;
		boolean puedeactualizar;
		
		principal = request.getPrincipal();
		id =  request.getModel().getInteger("id");
		result= this.repository.findOneTaskById(id);
		manager= result.getManager();
		puedeactualizar=manager.getUserAccount().getId() == principal.getAccountId();
		return puedeactualizar;

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
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		Task result;
		Principal principal;
		int id;
		principal = request.getPrincipal();
		id=request.getModel().getInteger("id");
		result= this.repository.findOneTaskById(id);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

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
