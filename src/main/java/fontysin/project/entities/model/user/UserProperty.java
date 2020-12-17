package fontysin.project.entities.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UserProperty {
    protected UserProperty(AppUser appUser, String name) {
        this.appUser = appUser;
        this.name = name;
    }

    protected UserProperty() {

    }

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    protected int id;

    @Column
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinTable(name = "PropertyUser", joinColumns = {@JoinColumn(name = "PropertyId", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "UserPcn", referencedColumnName = "pcn")})
    private AppUser appUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
