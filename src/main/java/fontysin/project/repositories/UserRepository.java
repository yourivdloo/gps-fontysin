package fontysin.project.repositories;

import fontysin.project.entities.model.user.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByPcn(int pcn);
    Optional<Iterable<AppUser>> findAllByFirstNameContainsAndPrivacySettings(String searchString, int privacy);
}
