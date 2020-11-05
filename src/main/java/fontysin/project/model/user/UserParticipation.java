package fontysin.project.model.user;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name = "user_participations")
public class UserParticipation {
    public UserParticipation(AppUser appUser, String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.appUser = appUser;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserParticipation(){

    }

    @Id
    @GeneratedValue
//    @Column(name="userparticipation_id")
    public int id;

    @ManyToOne
    @JoinColumn(name = "pcn", nullable = false)
    private AppUser appUser;

//    @Column(name="name")
    private String name;

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
