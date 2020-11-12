package fontysin.project.controllers;

import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserPersonalityTrait;
import fontysin.project.services.UserPersonalityTraitService;
import fontysin.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user/personality")
public class UserPersonalityTraitController {
    private final UserPersonalityTraitService userPersonalityTraitService;
    private final UserService userService;

    public UserPersonalityTraitController(UserPersonalityTraitService userPersonalityTraitService, UserService userService) { this.userPersonalityTraitService = userPersonalityTraitService; this.userService = userService; }

    @GetMapping(path = "/{pcn}")
    public @ResponseBody
    ResponseEntity<List<UserPersonalityTrait>> getAllPersonalityTraitsByUser(@PathVariable int pcn) {
        List<UserPersonalityTrait> userPersonalityTraits = userPersonalityTraitService.getAllPersonalityTraitsByUser(pcn);

        if(userPersonalityTraits.isEmpty()){
            throw new NotFoundException("There are no personality traits registered for the specified user");
        }
        return new ResponseEntity<>(userPersonalityTraits, HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    public @ResponseBody ResponseEntity<List<UserPersonalityTrait>> getAllUsersByPersonalityTrait(@PathVariable String name){
        List<UserPersonalityTrait> userPersonalityTraits = userPersonalityTraitService.getAllUsersByPersonalityTrait(name);

        if(userPersonalityTraits.isEmpty()){
            throw new NotFoundException("There are no users with the specified participation");
        }
        return new ResponseEntity<>(userPersonalityTraits, HttpStatus.OK);
    }

    @PostMapping(path = "/new")
    public @ResponseBody ResponseEntity<UserPersonalityTrait> createUserPersonalityTrait(@RequestBody UserPersonalityTrait userPersonalityTrait){
        if (userPersonalityTrait == null) {
            throw new BadRequestException("The personality trait was not created - Missing Arguments");
        }

        AppUser user = userService.getUserByPcn(Util.getPcn());
        userPersonalityTrait.setAppUser(user);
        UserPersonalityTrait result = userPersonalityTraitService.createUserPersonalityTrait(userPersonalityTrait);

        if(result == null) {
            throw new InternalServerException("We couldn't create the personality trait.");
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(path= "/{id}")
    public @ResponseBody ResponseEntity<String> deletePersonalityTrait(@PathVariable int id) {
        boolean success = userPersonalityTraitService.deleteUserPersonalityTrait(id);

        if(success){
            return new ResponseEntity<>("The personality trait with id " + id + " has successfully been deleted", HttpStatus.OK);
        }
        throw new InternalServerException("We couldn't delete the personality trait");
    }
}
