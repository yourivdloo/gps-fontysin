package fontysin.project.services;

import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserLicense;
import fontysin.project.repositories.UserLicenseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserLicenseService {
    final UserLicenseRepository userLicenseRepository;

    public UserLicenseService(UserLicenseRepository userLicenseRepository){ this.userLicenseRepository = userLicenseRepository; }

    public List<UserLicense> getAllUsersByLicense(String license){
        return userLicenseRepository.findAllByName(license);
    }

    public List<UserLicense> getAllLicensesByUser(int pcn){
        return userLicenseRepository.findAllByAppUserPcn(pcn);
    }

    public UserLicense createUserLicense(UserLicense userLicense){
        return userLicenseRepository.save(userLicense);
    }

    public boolean deleteUserLicense(int id){
        Optional<UserLicense> userLicense = userLicenseRepository.findById(id);

        if(userLicense.isPresent()){
            userLicenseRepository.delete(userLicense.get());
            return true;
        } else {
            return false;
        }
    }
}
