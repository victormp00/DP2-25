
package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.Maolet;
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

	@Query("select avg(t.workload) from Task t")
	Double averageWorkload();

	@Query("select stddev(t.workload) from Task t")
	Double deviationWorkload();

	@Query("select max(t.workload) from Task t")
	Double maxWorkload();

	@Query("select min(t.workload) from Task t")
	Double minWorkload();
	
	//control check
	@Query("select avg(t.budget.amount) from Maolet t group by t.budget.currency")
	List<Double> averageGroupByCurrency();
	
	@Query("select stddev(t.budget.amount) from Maolet t group by t.budget.currency")
	List<Double> deviationGroupByCurrency();
	
	@Query("select 1.0 * count(a) / (select count(b) from Shout b) from Shout a where a.maolet.important = true")
	Double ratioImportant();
	
	@Query("select 1.0 * count(a) / (select count(b) from Shout b) from Shout a where a.maolet.budget.amount = 0")
	Double ratioZeroBudget();

	@Query("SELECT x FROM Maolet x")
	List<Maolet> findAllMaolets();
}