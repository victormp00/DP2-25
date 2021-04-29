package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.dashboard.Dashboard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{
	@Query("select a from Dashboard a where a.id = ?1")
	Dashboard findOneDashboardById(int id);
}
