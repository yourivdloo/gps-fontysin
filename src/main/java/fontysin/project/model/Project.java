package fontysin.project.model;

import fontysin.project.model.user.AppUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
//@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
//    @Column(name="project_id")
    private int projectId;

//    @Column(name="name")
    private String name;

//    @Column(name="url")
    private String url;

    @ManyToMany(mappedBy="data")
    private Collection<AppUser> users = new ArrayList<>();

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int id) {
        this.projectId = id;
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
