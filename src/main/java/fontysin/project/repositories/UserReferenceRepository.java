package fontysin.project.repositories;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserReference;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserReferenceRepository extends CrudRepository<UserReference, Integer> {
    List<UserReference> findAllByAppUserPcn(int pcn);

    List<UserReference> findAllByName(String participation);
}
