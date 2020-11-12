package fontysin.project.services.user;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserHobby;
import fontysin.project.repositories.UserHobbyRepository;
import fontysin.project.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserHobbyService {

    final UserHobbyRepository userHobbyRepository;

    public UserHobbyService(UserHobbyRepository userHobbyRepository) {
        this.userHobbyRepository = userHobbyRepository;
    }

    public List<UserHobby> getAllHobbiesByUser(int pcn){
        return userHobbyRepository.findAllByAppUserPcn(pcn);
    }

    public List<UserHobby> getAllUsersByHobby(String name){
        return userHobbyRepository.findAllByName(name);
    }

    public UserHobby createUserHobby(UserHobby userHobby){
        return userHobbyRepository.save(userHobby);
    }

    public boolean deleteUserHobby(int id){
        Optional<UserHobby> userHobby = userHobbyRepository.findById(id);
        if (userHobby.isPresent()){
            userHobbyRepository.delete(userHobby.get());
            return  true;
        }
        return false;
    }
}
