package fontysin.project.repositories;

import fontysin.project.entities.model.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    Optional<Project> getByProjectId(int id);
    List<Project> findProjectByUsers_pcn(int pcn);
}
