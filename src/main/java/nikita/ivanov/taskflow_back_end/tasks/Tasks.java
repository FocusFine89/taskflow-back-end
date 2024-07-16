package nikita.ivanov.taskflow_back_end.tasks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
