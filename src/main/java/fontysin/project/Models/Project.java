package fontysin.project.Models;

import fontysin.project.Models.user.AppUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
//@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
//    @Column(name="project_id")
    private int project_id;

//    @Column(name="name")
    private String name;

//    @Column(name="url")
    private String url;

    @ManyToMany(mappedBy="data")
    private Collection<AppUser> users = new ArrayList<>();

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int id) {
        this.project_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
