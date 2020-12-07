package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Study")
public class UserStudy extends UserProperty {
    public UserStudy(AppUser appUser, String name, String school, String startDate, String endDate, boolean finished) {
        super(appUser, name);
        this.school = school;
        this.startDate = startDate;
        this.endDate = endDate;
        this.finished = finished;
    }

    public UserStudy(){

    }

    private String school;

    private String startDate;

    private String endDate;

    private boolean finished;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
