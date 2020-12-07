package fontysin.project.entities.dto;


import fontysin.project.exceptions.InternalServerException;
import fontysin.project.entities.model.user.UserProperty;
import fontysin.project.entities.model.user.properties.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserPropertiesDTO {
    public UserPropertiesDTO(Iterable<UserProperty> userProperties) {
        for(UserProperty prop : userProperties) {
            switch(prop.getClass().getSimpleName()) {
                case "UserHobby":
                    hobbies.add((UserHobby) prop);
                    break;
                case "UserInterest":
                    interests.add((UserInterest) prop);
                    break;
                case "UserJob":
                    jobs.add((UserJob) prop);
                    break;
                case "UserLanguage":
                    languages.add((UserLanguage) prop);
                    break;
                case "UserLicense":
                    licenses.add((UserLicense) prop);
                    break;
                case "UserParticipation":
                    participations.add((UserParticipation) prop);
                    break;
                case "UserPersonalityTrait":
                    personalityTraits.add((UserPersonalityTrait) prop);
                    break;
                case "UserReference":
                    references.add((UserReference) prop);
                    break;
                case "UserSkill":
                    skills.add((UserSkill) prop);
                    break;
                case "UserStudy":
                    studies.add((UserStudy) prop);
                    break;
                default:
                    throw new InternalServerException("An unknown UserProperty was encountered, type=" + prop.getClass().toString());
            }
        }
    }

    public UserPropertiesDTO(){}

    private List<UserHobby> hobbies = new ArrayList<>();
    private List<UserInterest> interests = new ArrayList<>();
    private List<UserJob> jobs = new ArrayList<>();
    private List<UserLanguage> languages = new ArrayList<>();
    private List<UserLicense> licenses = new ArrayList<>();
    private List<UserParticipation> participations = new ArrayList<>();
    private List<UserPersonalityTrait> personalityTraits = new ArrayList<>();
    private List<UserReference> references = new ArrayList<>();
    private List<UserSkill> skills = new ArrayList<>();
    private List<UserStudy> studies = new ArrayList<>();
}
