package fontysin.project.services;

import fontysin.project.controllers.Util;
import fontysin.project.entities.dto.UserPropertiesDTO;
import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;
import fontysin.project.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    private final UserService userService;

    public PropertyService(PropertyRepository propertyRepository, UserService userService) {
        this.propertyRepository = propertyRepository;
        this.userService = userService;
    }

    public void updateUserProperties(UserPropertiesDTO userPropertiesDTO) {
        AppUser appUser = userService.getUserByPcn(Util.getPcn());

        List<UserProperty> toUpdate = new ArrayList<>();
        for(UserProperty userProperty: userPropertiesDTO.getAll()) {
            userProperty.setAppUser(appUser);
            toUpdate.add(userProperty);
        }

        propertyRepository.saveAll(toUpdate);
    }

    public boolean removeUserProperty(int propertyId) {
        Optional<UserProperty> userPropertyOptional = propertyRepository.findById(propertyId);
        if (userPropertyOptional.isEmpty()) {
            return false;
        }

        propertyRepository.delete(userPropertyOptional.get());
        return true;
    }

    public void removeUserProperties(UserPropertiesDTO userPropertiesDTO) {
        propertyRepository.deleteAll(userPropertiesDTO.getAll());
    }

    public Iterable<UserProperty> getUserProperties(int pcn) {
        return propertyRepository.findByAppUserPcn(pcn);
    }
}
