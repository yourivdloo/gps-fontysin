package fontysin.project.model.user.properties;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserProperty;

import javax.persistence.*;

@Entity(name = "Skill")
public class UserSkill extends UserProperty {
    public UserSkill(AppUser appUser, String name) {
        super(appUser, name);
    }

    public UserSkill(){

    }
}
