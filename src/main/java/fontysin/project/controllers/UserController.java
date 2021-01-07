package fontysin.project.controllers;

import fontysin.project.entities.dto.UserDTO;
import fontysin.project.entities.model.user.UserProperty;
import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.entities.model.user.AppUser;
import fontysin.project.services.PropertyService;
import fontysin.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Iterator;

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

    @GetMapping(path="/current")
    public @ResponseBody ResponseEntity<UserDTO> getCurrentUser(){
        AppUser user = userService.getCurrentUser();
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

        propertyService.addUserProperties(user.getUserProperties());

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

    @PutMapping(path="/{pcn}/props")
    public @ResponseBody ResponseEntity<UserDTO> updateUserProperties(@PathVariable int pcn, @RequestBody UserDTO user) {
        if (Util.emptyOrNull(new String[]{user.getFirstName(), user.getLastName()})) {
            throw new BadRequestException("The user was not updated - Missing Arguments");
        }

        AppUser result = userService.getUserByPcn(pcn);
        if(result == null) {
            throw new NotFoundException(error);
        }

        propertyService.updateUserProperties(user.getUserProperties());

        Iterable<UserProperty> properties = propertyService.getUserProperties(pcn);

        Iterator<UserProperty> itr = properties.iterator();

        while(itr.hasNext()){
            boolean removed = true;
            int id = itr.next().getId();

            for(UserProperty prop : user.getUserProperties().getHobbies()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            for(UserProperty prop : user.getUserProperties().getInterests()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            for(UserProperty prop : user.getUserProperties().getJobs()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            for(UserProperty prop : user.getUserProperties().getLanguages()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            for(UserProperty prop : user.getUserProperties().getLicenses()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            for(UserProperty prop : user.getUserProperties().getParticipations()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            for(UserProperty prop : user.getUserProperties().getPersonalityTraits()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            for(UserProperty prop : user.getUserProperties().getReferences()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            for(UserProperty prop : user.getUserProperties().getSkills()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            for(UserProperty prop : user.getUserProperties().getStudies()){
                if(id == prop.getId() || prop.getId()==0){
                    removed = false;
                }
            }

            if(removed){
                propertyService.removeUserProperty(id);
            }
        }

        UserDTO toSend = new UserDTO(result, propertyService.getUserProperties(result.getPcn()));

        return new ResponseEntity<>(toSend, HttpStatus.OK);
    }
}
