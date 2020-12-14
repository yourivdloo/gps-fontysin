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
            toAdd.add(new UserHobby(appUser, userHobby.getName()));
        }

        for (UserInterest userInterest : emptyIfNull(userPropertiesDTO.getInterests())) {
            toAdd.add(new UserInterest(appUser, userInterest.getName()));
        }

        for (UserJob userJob : emptyIfNull(userPropertiesDTO.getJobs())) {
            toAdd.add(new UserJob(appUser, userJob.getName(), userJob.getCompanyName(), userJob.getStartDate(), userJob.getEndDate()));
        }

        for (UserLanguage userLanguage : emptyIfNull(userPropertiesDTO.getLanguages())) {
            toAdd.add(new UserLanguage(appUser, userLanguage.getName()));
        }

        for (UserLicense userLicense : emptyIfNull(userPropertiesDTO.getLicenses())) {
            toAdd.add(new UserLicense(appUser, userLicense.getName()));
        }

        for (UserParticipation userParticipation : emptyIfNull(userPropertiesDTO.getParticipations())) {
            toAdd.add(new UserParticipation(appUser, userParticipation.getName(), userParticipation.getStartDate()));
        }


        for (UserPersonalityTrait userPersonalityTrait : emptyIfNull(userPropertiesDTO.getPersonalityTraits())) {
            toAdd.add(new UserPersonalityTrait(appUser, userPersonalityTrait.getName()));
        }

        for (UserReference userReference : emptyIfNull(userPropertiesDTO.getReferences())) {
            toAdd.add(new UserReference(appUser, userReference.getName(), userReference.getPhoneNumber(), userReference.getEmail()));
        }

        for (UserSkill userSkill : emptyIfNull(userPropertiesDTO.getSkills())) {
            toAdd.add(new UserSkill(appUser, userSkill.getName()));
        }

        for (UserStudy userStudy : emptyIfNull(userPropertiesDTO.getStudies())) {
            toAdd.add(new UserStudy(appUser, userStudy.getName(), userStudy.getSchool(), userStudy.getCity(), userStudy.getStartDate(), userStudy.getEndDate(), userStudy.isFinished()));
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
