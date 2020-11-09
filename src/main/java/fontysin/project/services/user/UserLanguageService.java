package fontysin.project.services.user;

import fontysin.project.model.user.UserJob;
import fontysin.project.model.user.UserLanguage;
import fontysin.project.repositories.UserJobRepository;
import fontysin.project.repositories.UserLanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLanguageService {

    final UserLanguageRepository userLanguageRepository;

    public UserLanguageService(UserLanguageRepository userLanguageRepository) {
        this.userLanguageRepository = userLanguageRepository;
    }

    public List<UserLanguage> getAllLanguagesByUser(int pcn){
        return userLanguageRepository.findAllByAppUserPcn(pcn);
    }

    public List<UserLanguage> getAllUsersByLanguage(String name){
        return userLanguageRepository.findAllByName(name);
    }

    public UserLanguage createUserLanguage(UserLanguage userLanguage){
        return userLanguageRepository.save(userLanguage);
    }

    public boolean deleteUserLanguage(int id){
        Optional<UserLanguage> userLanguage = userLanguageRepository.findById(id);
        if (userLanguage.isPresent()){
            userLanguageRepository.delete(userLanguage.get());
            return  true;
        }
        return false;
    }

}
