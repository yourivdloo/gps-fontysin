package fontysin.project.repositories;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserInterest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserInterestRepository extends CrudRepository<UserInterest, Integer> {
    List<UserInterest> findAllByAppUserPcn(int pcn);
    List<UserInterest> findAllByName(String name);
}
