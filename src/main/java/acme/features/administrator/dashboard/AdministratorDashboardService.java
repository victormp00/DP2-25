
package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, //
			"publicTasks", "privateTasks", "ongoingTasks", "finishedTasks", "averageExecTime", //
			"deviationExecTime", "maxExecTime", "minExecTime", "averageWorkload", "deviationWorkload",//
			"maxWorkload", "minWorkload");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		Dashboard result;
		Integer publicTasks;
		Integer privateTasks;
		Integer ongoingTasks;
		Integer finishedTasks;
		Double averageExecTime;
		Double deviationExecTime;
		Double maxExecTime;
		Double minExecTime;
		Double averageWorkload;
		Double deviationWorkload;
		Double maxWorkload;
		Double minWorkload;
		List<Task> tasks;

		publicTasks = this.repository.publicTasks();
		privateTasks = this.repository.privateTasks();
		ongoingTasks = this.repository.ongoingTasks();
		finishedTasks = this.repository.finishedTasks();
		averageExecTime = this.repository.averageExecTime();
		deviationExecTime = this.repository.deviationExecTime();
		maxExecTime = this.repository.maxExecTime();
		minExecTime = this.repository.minExecTime();
		averageWorkload = this.repository.averageWorkload();
		deviationWorkload = this.repository.deviationWorkload();
		maxWorkload = this.repository.maxWorkload();
		minWorkload = this.repository.minWorkload();
		tasks = this.repository.findAllTasks();
		for (final Task t : tasks) {
			t.setExecutionTime(t.getExecutionTime());
		}

		result = new Dashboard();
		result.setPublicTasks(publicTasks);
		result.setPrivateTasks(privateTasks);
		result.setOngoingTasks(ongoingTasks);
		result.setFinishedTasks(finishedTasks);
		result.setAverageExecTime(averageExecTime);
		result.setDeviationExecTime(deviationExecTime);
		result.setMaxExecTime(maxExecTime);
		result.setMinExecTime(minExecTime);
		result.setAverageWorkload(averageWorkload);
		result.setDeviationWorkload(deviationWorkload);
		result.setMaxWorkload(maxWorkload);
		result.setMinWorkload(minWorkload);
		return result;
	}

}