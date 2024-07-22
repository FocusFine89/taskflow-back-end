package nikita.ivanov.taskflow_back_end.habits;

import jakarta.persistence.*;
import nikita.ivanov.taskflow_back_end.users.Users;

@Entity
@Table(name = "habits")
public class Habits {
    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name = "days_done")
    private int daysDone;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    //Costruttori
    public Habits(){}

    public Habits(String name, Users user) {
        this.name = name;
        this.daysDone = 0;
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

    public int getDaysDone() {
        return daysDone;
    }

    public void setDaysDone(int daysDone) {
        this.daysDone = daysDone;
    }

    public Users getUser() {
        return user;
    }
}
