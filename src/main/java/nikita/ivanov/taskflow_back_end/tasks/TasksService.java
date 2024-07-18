package nikita.ivanov.taskflow_back_end.tasks;

import nikita.ivanov.taskflow_back_end.exceptions.NotFoundException;
import nikita.ivanov.taskflow_back_end.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TasksService {

    @Autowired
    TasksRepository tasksRepository;

    //Creazione Tasks
    public Tasks createTask(TasksCreateDTO task, Users user){
        Tasks newTask = new Tasks(task.name(), task.date(),user);
        return this.tasksRepository.save(newTask);

    }

    //Lista di tutte le Task per current user
    public List<Tasks> findTaskByUserId(UUID id){
        return this.tasksRepository.findTaskByUserId(id);
    }



    //Cerca Task per ID
    public Tasks findTask(UUID id){
        return this.tasksRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }


    //Modifica Task (POST)
    public Tasks postTask(TasksPostRequestDTO updatedTask, UUID idTask){
        Tasks task = this.findTask(idTask);
        task.setName(updatedTask.name());
        task.setDate(updatedTask.date());
        task.setDone(updatedTask.done());
        return this.tasksRepository.save(task);

    }


    //Elimina Task
    public void deleteTask(UUID id){
        Tasks task =  this.findTask(id);
        this.tasksRepository.delete(task);
    }

}
