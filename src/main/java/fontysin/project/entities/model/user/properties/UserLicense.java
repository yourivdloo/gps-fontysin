package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "License")
public class UserLicense extends UserProperty {
    public UserLicense(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserLicense(){

    }
}
