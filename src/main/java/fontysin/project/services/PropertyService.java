package fontysin.project.services;

import fontysin.project.controllers.Util;
import fontysin.project.dto.UserPropertiesDTO;
import fontysin.project.dto.UserPropertyDTO;
import fontysin.project.exceptions.BadRequestException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;
import fontysin.project.model.user.properties.*;
import fontysin.project.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public void addUserProperties(UserPropertiesDTO userPropertiesDTO) {
        AppUser appUser = userService.getUserByPcn(Util.getPcn());

        List<UserProperty> toAdd = new ArrayList<>();

        if(userPropertiesDTO.getHobbies() != null && userPropertiesDTO.getHobbies().size() > 0){
            for(UserHobby userHobby : userPropertiesDTO.getHobbies()) {
                toAdd.add(new UserHobby(appUser, userHobby.getName()));
            }
        }

        if(userPropertiesDTO.getInterests() != null && userPropertiesDTO.getInterests().size() > 0) {
            for (UserInterest userInterest : userPropertiesDTO.getInterests()) {
                toAdd.add(new UserInterest(appUser, userInterest.getName()));
            }
        }

        if(userPropertiesDTO.getJobs() != null && userPropertiesDTO.getJobs().size() > 0) {
            for (UserJob userJob : userPropertiesDTO.getJobs()) {
                toAdd.add(new UserJob(appUser, userJob.getName(), userJob.getCity(), userJob.getStartDate(), userJob.getEndDate()));
            }
        }

        if(userPropertiesDTO.getLanguages() != null && userPropertiesDTO.getLanguages().size() > 0) {
            for (UserLanguage userLanguage : userPropertiesDTO.getLanguages()) {
                toAdd.add(new UserLanguage(appUser, userLanguage.getName()));
            }
        }

        if(userPropertiesDTO.getLicenses() != null && userPropertiesDTO.getLicenses().size() > 0) {
            for (UserLicense userLicense : userPropertiesDTO.getLicenses()) {
                toAdd.add(new UserLicense(appUser, userLicense.getName()));
            }
        }

        if(userPropertiesDTO.getParticipations() != null && userPropertiesDTO.getParticipations().size() > 0) {
            for (UserParticipation userParticipation : userPropertiesDTO.getParticipations()) {
                toAdd.add(new UserParticipation(appUser, userParticipation.getName(), userParticipation.getStartDate(), userParticipation.getEndDate()));
            }
        }

        if(userPropertiesDTO.getPersonalityTraits() != null && userPropertiesDTO.getPersonalityTraits().size() > 0) {
            for (UserPersonalityTrait userPersonalityTrait : userPropertiesDTO.getPersonalityTraits()) {
                toAdd.add(new UserPersonalityTrait(appUser, userPersonalityTrait.getName()));
            }
        }

        if(userPropertiesDTO.getReferences() != null && userPropertiesDTO.getReferences().size() > 0) {
            for (UserReference userReference : userPropertiesDTO.getReferences()) {
                toAdd.add(new UserReference(appUser, userReference.getName(), userReference.getPhoneNumber(), userReference.getEmail()));
            }
        }

        if(userPropertiesDTO.getSkills() != null && userPropertiesDTO.getSkills().size() > 0) {
            for (UserSkill userSkill : userPropertiesDTO.getSkills()) {
                toAdd.add(new UserSkill(appUser, userSkill.getName()));
            }
        }

        if(userPropertiesDTO.getStudies() != null && userPropertiesDTO.getStudies().size() > 0) {
            for (UserStudy userStudy : userPropertiesDTO.getStudies()) {
                toAdd.add(new UserStudy(appUser, userStudy.getName(), userStudy.getSchool(), userStudy.getStartDate(), userStudy.getEndDate(), userStudy.isFinished()));
            }
        }

        propertyRepository.saveAll(toAdd);
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
