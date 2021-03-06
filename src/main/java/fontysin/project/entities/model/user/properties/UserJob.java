package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Job")
public class UserJob extends UserProperty {
    public UserJob(AppUser appUser, String name, String companyName, String startDate, String endDate) {
        super(appUser, name);
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public UserJob(){

    }

    private String companyName;

    private String startDate;

    private String endDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
