
package acme.features.administrator.threshold;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam.Threshold;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ThresholdRepository extends AbstractRepository {

	@Query("select a from Threshold a")
	Threshold findSpamEntity(int id);

	@Query("select a from Threshold a")
	Collection<Threshold> findListEntity();

}
