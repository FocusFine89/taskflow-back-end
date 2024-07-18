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
public interface TasksRepository extends JpaRepository<Tasks, UUID> {
    Optional<Tasks> findById(String id);


    @Query(value = "SELECT t FROM tasks t WHERE t.user.id = :userId")
    List<Tasks> findTaskByUserId(@Param("userId") UUID userId);
}
