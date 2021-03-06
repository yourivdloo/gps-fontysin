package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;
@Entity(name = "Reference")
public class UserReference extends UserProperty {
    public UserReference(AppUser appUser, String name, String phoneNumber, String email) {
        super(appUser, name);
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UserReference(){

    }

    private String phoneNumber;

    private String email;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
