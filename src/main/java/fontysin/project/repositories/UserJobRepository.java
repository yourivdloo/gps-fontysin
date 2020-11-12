package fontysin.project.repositories;

import fontysin.project.model.user.properties.UserInterest;
import fontysin.project.model.user.properties.UserJob;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserJobRepository extends CrudRepository<UserJob, Integer> {
    List<UserJob> findAllByAppUserPcn(int pcn);
    List<UserJob> findAllByName(String name);
}
