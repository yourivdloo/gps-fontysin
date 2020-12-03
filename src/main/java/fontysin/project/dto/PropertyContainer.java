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
                    hobbies.add(prop);
                    break;
                case "UserInterest":
                    interests.add(prop);
                    break;
                case "UserJob":
                    jobs.add(prop);
                    break;
                case "UserLanguage":
                    languages.add(prop);
                    break;
                case "UserLicense":
                    licenses.add(prop);
                    break;
                case "UserParticipation":
                    participations.add(prop);
                    break;
                case "UserPersonalityTrait":
                    personalityTraits.add(prop);
                    break;
                case "UserReference":
                    references.add(prop);
                    break;
                case "UserSkill":
                    skills.add(prop);
                    break;
                case "UserStudy":
                    studies.add(prop);
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
    private List<Object> references = new ArrayList<>();
    private List<Object> skills = new ArrayList<>();
    private List<Object> studies = new ArrayList<>();

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

    public List<Object> getReferences() {
        return references;
    }

    public List<Object> getSkills() {
        return skills;
    }

    public List<Object> getStudies() {
        return studies;
    }
}
