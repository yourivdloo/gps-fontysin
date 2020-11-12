package fontysin.project.repositories;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserHobby;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserHobbyRepository extends CrudRepository<UserHobby, Integer> {
    List<UserHobby> findAllByAppUserPcn(int pcn);
    List<UserHobby> findAllByName(String name);
}
