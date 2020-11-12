package fontysin.project.dto;


import fontysin.project.exceptions.InternalServerException;
import fontysin.project.model.user.UserProperty;

import java.util.ArrayList;
import java.util.List;

public class PropertyContainer {
    public PropertyContainer(Iterable<UserProperty> userProperties) {
        for(UserProperty prop : userProperties) {
            switch(prop.getClass().getSimpleName()) {
                case "UserHobby":
                    hobbies.add(prop.getName());
                    break;
                case "UserInterest":
                    interests.add(prop.getName());
                    break;
                case "UserJob":
                    jobs.add(prop.getName());
                    break;
                case "UserLanguage":
                    languages.add(prop.getName());
                    break;
                case "UserLicense":
                    licenses.add(prop.getName());
                    break;
                case "UserParticipation":
                    participations.add(prop.getName());
                    break;
                case "UserPersonalityTrait":
                    personalityTraits.add(prop.getName());
                    break;
                case "UserReference":
                    userReferences.add(prop.getName());
                    break;
                case "UserSkill":
                    userSkills.add(prop.getName());
                    break;
                case "UserStudy":
                    userStudies.add(prop.getName());
                    break;
                default:
                    throw new InternalServerException("An unknown UserProperty was encountered, type=" + prop.getClass().toString());
            }
        }
    }

    private List<Object> hobbies = new ArrayList<>();
    private List<Object> interests = new ArrayList<>();
    private List<Object> jobs = new ArrayList<>();
    private List<Object> languages = new ArrayList<>();
    private List<Object> licenses = new ArrayList<>();
    private List<Object> participations = new ArrayList<>();
    private List<Object> personalityTraits = new ArrayList<>();
    private List<Object> userReferences = new ArrayList<>();
    private List<Object> userSkills = new ArrayList<>();
    private List<Object> userStudies = new ArrayList<>();

    public List<Object> getHobbies() {
        return hobbies;
    }

    public List<Object> getInterests() {
        return interests;
    }

    public List<Object> getJobs() {
        return jobs;
    }

    public List<Object> getLanguages() {
        return languages;
    }

    public List<Object> getLicenses() {
        return licenses;
    }

    public List<Object> getParticipations() {
        return participations;
    }

    public List<Object> getPersonalityTraits() {
        return personalityTraits;
    }

    public List<Object> getUserReferences() {
        return userReferences;
    }

    public List<Object> getUserSkills() {
        return userSkills;
    }

    public List<Object> getUserStudies() {
        return userStudies;
    }
}
