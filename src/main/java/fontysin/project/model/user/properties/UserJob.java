package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Job")
public class UserJob extends UserProperty {
    public UserJob(AppUser appUser, String name, String city, LocalDateTime startDate, LocalDateTime endDate) {
        super(appUser, name);
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserJob(){

    }

    private String city;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

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
