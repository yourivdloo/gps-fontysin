package fontysin.project.controllers;

import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserParticipation;
import fontysin.project.services.UserParticipationService;
import fontysin.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/user/participation")
public class UserParticipationController {
    private final UserParticipationService userParticipationService;
    private final UserService userService;

    public UserParticipationController(UserParticipationService useruserParticipationService, UserService userService) { this.userParticipationService = useruserParticipationService; this.userService = userService; }

    @GetMapping(path = "/{pcn}")
    public @ResponseBody ResponseEntity<List<UserParticipation>> getAllParticipationsByUser(@PathVariable int pcn) {
        List<UserParticipation> userParticipations = userParticipationService.getAllParticipationsByUser(pcn);

        if(userParticipations.isEmpty()){
            throw new NotFoundException("There are no participations registered for the specified user");
        }
        return new ResponseEntity<>(userParticipations, HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    public @ResponseBody ResponseEntity<List<UserParticipation>> getAllUsersByParticipation(@PathVariable String name){
        List<UserParticipation> userParticipations = userParticipationService.getAllUsersByParticipation(name);

        if(userParticipations.isEmpty()){
            throw new NotFoundException("There are no users with the specified participation");
        }
        return new ResponseEntity<>(userParticipations, HttpStatus.OK);
    }

    @PostMapping(path = "/new")
    public @ResponseBody ResponseEntity<UserParticipation> createUserParticipation(@RequestBody UserParticipation userParticipation){
        if (userParticipation == null) {
            throw new BadRequestException("The participation was not created - Missing Arguments");
        }

        AppUser user = userService.getUserByPcn(Util.getPcn());
        userParticipation.setAppUser(user);
        UserParticipation result = userParticipationService.createUserParticipation(userParticipation);

        if(result == null) {
            throw new InternalServerException("We couldn't create the participation");
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(path= "/{id}")
    public @ResponseBody ResponseEntity<String> deleteParticipation(@PathVariable int id) {
        boolean success = userParticipationService.deleteUserParticipation(id);

        if(success){
            return new ResponseEntity<>("The participation with id " + id + " has successfully been deleted", HttpStatus.OK);
        }
        throw new InternalServerException("We couldn't delete the participation");
    }
}
