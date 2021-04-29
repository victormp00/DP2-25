
package acme.entities.dashboard;

import java.util.Comparator;
import java.util.List;

import javax.persistence.Entity;

import acme.entities.task.Task;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Dashboard extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	public List<Task>			tasks;

	// Derived attributes -----------------------------------------------------

	public Integer				publicTasks;

	public Integer				privateTasks;

	public Integer				ongoingTasks;

	public Integer				finishedTasks;

	public Double				averageExecTime;

	public Double				deviationExecTime;

	public Double				maxExecTime;

	public Double				minExecTime;

	public Double				averageWorkload;

	public Double				deviationWorkload;

	public Double				maxWorkload;

	public Double				minWorkload;

	// Tasks methods

	public Integer getPublicTasks() {
		Integer res = 0;
		for (final Task t : this.tasks) {
			final Boolean b = t.getPublico();
			if (Boolean.TRUE.equals(b)) {
				res++;
			}
		}
		return res;
	}

	public Integer getPrivateTasks() {
		Integer res = 0;
		for (final Task t : this.tasks) {
			final Boolean b = t.getPublico();
			if (Boolean.FALSE.equals(b)) {
				res++;
			}
		}
		return res;
	}

	public Integer getFinishedTasks() {
		Integer res = 0;
		for (final Task t : this.tasks) {
			final Boolean b = t.getFinished();
			if (Boolean.TRUE.equals(b)) {
				res++;
			}
		}
		return res;
	}

	public Integer getOngoingTasks() {
		Integer res = 0;
		for (final Task t : this.tasks) {
			final Boolean b = t.getFinished();
			if (Boolean.FALSE.equals(b)) {
				res++;
			}
		}
		return res;
	}

	// Execution time methods

	public Double getAverageET() {
		Double a = 0.0;
		for (final Task t : this.tasks) {
			a = a + t.getExecutionTime();
		}
		return a / this.tasks.size();
	}

	public Double getDeviationET() {
		final Double mean = this.getAverageET();
		Double x = 0.0;
		for (final Task t : this.tasks) {
			x = x + (Math.pow(t.getExecutionTime() - mean, 2));

		}
		return x / this.tasks.size();
	}

	public Double getMaxExecTime() {

		final Comparator<Task> cmp = Comparator.comparing(Task::getExecutionTime);

		return this.tasks.stream().max(cmp).get().getExecutionTime();
	}

	public Double getMinExecTime() {

		final Comparator<Task> cmp = Comparator.comparing(Task::getExecutionTime);

		return this.tasks.stream().min(cmp).get().getExecutionTime();
	}

	// Workload methods

	public Double getAverageWL() {
		Double a = 0.0;
		for (final Task t : this.tasks) {
			a = a + t.getWorkload();
		}
		return a / this.tasks.size();
	}

	public Double getDeviationWL() {
		final Double mean = this.getAverageWL();
		Double x = 0.0;
		for (final Task t : this.tasks) {
			x = x + (Math.pow(t.getWorkload() - mean, 2));

		}
		return x / this.tasks.size();
	}

	public Double getMaxWorkload() {

		final Comparator<Task> cmp = Comparator.comparing(Task::getWorkload);

		return this.tasks.stream().max(cmp).get().getWorkload();
	}

	public Double getMinWorkload() {

		final Comparator<Task> cmp = Comparator.comparing(Task::getWorkload);

		return this.tasks.stream().min(cmp).get().getWorkload();
	}
}
