package fontysin.project.services;

import fontysin.project.model.user.properties.UserReference;
import fontysin.project.model.user.properties.UserSkill;
import fontysin.project.repositories.UserReferenceRepository;
import fontysin.project.repositories.UserSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSkillService {
    final UserSkillRepository userSkillRepository;

    public UserSkillService(UserSkillRepository userSkillRepository){ this.userSkillRepository = userSkillRepository; }

    public List<UserSkill> getAllUsersBySkill(String skill){
        return userSkillRepository.findAllByName(skill);
    }

    public List<UserSkill> getAllSkillsByUser(int pcn){
        return userSkillRepository.findAllByAppUserPcn(pcn);
    }

    public UserSkill createUserSkill(UserSkill userSkill){
        return userSkillRepository.save(userSkill);
    }

    public boolean deleteUserSkill(int id){
        Optional<UserSkill> userSkill = userSkillRepository.findById(id);

        if(userSkill.isPresent()){
            userSkillRepository.delete(userSkill.get());
            return true;
        } else {
            return false;
        }
    }
}
