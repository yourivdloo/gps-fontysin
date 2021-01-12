package fontysin.project.controllers;

import fontysin.project.entities.dto.UserDTO;
import fontysin.project.entities.dto.UserPropertiesDTO;
import fontysin.project.entities.model.user.AppUser;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.services.PropertyService;
import fontysin.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyService propertyService;
    private final UserService userService;

    public PropertyController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @DeleteMapping(path="/delete/{id}")
    public @ResponseBody ResponseEntity<UserDTO> deleteUserProperties(@PathVariable int id) {
         boolean success = propertyService.removeUserProperty(id);
         if(!success) {
             throw new NotFoundException("A property with that Id doesn't exist");
         }

        return new ResponseEntity<>(getCurrentUserDto(), HttpStatus.OK);
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<UserDTO> addUserProperties(@RequestBody UserPropertiesDTO userPropertiesDTO) {
        propertyService.updateUserProperties(userPropertiesDTO);

        return new ResponseEntity<>(getCurrentUserDto(), HttpStatus.CREATED);
    }


    @PutMapping(path="/update")
    public @ResponseBody ResponseEntity<UserDTO> updateUserProperties(@RequestBody UserPropertiesDTO userPropertiesDTO) {
        propertyService.updateUserProperties(userPropertiesDTO);

        return new ResponseEntity<>(getCurrentUserDto(), HttpStatus.OK);
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody ResponseEntity<UserDTO> deleteUserProperties(@RequestBody UserPropertiesDTO userPropertiesDTO) {
        propertyService.removeUserProperties(userPropertiesDTO);

        return new ResponseEntity<>(getCurrentUserDto(), HttpStatus.OK);
    }

    private UserDTO getCurrentUserDto() {
        AppUser currentUser = userService.getUserByPcn(Util.getPcn());
        return new UserDTO(currentUser, propertyService.getUserProperties(currentUser.getPcn()));
    }
}
