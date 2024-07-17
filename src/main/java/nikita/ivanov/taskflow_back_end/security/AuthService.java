package nikita.ivanov.taskflow_back_end.security;

import nikita.ivanov.taskflow_back_end.exceptions.UnauthorizedException;
import nikita.ivanov.taskflow_back_end.users.Users;
import nikita.ivanov.taskflow_back_end.users.UsersLoginDTO;
import nikita.ivanov.taskflow_back_end.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UsersService usersService;

    @Autowired
    JWTTools jwtTools;

    @Autowired
    PasswordEncoder bcrypt;
    public String loginAndCreateToken(UsersLoginDTO payload){
        Users user = this.usersService.findByEmail(payload.email());
        if(bcrypt.matches(payload.password(), user.getPassword())){
            return jwtTools.createToken(user);
        }else{
            throw new UnauthorizedException("Email o Password non corrette, riprova");
        }

    }

}
