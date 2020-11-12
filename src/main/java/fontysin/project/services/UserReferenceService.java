package fontysin.project.services;

import fontysin.project.model.user.UserReference;
import fontysin.project.repositories.UserReferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReferenceService {
    final UserReferenceRepository userReferenceRepository;

    public UserReferenceService(UserReferenceRepository userReferenceRepository){ this.userReferenceRepository = userReferenceRepository; }

    public List<UserReference> getAllUsersByReference(String reference){
        return userReferenceRepository.findAllByName(reference);
    }

    public List<UserReference> getAllReferencesByUser(int pcn){
        return userReferenceRepository.findAllByAppUserPcn(pcn);
    }

    public UserReference createUserReference(UserReference userReference){
        return userReferenceRepository.save(userReference);
    }

    public boolean deleteUserReference(int id){
        Optional<UserReference> userReference = userReferenceRepository.findById(id);

        if(userReference.isPresent()){
            userReferenceRepository.delete(userReference.get());
            return true;
        } else {
            return false;
        }
    }
}
