package nikita.ivanov.taskflow_back_end.tasks;

import jakarta.persistence.*;
import nikita.ivanov.taskflow_back_end.users.Users;
import org.apache.catalina.User;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tasks")
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

    //Costruttori
    public Tasks(){}

    public Tasks(String name, LocalDate date) {
        this.name = name;
        this.date = date;
        this.isDone = false;
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
