package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Language")
public class UserLanguage extends UserProperty {
    public UserLanguage(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserLanguage() {

    }
}
