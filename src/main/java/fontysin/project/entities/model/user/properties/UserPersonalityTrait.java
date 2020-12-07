package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Personality")
public class UserPersonalityTrait extends UserProperty {
    public UserPersonalityTrait(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserPersonalityTrait(){

    }
}
