package fontysin.project.dto;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

public class CompleteUser {
    public CompleteUser(int pcn, String emailAddress, String firstName, String prefix, String lastName, int privacySettings, String nationality, String birthday, String birthPlace, String phoneNumber, String address, String zipCode, String city, Iterable<UserProperty> userProperties) {
        this.pcn = pcn;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
        this.privacySettings = privacySettings;
        this.nationality = nationality;
        this.birthday = birthday;
        this.birthPlace = birthPlace;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.userProperties = new PropertyContainer(userProperties);
    }

    public CompleteUser(AppUser user, Iterable<UserProperty> userProperties){
        this.pcn = user.getPcn();
        this.emailAddress = user.getEmailAddress();
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
        this.userProperties = new PropertyContainer(userProperties);
    }

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
    private PropertyContainer userProperties;

    public int getPcn() {
        return pcn;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPrefix() {
        return prefix;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PropertyContainer getUserProperties() {
        return userProperties;
    }
}
