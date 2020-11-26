package fontysin.project.dto;

import fontysin.project.model.user.properties.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserPropertiesDTO {
    private List<UserHobby> hobbies;
    private List<UserInterest> interests;
    private List<UserJob> jobs;
    private List<UserLanguage> languages;
    private List<UserLicense> licenses;
    private List<UserParticipation> participations;
    private List<UserPersonalityTrait> personalityTraits;
    private List<UserReference> references;
    private List<UserSkill> skills;
    private List<UserStudy> studies;
}
