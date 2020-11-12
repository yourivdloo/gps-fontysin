package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.*;

@Entity(name = "Personality")
public class UserPersonalityTrait extends UserProperty {
    public UserPersonalityTrait(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserPersonalityTrait(){

    }
}
