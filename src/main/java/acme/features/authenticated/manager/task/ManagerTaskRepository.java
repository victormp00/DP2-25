package acme.features.authenticated.manager.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ManagerTaskRepository extends AbstractRepository{
	
	@Query("select a from Task a where a.id = ?1")
	Task findOneTaskById(int id);
	
	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);
	//and where a.finish = null
	@Query("select a from Task a where a.manager.userAccount.username = ?1")
	Collection<Task> findManagerTasks(String username);
	
	@Query("select a.manager from Task a where a.manager.userAccount.id = ?1")
	Manager findManager(int id);
	
//	@Query("select c from Task c where c.manager.userAccount.id = ?1")
//	Task findOneTaskByUserAccountId(int id);
	
	

}
