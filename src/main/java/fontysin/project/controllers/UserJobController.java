package fontysin.project.controllers;

import fontysin.project.exceptions.BadRequestException;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.model.user.AppUser;
import fontysin.project.model.user.properties.UserJob;
import fontysin.project.services.UserService;
import fontysin.project.services.user.UserJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class UserJobController {

    private final UserJobService userJobService;
    private final UserService userService;

    public UserJobController(UserJobService userJobService, UserService userService) {
        this.userJobService = userJobService;
        this.userService = userService;
    }

    @GetMapping(path="/{pcn}")
    public @ResponseBody
    ResponseEntity<List<UserJob>> getAllJobsByUser(@PathVariable int pcn){
        List<UserJob> jobs = userJobService.getAllJobsByUser(pcn);
        if (jobs.isEmpty()){
            throw new NotFoundException("The user with that PCN doesn't have any jobs yet");
        } else {
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        }
    }

    @GetMapping(path="/{name}")
    public @ResponseBody ResponseEntity<List<UserJob>> getAllUsersByJob(@PathVariable String name){
        List<UserJob> jobs = userJobService.getAllUsersByJob(name);
        if (jobs.isEmpty()){
            throw new NotFoundException("There are no users with that job yet");
        } else {
            return new ResponseEntity<>(jobs, HttpStatus.OK);
        }
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<UserJob> createJob(@RequestBody UserJob job){
        if (Util.emptyOrNull(new String[]{job.getName(), String.valueOf(Util.getPcn())})) {
            throw new BadRequestException("The job was not created - Missing Arguments");
        }
        AppUser user = userService.getUserByPcn(Util.getPcn());
        job.setAppUser(user);
        UserJob result = userJobService.createUserJob(job);
        if(result == null) {
            throw new InternalServerException("We couldn't create the job");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<Boolean> deleteJob(@PathVariable int id){
        boolean result = userJobService.deleteUserJob(id);
        if (result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        throw new NotFoundException("There are no jobs with that id");
    }
}
