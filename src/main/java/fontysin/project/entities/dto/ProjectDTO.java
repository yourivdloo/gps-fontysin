package fontysin.project.entities.dto;

import fontysin.project.entities.model.user.AppUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {
    private int id;
    private String name;
    private String url;
    private Collection<AppUser> users;
}
