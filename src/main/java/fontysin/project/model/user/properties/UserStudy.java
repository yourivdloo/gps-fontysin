package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Study")
public class UserStudy extends UserProperty {
    public UserStudy(AppUser appUser, String name, String school, LocalDateTime startDate, LocalDateTime endDate, boolean finished) {
        super(appUser, name);
        this.school = school;
        this.startDate = startDate;
        this.endDate = endDate;
        this.finished = finished;
    }

    public UserStudy(){

    }

    private String school;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean finished;

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