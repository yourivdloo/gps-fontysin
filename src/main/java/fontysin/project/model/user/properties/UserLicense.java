package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.*;

@Entity(name = "License")
public class UserLicense extends UserProperty {
    public UserLicense(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserLicense(){

    }
}
