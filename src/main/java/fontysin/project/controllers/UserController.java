package fontysin.project.controllers;

import fontysin.project.entities.dto.UserDTO;
import fontysin.project.entities.model.user.AppUser;
import fontysin.project.entities.model.user.UserProperty;
import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.services.PropertyService;
import fontysin.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PropertyService propertyService;

    String error= "A user with that pcn doesn't exist.";

    public UserController(UserService userService, PropertyService propertyService) {
        this.userService = userService;
        this.propertyService = propertyService;
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<AppUser>> getAllUsers(){
        Iterable<AppUser> users = userService.getAllUsers();
        if(!users.iterator().hasNext()) {
            throw new NotFoundException("There are currently no users in the database");
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path="/{pcn}")
    public @ResponseBody ResponseEntity<UserDTO> getUserByPcn(@PathVariable int pcn){
        AppUser user = userService.getUserByPcn(pcn);
        if (user != null){
            UserDTO toSend = new UserDTO(user, propertyService.getUserProperties(user.getPcn()));
            return new ResponseEntity<>(toSend, HttpStatus.OK);
        } else {
            throw new NotFoundException(error);
        }
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        if (Util.emptyOrNull(new String[]{user.getFirstName(), user.getLastName()})) {
            throw new BadRequestException("The user was not created - Missing Arguments");
        }

        AppUser result = userService.createUser(new AppUser(user));
        if(result == null) {
            throw new InternalServerException("We couldn't create the user");
        }

        propertyService.updateUserProperties(user.getUserProperties());

        UserDTO toSend = new UserDTO(result, propertyService.getUserProperties(result.getPcn()));

        return new ResponseEntity<>(toSend, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{pcn}")
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable int pcn){
        AppUser user = userService.getUserByPcn(pcn);
        if(user == null) {
            throw new NotFoundException(error);
        }

        boolean success = userService.deleteUser(pcn);
        if(!success) {
            throw new InternalServerException("We couldn't delete the user");
        }
        return new ResponseEntity<>("The user with PCN " + pcn + " has been deleted.", HttpStatus.OK);
    }

    @PutMapping(path="/{pcn}")
    public @ResponseBody ResponseEntity<UserDTO> updateUserInfo(@PathVariable int pcn, @RequestBody UserDTO user){
        if (Util.emptyOrNull(new String[]{user.getFirstName(), user.getLastName()})) {
            throw new BadRequestException("The user was not updated - Missing Arguments");
        }

        AppUser exists = userService.getUserByPcn(pcn);
        if(exists == null) {
            throw new NotFoundException(error);
        }

        AppUser updatedUser = userService.updateUser(new AppUser(user));
        Iterable<UserProperty> props = propertyService.getUserProperties(pcn);
        UserDTO result = new UserDTO(updatedUser, props);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(path="/search/{firstName}")
    public @ResponseBody ResponseEntity<Iterable<AppUser>> basicSearch(@PathVariable String firstName) {
        return new ResponseEntity<>(userService.searchByFirstName(firstName), HttpStatus.OK);
    }
}
