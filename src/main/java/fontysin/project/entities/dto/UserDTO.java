package fontysin.project.entities.dto;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    public UserDTO(AppUser user, Iterable<UserProperty> userProperties) {
        this.pcn = user.getPcn();
        this.emailAddress = user.getEmailAddress();
        this.fullName = user.getName();
        this.firstName = user.getFirstName();
        this.prefix = user.getPrefix();
        this.lastName = user.getLastName();
        this.privacySettings = user.getPrivacySettings();
        this.nationality = user.getNationality();
        this.birthday = user.getBirthday();
        this.birthPlace = user.getBirthPlace();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.zipCode = user.getZipCode();
        this.city = user.getCity();
        this.isStudent = user.getIsStudent();
        this.userProperties = new UserPropertiesDTO(userProperties);
    }

    public UserDTO() {

    }

    private int pcn;
    private String emailAddress;
    private String fullName;
    private String firstName;
    private String prefix;
    private String lastName;
    private int privacySettings;
    private String nationality;
    private String birthday;
    private String birthPlace;
    private String phoneNumber;
    private String address;
    private String zipCode;
    private String city;
    private Boolean isStudent;
    private UserPropertiesDTO userProperties = new UserPropertiesDTO();
}
