package fontysin.project.Models.user;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "user_studies")
public class UserStudy {
    public UserStudy(AppUser appUser, String name, String school, LocalDateTime startDate, LocalDateTime endDate, boolean finished) {
        this.appUser = appUser;
        this.name = name;
        this.school = school;
        this.startDate = startDate;
        this.endDate = endDate;
        this.finished = finished;
    }

    public UserStudy(){

    }

    @Id
    @GeneratedValue
//    @Column(name="userstudy_id")
    public int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

//    @Column(name="name")
    private String name;

//    @Column(name="school")
    private String school;

//    @Column(name="start_date")
    private LocalDateTime startDate;

//    @Column(name="end_date")
    private LocalDateTime endDate;

//    @Column(name="finished")
    private boolean finished;

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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
