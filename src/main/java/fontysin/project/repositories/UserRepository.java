package fontysin.project.repositories;

import fontysin.project.model.user.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByPcn(int pcn);
}
