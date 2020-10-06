package fontysin.project.Models;

import javax.persistence.*;

@Entity
//@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue
//    @Column(name="project_id")
    private int id;

//    @Column(name="name")
    private String name;

//    @Column(name="url")
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
