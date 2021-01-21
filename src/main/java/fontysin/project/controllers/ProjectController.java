package fontysin.project.controllers;

import fontysin.project.entities.dto.ProjectDTO;
import fontysin.project.entities.model.Project;
import fontysin.project.entities.model.user.AppUser;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.services.ProjectService;
import fontysin.project.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping(path="/{pcn}")
    public @ResponseBody ResponseEntity<List<Project>> getProjectsByPcn(@PathVariable int pcn) {
        return new ResponseEntity<>(projectService.getProjectsByPcn(pcn), HttpStatus.OK);
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDTO) {
        AppUser user = userService.getUserByPcn(Util.getPcn());
        List<AppUser> users = new ArrayList<>();
        users.add(user);
        projectDTO.setUsers(users);

        return new ResponseEntity<>(projectService.createProject(projectDTO), HttpStatus.CREATED);
    }

    @PostMapping(path="/new/list")
    public @ResponseBody ResponseEntity<List<Project>> createProject(@RequestBody List<ProjectDTO> projectDTOList) {
        AppUser user = userService.getUserByPcn(Util.getPcn());
        List<AppUser> users = new ArrayList<>();
        users.add(user);

        List<Project> projects = new ArrayList<>();
        for(ProjectDTO projectDTO : projectDTOList){
            projectDTO.setUsers(users);
            Project project = projectService.createProject(projectDTO);

            if(project.getProjectId() > 0){
                projects.add(project);
            }
        }

        return new ResponseEntity<>(projects, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody ResponseEntity<String> deleteProject(@PathVariable int id) {
        Project toDelete = projectService.getProjectById(id);
        if(toDelete == null) {
            throw new NotFoundException("We couldn't find a project with that Id");
        }

        if(!projectService.deleteProject(id)) {
            throw new InternalServerException("Something went wrong deleting the project");
        }
        return new ResponseEntity<>("Successfully deleted the project!", HttpStatus.OK);
    }

    @PutMapping(path="/update")
    public @ResponseBody ResponseEntity<Project> updateProject(@RequestBody ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.createProject(projectDTO), HttpStatus.OK);
    }

    @PutMapping(path="/update/list")
    public @ResponseBody ResponseEntity<List<Project>> updateProject(@RequestBody List<ProjectDTO> projectDTOList) {
        List<Project> projects = new ArrayList<>();
        for(ProjectDTO projectDTO : projectDTOList){
            Project project = projectService.createProject(projectDTO);

            if(project.getProjectId() > 0){
                projects.add(project);
            }
        }

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
