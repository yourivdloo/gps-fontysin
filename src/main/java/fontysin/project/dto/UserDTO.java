package fontysin.project.dto;

import fontysin.project.model.user.UserProperty;
import fontysin.project.model.user.properties.*;

import java.time.LocalDateTime;
import java.util.List;

public class UserDTO {
    private int pcn;
    private String firstName;
    private String prefix;
    private String lastName;
    private String emailAddress;
    private int privacySettings;
    private String nationality;
    private String  birthday;
    private String birthPlace;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String city;

    private List<UserHobbyDTO> hobbies;
    private List<UserInterestDTO> interests;
    private List<UserJobDTO> jobs;
    private List<UserLanguage> languages;
    private List<UserLicense> licenses;
    private List<UserParticipation> participations;
    private List<UserPersonalityTrait> personalityTraits;
    private List<UserReference> references;
    private List<UserSkill> skills;
    private List<UserStudyDTO> studies;

    public int getPcn() {
        return pcn;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getPrefix() {
        return prefix;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public int getPrivacySettings() {
        return privacySettings;
    }
    public String getNationality() {
        return nationality;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getBirthPlace() {
        return birthPlace;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public String getZipCode() {
        return zipCode;
    }
    public String getCity() {
        return city;
    }

    public List<UserHobbyDTO> getHobbies() {
        return hobbies;
    }
    public List<UserInterestDTO> getInterests() {
        return interests;
    }
    public List<UserJobDTO> getJobs() {
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
    public List<UserStudyDTO> getStudies() {
        return studies;
    }
}
