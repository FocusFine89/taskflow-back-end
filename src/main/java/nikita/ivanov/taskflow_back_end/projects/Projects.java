package nikita.ivanov.taskflow_back_end.projects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import nikita.ivanov.taskflow_back_end.tasks.Tasks;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects")
public class Projects {
    //Attributi
    @Id
    private UUID id;
    private String name;

    //qui vanno le relazioni tra entità
    @OneToMany(mappedBy = "project")
    private List<Tasks> tasksList = new ArrayList<>();

    //Costruttori
    public Projects(){}

    public Projects(String name) {
        this.name = name;
    }

    //Metodi


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tasks> getTasksList() {
        return tasksList;
    }

    public void setTasksList(List<Tasks> tasksList) {
        this.tasksList = tasksList;
    }

    public void addTaskToList(Tasks task){
        this.tasksList.add(task);
    }
}
