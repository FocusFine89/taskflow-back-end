package nikita.ivanov.taskflow_back_end.tasks;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record TasksCreateDTO(
        @NotEmpty(message = "Il campo del titolo non può essere vuoto")
        String name,
        LocalDate date
) {
}
