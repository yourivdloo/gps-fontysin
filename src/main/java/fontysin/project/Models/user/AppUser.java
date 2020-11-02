package fontysin.project.Models.user;

import fontysin.project.Models.Project;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
//@Table(name = "users")
public class AppUser {
    public AppUser(String pcn, String firstName, String lastName) {
        this.pcn = pcn;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public AppUser(){

    }

    @Id
    @GeneratedValue
//    @Column(name="user_id")
    private int user_id;

//    @Column(name="pcn")
    private String pcn;

//    @Column(name="email_address")
    private String emailAddress;

//    @Column(name="first_name")
    private String firstName;

//    @Column(name="last_name")
    private String lastName;

//    @Column(name="privacy_settings")
    private int privacySettings;

//    @Column(name="nationality")
    private String nationality;

//    @Column(name="birthday")
    private LocalDateTime birthday;

//    @Column(name="birth_place")
    private String birthPlace;

//    @Column(name="phone_number")
    private String phoneNumber;

//    @Column(name="address")
    private String address;

//    @Column(name="zip_code")
    private String zipCode;

//    @Column(name="city")
    private String city;

    @ManyToMany
    @JoinTable(name="project_collaborators",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") })
    Collection<Project> data = new ArrayList<>();

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int id) {
        this.user_id = id;
    }

    public String getPcn() {
        return pcn;
    }

    public void setPcn(String pcn) {
        this.pcn = pcn;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPrivacySettings() {
        return privacySettings;
    }

    public void setPrivacySettings(int privacySettings) {
        this.privacySettings = privacySettings;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
