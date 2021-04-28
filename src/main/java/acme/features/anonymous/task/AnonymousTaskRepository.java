package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousTaskRepository extends AbstractRepository{
	
	@Query("select a from Task a where a.id = ?1")
	Task findOneTaskById(int id);
	
	//and where a.finish = null
	@Query("select a from Task a where a.publico = TRUE and a.finished=false order by a.workload")
	Collection<Task> findAnonymousTasks();

}
