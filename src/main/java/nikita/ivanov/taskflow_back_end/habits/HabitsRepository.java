package nikita.ivanov.taskflow_back_end.habits;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitsRepository extends JpaRepository<Habits, Long> {
    Optional<Habits> findById(long id);

    //Select Habit per Utente
    @Query("SELECT h FROM Habits h WHERE h.user.id = :userId")
    List<Habits> listHabits(@Param("userId") long userId);
}
