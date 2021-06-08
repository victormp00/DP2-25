
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
			"maxWorkload", "minWorkload",
			//control check
			"xxxFlaggedRatio","xxxratio2020","xxxaverageGroupByCurrency1","xxxDeviationGroupByCurrency1",
			"xxxaverageGroupByCurrency2","xxxDeviationGroupByCurrency2");
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

		publicTasks = this.repository.publicTasks();
		privateTasks = this.repository.privateTasks();
		ongoingTasks = this.repository.ongoingTasks();
		finishedTasks = this.repository.finishedTasks();
		averageWorkload = this.repository.averageWorkload();
		deviationWorkload = this.repository.deviationWorkload();
		maxWorkload = this.repository.maxWorkload();
		minWorkload = this.repository.minWorkload();
		tasks = this.repository.findAllTasks();
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
		
		//control check
		final Double xxxFlaggedRatio=this.repository.xxxFlaggedRatio();
		final Double xxxratio2020=this.repository.xxxratio2020();
		final List<Double> xxxaverageGroupByCurrency = this.repository.xxxaverageGroupByCurrency();
		final List<Double> xxxDeviationGroupByCurrency=this.repository.xxxDeviationGroupByCurrency();
		
		final Double xxxaverageGroupByCurrency1=xxxaverageGroupByCurrency.get(0);
		final Double xxxDeviationGroupByCurrency1=xxxDeviationGroupByCurrency.get(0);
		final Double xxxaverageGroupByCurrency2=xxxaverageGroupByCurrency.get(1);
		final Double xxxDeviationGroupByCurrency2=xxxDeviationGroupByCurrency.get(1);
		
		result.setXxxaverageGroupByCurrency1(xxxaverageGroupByCurrency1);
		result.setXxxDeviationGroupByCurrency1(xxxDeviationGroupByCurrency1);
		result.setXxxaverageGroupByCurrency2(xxxaverageGroupByCurrency2);
		result.setXxxDeviationGroupByCurrency2(xxxDeviationGroupByCurrency2);
		result.setXxxFlaggedRatio(xxxFlaggedRatio);
		result.setXxxratio2020(xxxratio2020);
		
		
		return result;
	}

}
