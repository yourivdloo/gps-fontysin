package fontysin.project.repositories;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserLicense;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserLicenseRepository extends CrudRepository<UserLicense, Integer> {
    List<UserLicense> findAllByAppUserPcn(int pcn);

    List<UserLicense> findAllByName(String license);
}
