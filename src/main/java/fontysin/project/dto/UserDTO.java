package fontysin.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int pcn;
    private String emailAddress;
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
    private UserPropertiesDTO userProperties;
}
