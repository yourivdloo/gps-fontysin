package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Participation")
public class UserParticipation extends UserProperty {
    public UserParticipation(AppUser appUser, String name, LocalDateTime startDate, LocalDateTime endDate) {
        super(appUser, name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserParticipation(){

    }

    private LocalDateTime startDate;

    private LocalDateTime endDate;

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
