package fontysin.project.Models.user;

import javax.persistence.*;

@Entity
//@Table(name = "user_skills")
public class UserSkill {
    public UserSkill(AppUser appUser, String name) {
        this.appUser = appUser;
        this.name = name;
    }

    public UserSkill(){

    }

    @Id
    @GeneratedValue
//    @Column(name="userskill_id")
    public int id;

    @ManyToOne
    @JoinColumn(name = "pcn", nullable = false)
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
