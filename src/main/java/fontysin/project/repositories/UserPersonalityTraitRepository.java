package fontysin.project.repositories;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserPersonalityTrait;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPersonalityTraitRepository extends CrudRepository<UserPersonalityTrait, Integer> {
    List<UserPersonalityTrait> findAllByAppUserPcn(int pcn);

    List<UserPersonalityTrait> findAllByName(String personalityTrait);
}
