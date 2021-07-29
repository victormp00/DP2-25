
package acme.features.authenticated.manager.task;

import java.util.Date;
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
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreate implements AbstractCreateService<Manager, Task> {

	@Autowired
	protected ManagerTaskRepository	repository;

	@Autowired
	protected AdminSpamRepository	spamRepository;

	@Autowired
	protected ThresholdRepository	thresholdRepository;


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

		request.unbind(entity, model, "title", "creation", "finish", "workload", "description", "link", "publico");
		model.setAttribute("taskId", entity.getId());
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;

		Task result;
		Manager manager;
		Date creation;

		manager = this.repository.findManyManagerById(request.getPrincipal().getActiveRoleId());
		creation = new Date();

		result = new Task();
		result.setManager(manager);
		result.setCreation(creation);
		result.setPublico(true);
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
		final List<Spam> spam = (List<Spam>) this.spamRepository.findSpam();
		final Threshold threshold = this.thresholdRepository.findSpamEntity();
		final Boolean censuraDescr = Threshold.censura(entity.getDescription(), spam, threshold.getThreshold());
		final Boolean censuratitle = Threshold.censura(entity.getTitle(), spam, threshold.getThreshold());
		final Boolean censuraLink = Threshold.censura(entity.getLink(), spam, threshold.getThreshold());

		if ((entity.getCreation() != null) && (entity.getFinish() != null)) {
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
				errors.state(request, Boolean.TRUE.equals(entity.creationBeforeNow()), "creation", "manager.task.date2");

			}
			if (!errors.hasErrors("finish")) {
				errors.state(request, Boolean.TRUE.equals(entity.datefit()), "finish", "manager.task.date");

			}

			if (!errors.hasErrors("workload")) {
				errors.state(request, Boolean.TRUE.equals(entity.isFit()), "workload", "manager.task.workload");
				errors.state(request, Boolean.TRUE.equals(Task.workloadOK(entity.getWorkload())), "workload", "manager.task.workload.decimals");
			}
		}
	}
	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		Date creation;

		creation = new Date();

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
