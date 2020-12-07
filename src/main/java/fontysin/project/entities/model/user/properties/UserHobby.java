package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Hobby")
public class UserHobby extends UserProperty {
    public UserHobby(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserHobby() {

    }
}
