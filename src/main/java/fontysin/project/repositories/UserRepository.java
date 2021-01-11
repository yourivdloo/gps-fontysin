package fontysin.project.repositories;

import fontysin.project.entities.model.user.AppUser;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends CrudRepository<AppUser, Integer> {
    Optional<AppUser> findByPcn(int pcn);
    Optional<Iterable<AppUser>> findAllByFirstNameContains(String searchString);
}
