package fontysin.project.Models.user;

import javax.persistence.*;

@Entity
//@Table(name = "user_references")
public class UserReference {
    public UserReference(AppUser appUser, String name, String phoneNumber, String email) {
        this.appUser = appUser;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UserReference(){

    }

    @Id
    @GeneratedValue
//    @Column(name="userreference_id")
    public int id;

    @ManyToOne
    @JoinColumn(name = "pcn", nullable = false)
    private AppUser appUser;

//    @Column(name="name")
    private String name;

//    @Column(name="phone_number")
    private String phoneNumber;

//    @Column(name="email_address")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
