package fontysin.project.controllers;

import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserJob;
import fontysin.project.model.user.properties.UserLanguage;
import fontysin.project.services.UserService;
import fontysin.project.services.user.UserJobService;
import fontysin.project.services.user.UserLanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserLanguageController {

    private final UserLanguageService userLanguageService;
    private final UserService userService;

    public UserLanguageController(UserLanguageService userLanguageService, UserService userService) {
        this.userLanguageService = userLanguageService;
        this.userService = userService;
    }

    @GetMapping(path="/{pcn}")
    public @ResponseBody
    ResponseEntity<List<UserLanguage>> getAllLanguagesByUser(@PathVariable int pcn){
        List<UserLanguage> languages= userLanguageService.getAllLanguagesByUser(pcn);
        if (languages.isEmpty() || languages == null){
            throw new NotFoundException("The user with that PCN doesn't have any languages yet");
        } else {
            return new ResponseEntity<>(languages, HttpStatus.OK);
        }
    }

    @GetMapping(path="/{name}")
    public @ResponseBody ResponseEntity<List<UserLanguage>> getAllUsersByLanguage(@PathVariable String name){
        List<UserLanguage> languages = userLanguageService.getAllUsersByLanguage(name);
        if (languages.isEmpty() || languages == null){
            throw new NotFoundException("There are no users with that language yet");
        } else {
            return new ResponseEntity<>(languages, HttpStatus.OK);
        }
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<UserLanguage> createLanguage(@RequestBody UserLanguage language){
        if (Util.emptyOrNull(new String[]{language.getName(), String.valueOf(Util.getPcn())})) {
            throw new BadRequestException("The language was not created - Missing Arguments");
        }
        AppUser user = userService.getUserByPcn(Util.getPcn());
        language.setAppUser(user);
        UserLanguage result = userLanguageService.createUserLanguage(language);
        if(result == null) {
            throw new InternalServerException("We couldn't create the language");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Boolean> deleteLanguage(@PathVariable int id){
        boolean result = userLanguageService.deleteUserLanguage(id);
        if (result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        throw new NotFoundException("There are no languages with that id");
    }
}
