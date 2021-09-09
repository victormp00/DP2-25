
package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.shouts.XXX;
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
	@Query("select avg(t.xxxamount.amount) from XXX t group by t.xxxamount.currency")
	List<Double> xxxaverageGroupByCurrency();
	
	@Query("select stddev(t.xxxamount.amount) from XXX t group by t.xxxamount.currency")
	List<Double> xxxDeviationGroupByCurrency();
	
	@Query("select 1.0 * count(a) / (select count(b) from Shout b) from Shout a where a.xxx.xxxboolean = true")
	Double xxxFlaggedRatio();
	
	@Query("select 1.0 * count(a) / (select count(b) from Shout b) from Shout a where a.xxx.xxxamount.amount = 0")
	Double xxxratioBudgetZero();

	@Query("SELECT x FROM XXX x")
	List<XXX> findAllXXX();
}