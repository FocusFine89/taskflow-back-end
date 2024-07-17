package nikita.ivanov.taskflow_back_end.tasks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import nikita.ivanov.taskflow_back_end.projects.Projects;
import nikita.ivanov.taskflow_back_end.users.Users;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@JsonIgnoreProperties({"project"})
public class Tasks {
    //Attributi
    @Id
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "is_done")
    private boolean isDone;

    //qui vanno le relazioni tra Entità
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projects project;


    //Costruttori
    public Tasks(){}

    public Tasks(String name, LocalDate date, Users user) {
        this.name = name;
        this.date = date;
        this.isDone = false;
        this.user = user;
    }

    public Tasks(String name, LocalDate date, boolean isDone, Users user, Projects project) {
        this.name = name;
        this.date = date;
        this.isDone = false;
        this.user = user;
        this.project = project;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
