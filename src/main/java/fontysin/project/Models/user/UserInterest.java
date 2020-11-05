package fontysin.project.Models.user;

import javax.persistence.*;

@Entity
//@Table(name = "user_interests")
public class UserInterest {
    public UserInterest(AppUser appUser, String name) {
        this.appUser = appUser;
        this.name = name;
    }

    public UserInterest(){

    }

    @Id
    @GeneratedValue
//    @Column(name="userinterest_id")
    public int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

//    @Column(name="name")
    private String name;

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
}
