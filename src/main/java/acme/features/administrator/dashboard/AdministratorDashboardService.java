
package acme.features.administrator.dashboard;

import java.util.Comparator;
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
		final Double averageExecTime;
		final Double deviationExecTime;
		final Double maxExecTime;
		final Double minExecTime;
		Double averageWorkload;
		Double deviationWorkload;
		Double maxWorkload;
		Double minWorkload;
		List<Task> tasks;
		tasks = this.repository.findAllTasks();
		
		if(!tasks.isEmpty()) {
			
		
		
		publicTasks = this.repository.publicTasks();
		privateTasks = this.repository.privateTasks();
		ongoingTasks = this.repository.ongoingTasks();
		finishedTasks = this.repository.finishedTasks();
		averageWorkload = this.repository.averageWorkload();
		deviationWorkload = this.repository.deviationWorkload();
		maxWorkload = this.repository.maxWorkload();
		minWorkload = this.repository.minWorkload();
		
		final Comparator<Task> cmp = Comparator.comparing(Task::getExecutionTime);
		maxExecTime = tasks.stream().max(cmp).get().getExecutionTime();
		minExecTime = tasks.stream().min(cmp).get().getExecutionTime();
		averageExecTime = tasks.stream().mapToDouble(Task::getExecutionTime).average().getAsDouble();
		deviationExecTime = Math.sqrt(tasks.stream().mapToDouble(Task::getExecutionTime).map(i -> (i - averageExecTime)).map(i -> i * i).average().getAsDouble());
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
		
		}else {
			result = new Dashboard();
			result.setPublicTasks(0);
			result.setPrivateTasks(0);
			result.setOngoingTasks(0);
			result.setFinishedTasks(0);
			result.setAverageExecTime(0.0);
			result.setDeviationExecTime(0.0);
			result.setMaxExecTime(0.0);
			result.setMinExecTime(0.0);
			result.setAverageWorkload(0.0);
			result.setDeviationWorkload(0.0);
			result.setMaxWorkload(0.0);
			result.setMinWorkload(0.0);
			return result;
		}
	}

}
