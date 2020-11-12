package fontysin.project.controllers;

import fontysin.project.dto.UserPropertyDTO;
import fontysin.project.model.user.UserProperty;
import fontysin.project.services.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping(path = "/new")
    public @ResponseBody ResponseEntity<UserProperty> addUserProperty(@RequestBody UserPropertyDTO userPropertyDTO) {
        UserProperty property = propertyService.addUserProperty(userPropertyDTO);
        return new ResponseEntity<>(property, HttpStatus.CREATED);
    }
}
