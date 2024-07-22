package nikita.ivanov.taskflow_back_end.habits;

import jakarta.validation.constraints.NotEmpty;

public record HabitsCreateDTO(@NotEmpty(message = "Il campo del nome non può essere vuoto") String name) {
}
