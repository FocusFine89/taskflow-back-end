package nikita.ivanov.taskflow_back_end.users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UsersRegisterDTO(
        @NotEmpty(message = "Il campo del nome non può essere vuoto")
        String name,
        @NotEmpty(message = "Il campo del cognome non può essere vuoto")
        String surname,
        @NotEmpty(message = "Il campo dell'email non può essere vuoto")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email non valida")
        String email,
        @NotEmpty(message = "Il campo della password non può essere vuoto")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$", message = "Password non Valida")
        String password
) {
}
