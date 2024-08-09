package nikita.ivanov.taskflow_back_end.projects;

import nikita.ivanov.taskflow_back_end.exceptions.NotFoundException;
import nikita.ivanov.taskflow_back_end.exceptions.UnauthorizedException;
import nikita.ivanov.taskflow_back_end.tasks.Tasks;
import nikita.ivanov.taskflow_back_end.tasks.TasksCreateDTO;
import nikita.ivanov.taskflow_back_end.tasks.TasksRepository;
import nikita.ivanov.taskflow_back_end.tasks.TasksService;
import nikita.ivanov.taskflow_back_end.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    ProjectsRepository projectsRepository;

    @Autowired
    TasksRepository tasksRepository;


    //Creazione del Project
    public Projects createProject(ProjectsCreateDTO newProject, Users user){
        Projects project = new Projects(newProject.name(), user);
        return this.projectsRepository.save(project);
    }


    //Project per ID
    public Projects findById(long id){
        Projects project = this.projectsRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        return project;
    }


    //Lista di tutti i Project
    public List<Projects> findAllProjects(Users user){
        return this.projectsRepository.findByUserId(user.getId());
    }


    //Modifica Project
    public Projects findByIdAndUpdate(long id, ProjectsCreateDTO updatedProject, Users user){
        Projects project = this.findById(id);
        if(user.getId() == project.getUser().getId()){
            project.setName(updatedProject.name());
            return this.projectsRepository.save(project);
        }else{
            throw new UnauthorizedException("Non hai il Permesso per modificare questo Progetto");
        }


    }


    //Eliminazione Project
    public void deleteProject(long id, Users user){
        Projects project = this.findById(id);
        if(user.getId() == project.getUser().getId()){
            this.projectsRepository.delete(project);
        }else{
            throw new UnauthorizedException("Non hai il Permesso per eliminare questo Progetto");
        }


    }

    //Lista di tutte le Task per Project
    public List<Tasks> findTasksFromProject(long userId, long projectId){
        return this.tasksRepository.findTasksByUserIdAndProject(userId,projectId);
    }

}
