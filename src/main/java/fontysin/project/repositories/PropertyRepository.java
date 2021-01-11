package fontysin.project.repositories;

import fontysin.project.entities.model.user.UserProperty;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PropertyRepository extends CrudRepository<UserProperty, Integer> {
    Iterable<UserProperty> findByAppUserPcn(int pcn);
}
