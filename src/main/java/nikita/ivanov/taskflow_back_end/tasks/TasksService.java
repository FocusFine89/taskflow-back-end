package nikita.ivanov.taskflow_back_end.tasks;

import nikita.ivanov.taskflow_back_end.exceptions.NotFoundException;
import nikita.ivanov.taskflow_back_end.exceptions.UnauthorizedException;
import nikita.ivanov.taskflow_back_end.projects.Projects;
import nikita.ivanov.taskflow_back_end.projects.ProjectsService;
import nikita.ivanov.taskflow_back_end.users.Users;
import nikita.ivanov.taskflow_back_end.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TasksService {

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    ProjectsService projectsService;

    //Creazione Tasks
    public Tasks createTask(TasksCreateDTO task, Users user){
        Users newUSer = this.usersService.findById(user.getId());
        Tasks newTask = new Tasks(task.name(), task.date(),newUSer);
        System.out.println(newTask);
        System.out.println(newUSer);
        System.out.println("ciao");
        return this.tasksRepository.save(newTask);

    }

    //Creazione Task per Project
    public Tasks createTaskForProject(TasksCreateDTO task, Users user, long projectId){
        Users foundUser = this.usersService.findById(user.getId());
        Projects foundProject = this.projectsService.findById(projectId);
        if(foundUser.getId() == foundProject.getUser().getId() ){
            Tasks newTask = new Tasks(task.name(), task.date(), foundUser, foundProject);
            return this.tasksRepository.save(newTask);
        }else{
            throw new UnauthorizedException("Non hai il Permesso di creare la Task per questo Progetto");
        }



    }

    //Lista di tutte le Task per current user
    public List<Tasks> findTaskByUserId(long id){
        return this.tasksRepository.findTasksByUserIdAndProjectIsNull(id);
    }



    //Cerca Task per ID
    public Tasks findTask(long id, Users user){

        Tasks task = this.tasksRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        if(task.getUser().getId() == user.getId()){
            return task;
        }else{
            throw new UnauthorizedException("Non hai il permesso per accedere a questa Task");
        }
    }


    //Modifica Task (POST)
    public Tasks postTask(TasksPostRequestDTO updatedTask, long idTask, Users user){
        Tasks task = this.tasksRepository.findById(idTask).orElseThrow(()-> new NotFoundException(idTask));
        if(task.getUser().getId() == user.getId()){
            task.setName(updatedTask.name());
            task.setDate(updatedTask.date());
            task.setDone(updatedTask.done());
            return this.tasksRepository.save(task);
        }else{
            throw new UnauthorizedException("Non hai il permesso per modificare questa Task");
        }


    }


    //Elimina Task
    public void deleteTask(long id, Users user){
        Tasks task =  this.tasksRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
        if(task.getUser().getId() == user.getId()){
            this.tasksRepository.delete(task);
        }else{
            throw new UnauthorizedException("Non hai il permesso di eliminare questa Task");
        }

    }

}
