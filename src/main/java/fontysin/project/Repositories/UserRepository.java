package fontysin.project.Repositories;

import fontysin.project.Models.user.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByPcn(int pcn);
}
