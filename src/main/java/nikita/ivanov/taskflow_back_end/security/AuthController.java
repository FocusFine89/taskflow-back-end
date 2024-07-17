package nikita.ivanov.taskflow_back_end.security;

import nikita.ivanov.taskflow_back_end.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    UsersService usersService;

    //Registrazione Utente
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Users registerUser(@RequestBody @Validated UsersRegisterDTO newUser){
        return usersService.createUser(newUser);
    }


    //Login Utente
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UsersLoginResponseDTO login(@RequestBody @Validated UsersLoginDTO payload){
        return new UsersLoginResponseDTO(authService.loginAndCreateToken(payload));
    }

}
