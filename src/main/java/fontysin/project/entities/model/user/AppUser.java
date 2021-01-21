package fontysin.project.entities.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fontysin.project.entities.dto.UserDTO;
import fontysin.project.entities.model.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
//@Table(name = "users")
public class AppUser {
    public AppUser(UserDTO user) {
        this.pcn = user.getPcn();
        this.firstName = user.getFirstName();
        this.prefix = user.getPrefix();
        this.lastName = user.getLastName();
        this.emailAddress = user.getEmailAddress();
        this.privacySettings = user.getPrivacySettings();
        this.nationality = user.getNationality();
        this.birthday = user.getBirthday();
        this.birthPlace = user.getBirthPlace();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.zipCode = user.getZipCode();
        this.city = user.getCity();
        this.isStudent = true;
    }

    public AppUser(int pcn, String firstName, String prefix, String lastName) {
        this.pcn = pcn;
        this.firstName = firstName;
        this.prefix = prefix;
        this.lastName = lastName;
    }

    public AppUser(){

    }

    @Id
//    @Column(name="pcn")
    private int pcn;

//    @Column(name="first_name")
    private String firstName;

//    @Column(name="prefix")
    private String prefix;

//    @Column(name="last_name")
    private String lastName;

//    @Column(name="email_address")
    private String emailAddress;

//    @Column(name="privacy_settings")
    private int privacySettings;

//    @Column(name="nationality")
    private String nationality;

//    @Column(name="birthday")
    private String birthday;

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

//    @Column(name="isStudent")
    private Boolean isStudent;

    public String getName(){
        return  (this.firstName + " " + this.prefix).trim() + " " + this.lastName;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name="project_collaborators",
            joinColumns = { @JoinColumn(name = "pcn") },
            inverseJoinColumns = { @JoinColumn(name = "projectId") })
    Collection<Project> data = new ArrayList<>();

}
