package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.*;

@Entity(name = "Hobby")
public class UserHobby extends UserProperty {
    public UserHobby(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserHobby() {

    }
}
