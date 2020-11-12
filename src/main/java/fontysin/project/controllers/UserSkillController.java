package fontysin.project.controllers;

import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.UserSkill;
import fontysin.project.services.UserService;
import fontysin.project.services.UserSkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users/skill")
public class UserSkillController {
    private final UserSkillService userSkillService;
    private final UserService userService;

    public UserSkillController(UserSkillService userSkillService, UserService userService) { this.userSkillService = userSkillService; this.userService = userService; }

    @GetMapping()
    public @ResponseBody
    ResponseEntity<List<UserSkill>> getAllSkillsByUser() {
        List<UserSkill> userSkills = userSkillService.getAllSkillsByUser(Util.GetPcn());

        if(userSkills.isEmpty()){
            throw new NotFoundException("There are no skills registered for the specified user");
        }
        return new ResponseEntity<>(userSkills, HttpStatus.OK);
    }

    @GetMapping(path = "/{name}")
    public @ResponseBody ResponseEntity<List<UserSkill>> getAllUsersBySkill(@PathVariable String name){
        List<UserSkill> userSkill = userSkillService.getAllUsersBySkill(name);

        if(userSkill.isEmpty()){
            throw new NotFoundException("There are no users with the specified skill");
        }
        return new ResponseEntity<>(userSkill, HttpStatus.OK);
    }

    @PostMapping(path = "/new")
    public @ResponseBody ResponseEntity<UserSkill> createUserSkill(@RequestBody UserSkill userSkill){
        if (userSkill == null) {
            throw new BadRequestException("The skill was not created - Missing Arguments");
        }

        AppUser user = userService.getUserByPcn(Util.GetPcn());
        userSkill.setAppUser(user);
        UserSkill result = userSkillService.createUserSkill(userSkill);

        if(result == null) {
            throw new InternalServerException("We couldn't create the skill");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path= "/{id}")
    public @ResponseBody ResponseEntity<String> deleteSkill(@PathVariable int id) {
        boolean success = userSkillService.deleteUserSkill(id);

        if(success){
            return new ResponseEntity<>("The skill with id " + id + " has successfully been deleted", HttpStatus.OK);
        }
        throw new InternalServerException("We couldn't delete the skill");
    }
}
