package fontysin.project.services;

import fontysin.project.controllers.Util;
import fontysin.project.dto.UserPropertyDTO;
import fontysin.project.exceptions.BadRequestException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;
import fontysin.project.model.user.properties.*;
import fontysin.project.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    private final UserService userService;

    public PropertyService(PropertyRepository propertyRepository, UserService userService) {
        this.propertyRepository = propertyRepository;
        this.userService = userService;
    }

    public UserProperty addUserProperty(UserPropertyDTO userPropertyDTO) {
        String type = userPropertyDTO.getType();
        UserProperty toAdd;

        AppUser appUser = userService.getUserByPcn(Util.getPcn());
        switch(type) {
            case "hobby":
                toAdd = new UserHobby(appUser, userPropertyDTO.getName());
                break;
            case "interest":
                toAdd = new UserInterest(appUser, userPropertyDTO.getName());
                break;
            case "job":
                toAdd = new UserJob(appUser, userPropertyDTO.getName(), userPropertyDTO.getCity(), userPropertyDTO.getStartDate(), userPropertyDTO.getEndDate());
                break;
            case "language":
                toAdd = new UserLanguage(appUser, userPropertyDTO.getName());
                break;
            case "license":
                toAdd = new UserLicense(appUser, userPropertyDTO.getName());
                break;
            case "participation":
                toAdd = new UserParticipation(appUser, userPropertyDTO.getName(), userPropertyDTO.getStartDate(), userPropertyDTO.getEndDate());
                break;
            case "personality":
                toAdd = new UserPersonalityTrait(appUser, userPropertyDTO.getName());
                break;
            case "reference":
                toAdd = new UserReference(appUser, userPropertyDTO.getName(), userPropertyDTO.getPhoneNumber(), userPropertyDTO.getEmail());
                break;
            case "skill":
                toAdd = new UserSkill(appUser, userPropertyDTO.getName());
                break;
            case "study":
                toAdd = new UserStudy(appUser, userPropertyDTO.getName(), userPropertyDTO.getSchool(), userPropertyDTO.getStartDate(), userPropertyDTO.getEndDate(), userPropertyDTO.getFinished());
                break;
            default:
                throw new BadRequestException("The provided property is invalid");
        }
        return propertyRepository.save(toAdd);
    }

    public boolean removeUserProperty(int propertyId) {
        Optional<UserProperty> userPropertyOptional = propertyRepository.findById(propertyId);
        if(userPropertyOptional.isEmpty()) {
            return false;
        }

        propertyRepository.delete(userPropertyOptional.get());
        return true;
    }

    public Iterable<UserProperty> getUserProperties(int pcn) {
        return propertyRepository.findByAppUserPcn(pcn);
    }
}
