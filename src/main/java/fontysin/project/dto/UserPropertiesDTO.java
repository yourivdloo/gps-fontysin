package fontysin.project.dto;

import fontysin.project.model.user.properties.*;

import java.util.List;

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

    public List<UserHobby> getHobbies() {
        return hobbies;
    }

    public List<UserInterest> getInterests() {
        return interests;
    }

    public List<UserJob> getJobs() {
        return jobs;
    }

    public List<UserLanguage> getLanguages() {
        return languages;
    }

    public List<UserLicense> getLicenses() {
        return licenses;
    }

    public List<UserParticipation> getParticipations() {
        return participations;
    }

    public List<UserPersonalityTrait> getPersonalityTraits() {
        return personalityTraits;
    }

    public List<UserReference> getReferences() {
        return references;
    }

    public List<UserSkill> getSkills() {
        return skills;
    }

    public List<UserStudy> getStudies() {
        return studies;
    }
}
