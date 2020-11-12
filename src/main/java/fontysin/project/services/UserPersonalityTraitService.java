package fontysin.project.services;

import fontysin.project.model.user.properties.UserLicense;
import fontysin.project.model.user.properties.UserPersonalityTrait;
import fontysin.project.repositories.UserPersonalityTraitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserPersonalityTraitService {
    final UserPersonalityTraitRepository userPersonalityTraitRepository;

    public UserPersonalityTraitService(UserPersonalityTraitRepository userPersonalityTraitRepository){ this.userPersonalityTraitRepository = userPersonalityTraitRepository; }

    public List<UserPersonalityTrait> getAllUsersByPersonalityTrait(String personalityTrait){
        return userPersonalityTraitRepository.findAllByName(personalityTrait);
    }

    public List<UserPersonalityTrait> getAllPersonalityTraitsByUser(int pcn){
        return userPersonalityTraitRepository.findAllByAppUserPcn(pcn);
    }

    public UserPersonalityTrait createUserPersonalityTrait(UserPersonalityTrait userPersonalityTrait){
        return userPersonalityTraitRepository.save(userPersonalityTrait);
    }

    public boolean deleteUserPersonalityTrait(int id){
        Optional<UserPersonalityTrait> userPersonalityTrait = userPersonalityTraitRepository.findById(id);

        if(userPersonalityTrait.isPresent()){
            userPersonalityTraitRepository.delete(userPersonalityTrait.get());
            return true;
        } else {
            return false;
        }
    }
}
