package fontysin.project.controllers;

import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserReference;
import fontysin.project.services.UserReferenceService;
import fontysin.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users/references")
public class UserReferenceController {
    private final UserReferenceService userReferenceService;
    private final UserService userService;

    public UserReferenceController(UserReferenceService userReferenceService, UserService userService) { this.userReferenceService = userReferenceService; this.userService = userService; }

    @GetMapping(path = "/{pcn}")
    public @ResponseBody
    ResponseEntity<List<UserReference>> getAllReferencesByUser(@PathVariable int pcn) {
        List<UserReference> userReferences = userReferenceService.getAllReferencesByUser(pcn);

        if(userReferences.isEmpty()){
            throw new NotFoundException("There are no references registered for the specified user");
        }
        return new ResponseEntity<>(userReferences, HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    public @ResponseBody ResponseEntity<List<UserReference>> getAllUsersByReference(@PathVariable String name){
        List<UserReference> userReferences = userReferenceService.getAllUsersByReference(name);

        if(userReferences.isEmpty()){
            throw new NotFoundException("There are no users with the specified reference");
        }
        return new ResponseEntity<>(userReferences, HttpStatus.OK);
    }

    @PostMapping(path = "/new")
    public @ResponseBody ResponseEntity<UserReference> createUserReference(@RequestBody UserReference userReference){
        if (userReference == null) {
            throw new BadRequestException("The reference was not created - Missing Arguments");
        }

        AppUser user = userService.getUserByPcn(Util.getPcn());
        userReference.setAppUser(user);
        UserReference result = userReferenceService.createUserReference(userReference);

        if(result == null) {
            throw new InternalServerException("We couldn't create the reference");
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(path= "/{id}")
    public @ResponseBody ResponseEntity<String> deleteReference(@PathVariable int id) {
        boolean success = userReferenceService.deleteUserReference(id);

        if(success){
            return new ResponseEntity<>("The reference with id " + id + " has successfully been deleted", HttpStatus.OK);
        }
        throw new InternalServerException("We couldn't delete the reference.");
    }
}
