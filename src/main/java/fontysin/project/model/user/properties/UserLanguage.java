package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.*;

@Entity(name = "Language")
public class UserLanguage extends UserProperty {
    public UserLanguage(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserLanguage() {

    }
}
