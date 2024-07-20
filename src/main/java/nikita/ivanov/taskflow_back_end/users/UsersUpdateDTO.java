package nikita.ivanov.taskflow_back_end.users;

import jakarta.validation.constraints.NotEmpty;

public record UsersUpdateDTO(
        @NotEmpty(message = "Non puoi lasciare il campo del Nome vuoto")
        String name,
        @NotEmpty(message = "Non  puoi lasciare il campo del Cognome vuoto")
        String surname
) {
}
