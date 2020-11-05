package fontysin.project.Models.user;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "user_jobs")
public class UserJob {
    public UserJob(AppUser appUser, String name, String city, LocalDateTime startDate, LocalDateTime endDate) {
        this.appUser = appUser;
        this.name = name;
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserJob(){

    }

    @Id
    @GeneratedValue
//    @Column(name="userjob_id")
    public int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser appUser;

//    @Column(name="name")
    private String name;

//    @Column(name="city")
    private String city;

//    @Column(name="start_date")
    private LocalDateTime startDate;

//    @Column(name="end_date")
    private LocalDateTime endDate;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}
