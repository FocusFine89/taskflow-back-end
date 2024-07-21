package nikita.ivanov.taskflow_back_end.projects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects, Long> {
    Optional<Projects> findById(long id);

    //Qui andrà messo il current user
    @Query(value = "SELECT p FROM Projects p WHERE p.user.id = :userId")
    List<Projects> findByUserId(@Param("userId") long userId);
}
