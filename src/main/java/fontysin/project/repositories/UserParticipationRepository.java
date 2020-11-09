package fontysin.project.repositories;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserParticipation;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserParticipationRepository extends CrudRepository<UserParticipation, Integer> {
    List<UserParticipation> findAllByAppUserPcn(int pcn);

    List<UserParticipation> findAllByName(String participation);
}
