package fontysin.project.controllers;

import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserHobby;
import fontysin.project.model.user.properties.UserInterest;
import fontysin.project.services.UserService;
import fontysin.project.services.user.UserHobbyService;
import fontysin.project.services.user.UserInterestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user/interest")
public class UserInterestController {

    private final UserInterestService userInterestService;
    private final UserService userService;

    public UserInterestController(UserInterestService userInterestService, UserService userService) {
        this.userInterestService = userInterestService;
        this.userService = userService;
    }

    @GetMapping(path="/{pcn}")
    public @ResponseBody
    ResponseEntity<List<UserInterest>> getAllInterestsByUser(@PathVariable int pcn){
        List<UserInterest> interests = userInterestService.getAllInterestsByUser(pcn);
        if (interests.isEmpty()){
            throw new NotFoundException("The user with that PCN doesn't have any interests yet");
        } else {
            return new ResponseEntity<>(interests, HttpStatus.OK);
        }
    }

    @GetMapping(path="/{name}")
    public @ResponseBody ResponseEntity<List<UserInterest>> getAllUsersByInterest(@PathVariable String name){
        List<UserInterest> interests = userInterestService.getAllUsersByInterest(name);
        if (interests.isEmpty()){
            throw new NotFoundException("There are no users with that interest yet");
        } else {
            return new ResponseEntity<>(interests, HttpStatus.OK);
        }
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<UserInterest> createInterest(@RequestBody UserInterest interest){
        if (Util.emptyOrNull(new String[]{interest.getName(), String.valueOf(Util.getPcn())})) {
            throw new BadRequestException("The interest was not created - Missing Arguments");
        }
        AppUser user = userService.getUserByPcn(Util.getPcn());
        interest.setAppUser(user);
        UserInterest result = userInterestService.createUserInterest(interest);
        if(result == null) {
            throw new InternalServerException("We couldn't create the interest");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Boolean> deleteInterest(@PathVariable int id){
        boolean result = userInterestService.deleteUserInterest(id);
        if (result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        throw new NotFoundException("There are no interests with that id");
    }

}
