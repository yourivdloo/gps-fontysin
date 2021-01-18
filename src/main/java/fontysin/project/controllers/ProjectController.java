package fontysin.project.controllers;

import fontysin.project.entities.dto.ProjectDTO;
import fontysin.project.entities.model.Project;
import fontysin.project.exceptions.InternalServerException;
import fontysin.project.exceptions.NotFoundException;
import fontysin.project.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/project")
public class ProjectController {
    final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(path="/{pcn}")
    public @ResponseBody ResponseEntity<List<Project>> getProjectsByPcn(@PathVariable int pcn) {
        return new ResponseEntity<>(projectService.getProjectsByPcn(pcn), HttpStatus.OK);
    }

    @PostMapping(path="/new")
    public @ResponseBody ResponseEntity<Project> createProject(@RequestBody ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.createProject(projectDTO), HttpStatus.CREATED);
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
}
