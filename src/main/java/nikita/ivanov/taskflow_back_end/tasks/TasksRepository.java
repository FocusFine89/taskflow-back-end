package nikita.ivanov.taskflow_back_end.tasks;

import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
    Optional<Tasks> findById(long id);

    @Query("SELECT t FROM Tasks t WHERE t.user.id = :userId")
    List<Tasks> findTaskByUserId(@Param("userId") long userId);

    @Query("SELECT t FROM Tasks t WHERE t.user.id = :userId AND t.project IS NULL")
    List<Tasks> findTasksByUserIdAndProjectIsNull(@Param("userId") Long userId);
}
