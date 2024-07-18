package nikita.ivanov.taskflow_back_end.tasks;

import nikita.ivanov.taskflow_back_end.exceptions.NotFoundException;
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

    //Creazione Tasks
    public Tasks createTask(TasksCreateDTO task, Users user){
        System.out.println("qwerty");
        Users newUSer = this.usersService.findById(user.getId());
        Tasks newTask = new Tasks(task.name(), task.date(),newUSer);
        System.out.println(newTask);
        System.out.println(newUSer);
        System.out.println("ciao");
        return this.tasksRepository.save(newTask);

    }

    //Lista di tutte le Task per current user
    public List<Tasks> findTaskByUserId(long id){
        return this.tasksRepository.findTaskByUserId(id);
    }



    //Cerca Task per ID
    public Tasks findTask(long id){
        return this.tasksRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    //Modifica Task (POST)
    public Tasks postTask(TasksPostRequestDTO updatedTask, long idTask){
        Tasks task = this.findTask(idTask);
        task.setName(updatedTask.name());
        task.setDate(updatedTask.date());
        task.setDone(updatedTask.done());
        return this.tasksRepository.save(task);

    }


    //Elimina Task
    public void deleteTask(long id){
        Tasks task =  this.findTask(id);
        this.tasksRepository.delete(task);
    }

}
