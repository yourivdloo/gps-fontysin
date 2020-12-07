package fontysin.project.entities.model.user.properties;

import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;

import javax.persistence.Entity;

@Entity(name = "Skill")
public class UserSkill extends UserProperty {
    public UserSkill(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserSkill(){

    }
}
