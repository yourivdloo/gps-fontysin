package fontysin.project.repositories;

import fontysin.project.entities.model.user.UserProperty;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<UserProperty, Integer> {
    Iterable<UserProperty> findByAppUserPcn(int pcn);
}
