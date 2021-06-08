package acme.features.authenticated.manager.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.spam.Spam;
import acme.entities.spam.Threshold;
import acme.entities.task.Task;
import acme.features.administrator.spam.AdminSpamRepository;
import acme.features.administrator.threshold.ThresholdRepository;
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
	@Autowired
	private AdminSpamRepository spamRepository;
	@Autowired
	private ThresholdRepository thresholdRepository;

	
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

		request.unbind(entity, model,"title", "creation", "finish", "workload", "description", "link", "publico", "finished");
		
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;
		Task result;
		int id;
		id=request.getModel().getInteger("id");
		result= this.repository.findOneTaskById(id);

		return result;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final List<Spam> spam = (List<Spam>) this.spamRepository.findSpam();
		final Threshold threshold=this.thresholdRepository.findSpamEntity();
		final Boolean censuraDescr = Threshold.censura(entity.getDescription(), spam, threshold.getThreshold());
		final Boolean censuratitle = Threshold.censura(entity.getTitle(), spam, threshold.getThreshold());
		final Boolean censuraLink = Threshold.censura(entity.getLink(), spam, threshold.getThreshold());
		
		if (!errors.hasErrors("title")) {
			errors.state(request, !censuratitle, "title", "manager.task.spam.title");	
		}
		if (!errors.hasErrors("description")) {
			errors.state(request, !censuraDescr, "description", "manager.task.spam.description");
			
		}
		if (!errors.hasErrors("link")) {
			errors.state(request, !censuraLink, "link", "manager.task.spam.url");
			
		}
		if (!errors.hasErrors("creation")) {
			errors.state(request,Boolean.TRUE.equals(entity.datefit()), "creation", "manager.task.date");
			
		}
		if (!errors.hasErrors("finish")) {
			errors.state(request,Boolean.TRUE.equals(entity.datefit()), "finish", "manager.task.date");
			
		}
		
		if (!errors.hasErrors("workload")) {
			errors.state(request,Boolean.TRUE.equals(entity.isFit()) , "workload", "manager.task.workload");
			errors.state(request,Boolean.TRUE.equals(Task.workloadOK(entity.getWorkload())), "workload", "manager.task.workload.decimals");
		}
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
