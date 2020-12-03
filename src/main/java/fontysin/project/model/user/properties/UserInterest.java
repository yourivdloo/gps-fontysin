package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Interest")
public class UserInterest extends UserProperty {
    public UserInterest(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserInterest() {

    }
}
