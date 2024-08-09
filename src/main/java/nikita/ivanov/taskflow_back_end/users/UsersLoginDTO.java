package nikita.ivanov.taskflow_back_end.users;

import jakarta.validation.constraints.NotEmpty;

public record UsersLoginDTO(
        @NotEmpty(message = "Il campo dell'email non può essere vuoto")
        String email,
        @NotEmpty(message = "Il campo della password non può essere vuoto")
        String password
) {
}
