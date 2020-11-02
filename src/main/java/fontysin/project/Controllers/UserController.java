package fontysin.project.Controllers;

import fontysin.project.Exceptions.BadRequestException;
import fontysin.project.Exceptions.NotFoundException;
import fontysin.project.Models.user.AppUser;
import fontysin.project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/all")
    public @ResponseBody ResponseEntity<Iterable<AppUser>> getAllUsers(){
        Iterable<AppUser> users = userRepository.findAll();

        int size = 0;
        for(AppUser user : users){ size++; }

        if(size==0){ throw new NotFoundException("There are currently no users in the database"); }

        return new ResponseEntity<Iterable<AppUser>>(users, HttpStatus.FOUND);
    }

    @GetMapping(path="/pcn/{Pcn}")
    public @ResponseBody ResponseEntity<AppUser> getUserByPcn(@PathVariable String Pcn){
        Optional<AppUser> user = userRepository.findByPcn(Pcn);
        if (user.isPresent()){
            return new ResponseEntity<AppUser>(user.get(), HttpStatus.FOUND);
        } else {
            throw new NotFoundException("No user found for this PCN. Please provide a valid PCN.");
        }
    }

    @GetMapping(path="/id/{Id}")
    public @ResponseBody ResponseEntity<AppUser> getUserById(@PathVariable int Id){
        Optional<AppUser> user = userRepository.findById(Id);
        if(user.isPresent()) {
            return new ResponseEntity<AppUser>(user.get(), HttpStatus.FOUND);
        } else {
            throw new NotFoundException("No user found for this Id. Please provide a valid Id.");

        }
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<AppUser> createUser(@RequestParam(required = false) String pcn, @RequestParam(required= false) String firstName, @RequestParam(required= false) String lastName) {
        if (Util.EmptyOrNull(new String[]{pcn, firstName, lastName})) {
            throw new BadRequestException("The user can not be added because it is not complete");
        }
        AppUser user = new AppUser(pcn, firstName, lastName);
        AppUser result = userRepository.save(user);
        return new ResponseEntity<AppUser>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/id/{Id}")
    public @ResponseBody ResponseEntity deleteUser(@PathVariable int Id){
        Optional<AppUser> user = userRepository.findById(Id);
        if(user.isPresent()){
            userRepository.delete(user.get());
            return new ResponseEntity("User has successfully been deleted.", HttpStatus.OK);
        } else {
            throw new NotFoundException("User doesn't exist. Might have already been deleted.");
        }
    }
}
