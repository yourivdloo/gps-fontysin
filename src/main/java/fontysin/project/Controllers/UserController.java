package fontysin.project.Controllers;

import fontysin.project.Exceptions.BadRequestException;
import fontysin.project.Exceptions.InternalServerException;
import fontysin.project.Exceptions.NotFoundException;
import fontysin.project.Models.user.AppUser;
import fontysin.project.Repositories.UserRepository;
import fontysin.project.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<AppUser>> getAllUsers(){
        Iterable<AppUser> users = userService.getAllUsers();
        if(users == null) {
            throw new NotFoundException("There are currently no users in the database");
        }
        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @GetMapping(path="/{pcn}")
    public @ResponseBody ResponseEntity<AppUser> getUserByPcn(@PathVariable int pcn){
        AppUser user = userService.getUserByPcn(pcn);
        if (user != null){
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        } else {
            throw new NotFoundException("A user with that PCN doesn't exist");
        }
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<AppUser> createUser(@RequestBody AppUser user) {
        if (Util.EmptyOrNull(new String[]{user.getFirstName(), user.getLastName()})) {
            throw new BadRequestException("The user was not created - Missing Arguments");
        }

        AppUser result = userService.createUser(new AppUser(Util.GetPcn(), user.getFirstName(), user.getLastName()));
        if(result == null) {
            throw new InternalServerException("We couldn't create the user");
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
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
