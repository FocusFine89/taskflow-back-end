package nikita.ivanov.taskflow_back_end.tasks;

import nikita.ivanov.taskflow_back_end.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.config.Task;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    @Autowired
    TasksService tasksService;

    //creazione Task
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Tasks createTask(@RequestBody @Validated TasksCreateDTO newTask, @AuthenticationPrincipal Users currentUser){
        return this.tasksService.createTask(newTask,currentUser);
    }

    //Lista di Task per utente
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tasks> getAllTasks(@AuthenticationPrincipal Users currentUser){
        return this.tasksService.findTaskByUserId(currentUser.getId());
    }

    //Cerca Task tramite ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tasks findTaskById(@PathVariable long id, @AuthenticationPrincipal Users user){
        return this.tasksService.findTask(id, user);
    }

    //Modifica Task
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tasks upddateTask(@RequestBody TasksPostRequestDTO updatedTask, @PathVariable long id, @AuthenticationPrincipal Users currentUser){
        return this.tasksService.postTask(updatedTask, id, currentUser);
    }

    //Elimina Task
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable long id, @AuthenticationPrincipal Users user){
        this.tasksService.deleteTask(id, user);
    }

}
