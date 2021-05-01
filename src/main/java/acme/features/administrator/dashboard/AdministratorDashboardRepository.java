
package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
	
	@Query("SELECT t FROM Task t")
	List<Task> findAllTasks();
	
	@Query("select count(t.publico) from Task t where t.publico = TRUE")
	Integer publicTasks();

	@Query("select count(t.publico) from Task t where t.publico = FALSE")
	Integer privateTasks();
	
	@Query("select count(t.finished) from Task t where t.finished = FALSE")
	Integer ongoingTasks();
	
	@Query("select count(t.finished) from Task t where t.finished = TRUE")
	Integer finishedTasks();

	@Query("select avg(t.executionTime) from Task t")
	Double averageExecTime();

	@Query("select stddev(t.executionTime) from Task t")
	Double deviationExecTime();

	@Query("select max(t.executionTime) from Task t")
	Double maxExecTime();

	@Query("select min(t.executionTime) from Task t")
	Double minExecTime();

	@Query("select avg(t.workload) from Task t")
	Double averageWorkload();

	@Query("select stddev(t.workload) from Task t")
	Double deviationWorkload();

	@Query("select max(t.workload) from Task t")
	Double maxWorkload();

	@Query("select min(t.workload) from Task t")
	Double minWorkload();

}
