package fontysin.project.controllers;

import fontysin.project.dto.CompleteUser;
import fontysin.project.dto.UserDTO;
import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.services.PropertyService;
import fontysin.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PropertyService propertyService;

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
    public @ResponseBody ResponseEntity<CompleteUser> getUserByPcn(@PathVariable int pcn){
        AppUser user = userService.getUserByPcn(pcn);
        if (user != null){
            CompleteUser toSend = new CompleteUser(user.getFirstName(), user.getLastName(), propertyService.getUserProperties(user.getPcn()));
            return new ResponseEntity<>(toSend, HttpStatus.OK);
        } else {
            throw new NotFoundException("A user with that PCN doesn't exist");
        }
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<CompleteUser> createUser(@RequestBody UserDTO user) {
        if (Util.emptyOrNull(new String[]{user.getFirstName(), user.getLastName()})) {
            throw new BadRequestException("The user was not created - Missing Arguments");
        }

        AppUser result = userService.createUser(new AppUser(Util.getPcn(), user.getFirstName(), user.getPrefix(), user.getLastName()));
        if(result == null) {
            throw new InternalServerException("We couldn't create the user");
        }

        propertyService.addUserProperties(user.getUserProperties());

        CompleteUser toSend = new CompleteUser(result.getFirstName(), result.getLastName(), propertyService.getUserProperties(result.getPcn()));

        return new ResponseEntity<>(toSend, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{pcn}")
    public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable int pcn){
        AppUser user = userService.getUserByPcn(pcn);
        if(user == null) {
            throw new NotFoundException("A user with that PCN doesn't exist");
        }

        boolean success = userService.deleteUser(pcn);
        if(!success) {
            throw new InternalServerException("We couldn't delete the user");
        }
        return new ResponseEntity<>("The user with PCN " + pcn + " has been deleted.", HttpStatus.OK);
    }
}
