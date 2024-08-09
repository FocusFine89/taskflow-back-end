package nikita.ivanov.taskflow_back_end.projects;

import jakarta.persistence.*;
import nikita.ivanov.taskflow_back_end.tasks.Tasks;
import nikita.ivanov.taskflow_back_end.users.Users;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Projects {
    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    //qui vanno le relazioni tra entità
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tasks> tasksList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    //Costruttori
    public Projects(){}

    public Projects(String name, Users user) {
        this.name = name;
        this.user = user;
    }

    //Metodi


    public long getId() {
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

    public Users getUser() {
        return user;
    }

}
