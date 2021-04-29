
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dashboard.Dashboard;
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
		return false;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "tasks", "publicTasks", "privateTasks", "ongoingTasks", "finishedTasks", "averageExecTime", "deviationExecTime", "maxExecTime", "minExecTime", "averageWorkload", "deviationWorkload", "maxWorkload", "minWorkload");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {

		assert request != null;

		Dashboard result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneDashboardById(id);
		return result;
	}

}
