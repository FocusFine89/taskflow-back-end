package nikita.ivanov.taskflow_back_end.projects;

import nikita.ivanov.taskflow_back_end.tasks.Tasks;
import nikita.ivanov.taskflow_back_end.tasks.TasksCreateDTO;
import nikita.ivanov.taskflow_back_end.tasks.TasksRepository;
import nikita.ivanov.taskflow_back_end.tasks.TasksService;
import nikita.ivanov.taskflow_back_end.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsController {
    @Autowired
    ProjectsService projectsService;

    @Autowired
    TasksService tasksService;

    @Autowired
    TasksRepository tasksRepository;

    //Creazione Project
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Projects createProject(@RequestBody @Validated ProjectsCreateDTO project, @AuthenticationPrincipal Users user){
        return this.projectsService.createProject(project, user);
    }

    //Creazione Task per project
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Tasks createTaskForProject(@RequestBody @Validated TasksCreateDTO task, @AuthenticationPrincipal Users user, @PathVariable long id ){
        return this.tasksService.createTaskForProject(task,user,id);
    }


    //Lista di tutti i Project
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Projects> listOfProjects(@AuthenticationPrincipal Users user){
        return this.projectsService.findAllProjects(user);
    }

//    //Lista delle Task per project
//    @GetMapping("/tasks/{id}")
//    public List<Tasks> listOfTasksForProject(@AuthenticationPrincipal Users user, @PathVariable long id){
//        return this.projectsService.findTasksFromProject(user.getId(), id);
//    }
    //Questo metodo sopra non serve più perchè tutte le task escono automaticamente

    //Modifica Project
    @PostMapping("/modifie/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Projects updateProject(@PathVariable long id, @RequestBody ProjectsCreateDTO updatedProject, @AuthenticationPrincipal Users user){
        return this.projectsService.findByIdAndUpdate(id,updatedProject, user);

    }




    //Elimina Project
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable long id, @AuthenticationPrincipal Users user){
        this.projectsService.deleteProject(id, user);
    }


    //TODO Elimina Task del project (c'è gia il metodo nel service di Tasks)

}
