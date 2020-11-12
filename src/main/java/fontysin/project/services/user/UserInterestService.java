package fontysin.project.services.user;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserHobby;
import fontysin.project.model.user.properties.UserInterest;
import fontysin.project.repositories.UserHobbyRepository;
import fontysin.project.repositories.UserInterestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserInterestService {

    final UserInterestRepository userInterestRepository;

    public UserInterestService(UserInterestRepository userInterestRepository) {
        this.userInterestRepository = userInterestRepository;
    }

    public List<UserInterest> getAllInterestsByUser(int pcn){
        return userInterestRepository.findAllByAppUserPcn(pcn);
    }

    public List<UserInterest> getAllUsersByInterest(String name){
        return userInterestRepository.findAllByName(name);
    }

    public UserInterest createUserInterest(UserInterest userInterest){ return userInterestRepository.save(userInterest); }

    public boolean deleteUserInterest(int id){
        Optional<UserInterest> userInterest = userInterestRepository.findById(id);
        if (userInterest.isPresent()){
            userInterestRepository.delete(userInterest.get());
            return  true;
        }
        return false;
    }
}
