package fontysin.project.repositories;

import fontysin.project.model.user.properties.UserSkill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserSkillRepository extends CrudRepository<UserSkill, Integer> {
    List<UserSkill> findAllByAppUserPcn(int pcn);

    List<UserSkill> findAllByName(String skill);
}
