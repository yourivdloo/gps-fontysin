package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Participation")
public class UserParticipation extends UserProperty {
    public UserParticipation(AppUser appUser, String name, String startDate) {
        super(appUser, name);
        this.startDate = startDate;
    }

    public UserParticipation(){

    }

    private String startDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
