package fontysin.project.services.user;

import fontysin.project.model.user.properties.UserInterest;
import fontysin.project.model.user.properties.UserJob;
import fontysin.project.repositories.UserInterestRepository;
import fontysin.project.repositories.UserJobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserJobService {

    final UserJobRepository userJobRepository;

    public UserJobService(UserJobRepository userJobRepository) {
        this.userJobRepository = userJobRepository;
    }

    public List<UserJob> getAllJobsByUser(int pcn){
        return userJobRepository.findAllByAppUserPcn(pcn);
    }

    public List<UserJob> getAllUsersByJob(String name){
        return userJobRepository.findAllByName(name);
    }

    public UserJob createUserJob(UserJob userJob){
        return userJobRepository.save(userJob);
    }

    public boolean deleteUserJob(int id){
        Optional<UserJob> userJob = userJobRepository.findById(id);
        if (userJob.isPresent()){
            userJobRepository.delete(userJob.get());
            return  true;
        }
        return false;
    }

}
