package nikita.ivanov.taskflow_back_end.tasks;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record TasksPostRequestDTO(
        @NotEmpty(message = "Non puoi lasciare il campo del nome vuoto")
        String name,
        LocalDate date,
        @NotEmpty(message = "Non puoi lasciare lo stato della Task vuoto")
        boolean done
) {
}
