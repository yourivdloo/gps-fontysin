package fontysin.project.controllers;

import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserLicense;
import fontysin.project.services.UserLicenseService;
import fontysin.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users/licenses")
public class UserLicenseController {
    private final UserLicenseService userLicenseService;
    private final UserService userService;

    public UserLicenseController(UserLicenseService userLicenseService, UserService userService) { this.userLicenseService = userLicenseService; this.userService = userService; }

    @GetMapping()
    public @ResponseBody ResponseEntity<List<UserLicense>> getAllLicensesByUser() {
        List<UserLicense> userLicenses = userLicenseService.getAllLicensesByUser(Util.GetPcn());

        if(userLicenses.isEmpty()){
            throw new NotFoundException("There are no licenses registered for the specified user");
        }
        return new ResponseEntity<>(userLicenses, HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    public @ResponseBody ResponseEntity<List<UserLicense>> getAllUsersByLicense(@PathVariable String name){
        List<UserLicense> userLicenses = userLicenseService.getAllUsersByLicense(name);

        if(userLicenses.isEmpty()){
            throw new NotFoundException("There are no users with the specified license");
        }
        return new ResponseEntity<>(userLicenses, HttpStatus.OK);
    }

    @PostMapping(path = "/new")
    public @ResponseBody ResponseEntity<UserLicense> createUserLicense(@RequestBody UserLicense userLicense){
        if (userLicense == null) {
            throw new BadRequestException("The license was not created - Missing Arguments");
        }

        AppUser user = userService.getUserByPcn(Util.GetPcn());
        userLicense.setAppUser(user);
        UserLicense result = userLicenseService.createUserLicense(userLicense);

        if(result == null) {
            throw new InternalServerException("We couldn't create the license");
        }
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path= "/{id}")
    public @ResponseBody ResponseEntity<String> deleteLicense(@PathVariable int id) {
        boolean success = userLicenseService.deleteUserLicense(id);

        if(success){
            return new ResponseEntity<>("The license with id " + id + " has successfully been deleted", HttpStatus.OK);
        }
        throw new InternalServerException("We couldn't delete the license");
    }
}
