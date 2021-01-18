package fontysin.project.services;

import fontysin.project.entities.dto.ProjectDTO;
import fontysin.project.entities.model.Project;
import fontysin.project.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(ProjectDTO project) {
        Project toCreate = new Project(project.getId(), project.getName(), project.getUrl(), project.getUsers());
        return projectRepository.save(toCreate);
    }

    public boolean deleteProject(int id) {
        Optional<Project> found = projectRepository.getByProjectId(id);
        if(found.isEmpty()) {
            return false;
        }
        projectRepository.delete(found.get());
        return true;
    }

    public List<Project> getProjectsByPcn(int pcn) {
        return projectRepository.findProjectByUsers_pcn(pcn);
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id).orElse(null);
    }
}
