package fontysin.project.services;

import fontysin.project.controllers.Util;
import fontysin.project.entities.dto.UserPropertiesDTO;
import fontysin.project.entities.dto.UserPropertyDTO;
import fontysin.project.exceptions.BadRequestException;
import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;
import fontysin.project.entities.model.user.properties.*;
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
        switch (type) {
            case "hobby":
                toAdd = new UserHobby(appUser, userPropertyDTO.getName());
                break;
            case "interest":
                toAdd = new UserInterest(appUser, userPropertyDTO.getName());
                break;
            case "job":
                toAdd = new UserJob(appUser, userPropertyDTO.getName(), userPropertyDTO.getCompanyName(), userPropertyDTO.getStartDate(), userPropertyDTO.getEndDate());
                break;
            case "language":
                toAdd = new UserLanguage(appUser, userPropertyDTO.getName());
                break;
            case "license":
                toAdd = new UserLicense(appUser, userPropertyDTO.getName());
                break;
            case "participation":
                toAdd = new UserParticipation(appUser, userPropertyDTO.getName(), userPropertyDTO.getStartDate());
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
                toAdd = new UserStudy(appUser, userPropertyDTO.getName(), userPropertyDTO.getSchool(), userPropertyDTO.getCity(), userPropertyDTO.getStartDate(), userPropertyDTO.getEndDate(), userPropertyDTO.getFinished());
                break;
            default:
                throw new BadRequestException("The provided property is invalid");
        }
        return propertyRepository.save(toAdd);
    }

    public void addUserProperties(UserPropertiesDTO userPropertiesDTO) {
        AppUser appUser = userService.getUserByPcn(Util.getPcn());

        List<UserProperty> toAdd = new ArrayList<>();

        for (UserHobby userHobby : emptyIfNull(userPropertiesDTO.getHobbies())) {
            if(!userHobby.getName().equals("")) toAdd.add(new UserHobby(appUser, userHobby.getName()));
        }

        for (UserInterest userInterest : emptyIfNull(userPropertiesDTO.getInterests())) {
            if(!userInterest.getName().equals("")) toAdd.add(new UserInterest(appUser, userInterest.getName()));
        }

        for (UserJob userJob : emptyIfNull(userPropertiesDTO.getJobs())) {
            if(!userJob.getName().equals("")) toAdd.add(new UserJob(appUser, userJob.getName(), userJob.getCompanyName(), userJob.getStartDate(), userJob.getEndDate()));
        }

        for (UserLanguage userLanguage : emptyIfNull(userPropertiesDTO.getLanguages())) {
            if(!userLanguage.getName().equals("")) toAdd.add(new UserLanguage(appUser, userLanguage.getName()));
        }

        for (UserLicense userLicense : emptyIfNull(userPropertiesDTO.getLicenses())) {
            if(!userLicense.getName().equals("")) toAdd.add(new UserLicense(appUser, userLicense.getName()));
        }

        for (UserParticipation userParticipation : emptyIfNull(userPropertiesDTO.getParticipations())) {
            if(!userParticipation.getName().equals("")) toAdd.add(new UserParticipation(appUser, userParticipation.getName(), userParticipation.getStartDate()));
        }

        for (UserPersonalityTrait userPersonalityTrait : emptyIfNull(userPropertiesDTO.getPersonalityTraits())) {
            if(!userPersonalityTrait.getName().equals("")) toAdd.add(new UserPersonalityTrait(appUser, userPersonalityTrait.getName()));
        }

        for (UserReference userReference : emptyIfNull(userPropertiesDTO.getReferences())) {
            if(!userReference.getName().equals("")) toAdd.add(new UserReference(appUser, userReference.getName(), userReference.getPhoneNumber(), userReference.getEmail()));
        }

        for (UserSkill userSkill : emptyIfNull(userPropertiesDTO.getSkills())) {
            if(!userSkill.getName().equals("")) toAdd.add(new UserSkill(appUser, userSkill.getName()));
        }

        for (UserStudy userStudy : emptyIfNull(userPropertiesDTO.getStudies())) {
            if(!userStudy.getName().equals("")) toAdd.add(new UserStudy(appUser, userStudy.getName(), userStudy.getSchool(), userStudy.getCity(), userStudy.getStartDate(), userStudy.getEndDate(), userStudy.isFinished()));
        }

        propertyRepository.saveAll(toAdd);
    }

    public void updateUserProperties(UserPropertiesDTO userPropertiesDTO) {
        AppUser appUser = userService.getUserByPcn(Util.getPcn());

        List<UserProperty> toAdd = new ArrayList<>();
        for (UserHobby userHobby : emptyIfNull(userPropertiesDTO.getHobbies())) {
            if(!propertyRepository.findById(userHobby.getId()).isPresent()) {
                toAdd.add(new UserHobby(appUser, userHobby.getName()));
            } else {
                userHobby.setAppUser(appUser);
                propertyRepository.save(userHobby);
            }
        }

        for (UserInterest userInterest : emptyIfNull(userPropertiesDTO.getInterests())) {
            if(!propertyRepository.findById(userInterest.getId()).isPresent()) {
                toAdd.add(new UserInterest(appUser, userInterest.getName()));
            } else {
                userInterest.setAppUser(appUser);
                propertyRepository.save(userInterest);
            }
        }

        for (UserJob userJob : emptyIfNull(userPropertiesDTO.getJobs())) {
            if(!propertyRepository.findById(userJob.getId()).isPresent()) {
                toAdd.add(new UserJob(appUser, userJob.getName(), userJob.getCompanyName(), userJob.getStartDate(), userJob.getEndDate()));
            } else {
                userJob.setAppUser(appUser);
                propertyRepository.save(userJob);
            }
        }

        for (UserLanguage userLanguage : emptyIfNull(userPropertiesDTO.getLanguages())) {
            if(!propertyRepository.findById(userLanguage.getId()).isPresent()) {
                toAdd.add(new UserLanguage(appUser, userLanguage.getName()));
            } else {
                userLanguage.setAppUser(appUser);
                propertyRepository.save(userLanguage);
            }
        }

        for (UserLicense userLicense : emptyIfNull(userPropertiesDTO.getLicenses())) {
            if(!propertyRepository.findById(userLicense.getId()).isPresent()) {
                toAdd.add(new UserLicense(appUser, userLicense.getName()));
            } else {
                userLicense.setAppUser(appUser);
                propertyRepository.save(userLicense);
            }
        }

        for (UserParticipation userParticipation : emptyIfNull(userPropertiesDTO.getParticipations())) {
            if(!propertyRepository.findById(userParticipation.getId()).isPresent()) {
                toAdd.add(new UserParticipation(appUser, userParticipation.getName(), userParticipation.getStartDate()));
            } else {
                userParticipation.setAppUser(appUser);
                propertyRepository.save(userParticipation);
            }
        }

        for (UserPersonalityTrait userPersonalityTrait : emptyIfNull(userPropertiesDTO.getPersonalityTraits())) {
            if(!propertyRepository.findById(userPersonalityTrait.getId()).isPresent()) {
                toAdd.add(new UserPersonalityTrait(appUser, userPersonalityTrait.getName()));
            } else {
                userPersonalityTrait.setAppUser(appUser);
                propertyRepository.save(userPersonalityTrait);
            }
        }

        for (UserReference userReference : emptyIfNull(userPropertiesDTO.getReferences())) {
            if(!propertyRepository.findById(userReference.getId()).isPresent()) {
                toAdd.add(new UserReference(appUser, userReference.getName(), userReference.getPhoneNumber(), userReference.getEmail()));
            } else {
                userReference.setAppUser(appUser);
                propertyRepository.save(userReference);
            }
        }

        for (UserSkill userSkill : emptyIfNull(userPropertiesDTO.getSkills())) {
            if(!propertyRepository.findById(userSkill.getId()).isPresent()) {
                toAdd.add(new UserSkill(appUser, userSkill.getName()));
            } else {
                userSkill.setAppUser(appUser);
                propertyRepository.save(userSkill);
            }
        }

        for (UserStudy userStudy : emptyIfNull(userPropertiesDTO.getStudies())) {
            if(!propertyRepository.findById(userStudy.getId()).isPresent()) {
                toAdd.add(new UserStudy(appUser, userStudy.getName(), userStudy.getSchool(), userStudy.getCity(), userStudy.getStartDate(), userStudy.getEndDate(), userStudy.isFinished()));
            } else {
                userStudy.setAppUser(appUser);
                propertyRepository.save(userStudy);
            }
        }

        propertyRepository.saveAll(toAdd);
    }

    public boolean removeUserProperty(int propertyId) {
        Optional<UserProperty> userPropertyOptional = propertyRepository.findById(propertyId);
        if (userPropertyOptional.isEmpty()) {
            return false;
        }

        propertyRepository.delete(userPropertyOptional.get());
        return true;
    }

    public Iterable<UserProperty> getUserProperties(int pcn) {
        return propertyRepository.findByAppUserPcn(pcn);
    }

    private <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? List.of() : iterable;
    }
}
