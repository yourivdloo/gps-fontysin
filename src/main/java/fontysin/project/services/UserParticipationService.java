package fontysin.project.services;

import fontysin.project.model.user.UserLicense;
import fontysin.project.model.user.UserParticipation;
import fontysin.project.repositories.UserParticipationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserParticipationService {
    final UserParticipationRepository userParticipationRepository;

    public UserParticipationService(UserParticipationRepository userParticipationRepository){ this.userParticipationRepository = userParticipationRepository; }

    public List<UserParticipation> getAllUsersByParticipation(String participation){
        return userParticipationRepository.findAllByName(participation);
    }

    public List<UserParticipation> getAllParticipationsByUser(int pcn){
        return userParticipationRepository.findAllByAppUserPcn(pcn);
    }

    public UserParticipation createUserParticipation(UserParticipation userParticipation){
        return userParticipationRepository.save(userParticipation);
    }

    public boolean deleteUserParticipation(int id){
        Optional<UserParticipation> userParticipation = userParticipationRepository.findById(id);

        if(userParticipation.isPresent()){
            userParticipationRepository.delete(userParticipation.get());
            return true;
        } else {
            return false;
        }
    }
}
