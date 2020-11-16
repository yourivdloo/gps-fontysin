package fontysin.project.model.user;

import fontysin.project.model.Project;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
//@Table(name = "users")
public class AppUser {
    public AppUser(int pcn, String firstName, String Prefix, String lastName) {
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
            joinColumns = { @JoinColumn(name = "pcn") },
            inverseJoinColumns = { @JoinColumn(name = "projectId") })
    Collection<Project> data = new ArrayList<>();

}
