package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Participation")
public class UserParticipation extends UserProperty {
    public UserParticipation(AppUser appUser, String name, String startDate, String endDate) {
        super(appUser, name);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserParticipation(){

    }

    private String startDate;

    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
