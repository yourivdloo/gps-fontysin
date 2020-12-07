package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Job")
public class UserJob extends UserProperty {
    public UserJob(AppUser appUser, String name, String city, String startDate, String endDate) {
        super(appUser, name);
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserJob(){

    }

    private String city;

    private String startDate;

    private String endDate;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}
