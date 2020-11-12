package fontysin.project.controllers;
import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserHobby;
import fontysin.project.services.UserService;
import fontysin.project.services.user.UserHobbyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/hobbies")
public class UserHobbyController {
    private final UserHobbyService userHobbyService;
    private final UserService userService;

    public UserHobbyController(UserHobbyService userHobbyService, UserService userService) {
        this.userHobbyService = userHobbyService;
        this.userService = userService;
    }

    @GetMapping(path="/{pcn}")
    public @ResponseBody ResponseEntity<List<UserHobby>> getAllHobbiesByUser(@PathVariable int pcn){
        List<UserHobby> hobbies = userHobbyService.getAllHobbiesByUser(pcn);
        if (hobbies.isEmpty()){
            throw new NotFoundException("The user with that PCN doesn't have any hobbies yet");
        } else {
            return new ResponseEntity<>(hobbies, HttpStatus.OK);
        }
    }

    @GetMapping(path="/{name}")
    public @ResponseBody ResponseEntity<List<UserHobby>> getAllUsersByHobby(@PathVariable String name){
        List<UserHobby> hobbies = userHobbyService.getAllUsersByHobby(name);
        if (hobbies.isEmpty()){
            throw new NotFoundException("There are no users with that hobby yet");
        } else {
            return new ResponseEntity<>(hobbies, HttpStatus.OK);
        }
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<UserHobby> createHobby(@RequestBody UserHobby hobby){
        if (Util.emptyOrNull(new String[]{hobby.getName(), String.valueOf(Util.getPcn())})) {
            throw new BadRequestException("The hobby was not created - Missing Arguments");
        }
        AppUser user = userService.getUserByPcn(Util.getPcn());
        hobby.setAppUser(user);
        UserHobby result = userHobbyService.createUserHobby(hobby);
        if(result == null) {
            throw new InternalServerException("We couldn't create the hobby");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Boolean> deleteHobby(@PathVariable int id){
        boolean result = userHobbyService.deleteUserHobby(id);
        if (result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        throw new NotFoundException("There are no hobbies with that id");
    }
}
