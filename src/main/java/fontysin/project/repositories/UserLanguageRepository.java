package fontysin.project.repositories;
import fontysin.project.model.user.properties.UserLanguage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserLanguageRepository extends CrudRepository<UserLanguage, Integer> {
    List<UserLanguage> findAllByAppUserPcn(int pcn);
    List<UserLanguage> findAllByName(String name);
}
