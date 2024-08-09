package nikita.ivanov.taskflow_back_end.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import nikita.ivanov.taskflow_back_end.exceptions.UnauthorizedException;
import nikita.ivanov.taskflow_back_end.users.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Users user){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() +1000*60*60*24*7))
                .subject(String.valueOf(user.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token){
        try{
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        }catch(Exception ex){
            throw new UnauthorizedException("Problemi con il token, Effettua di nuovo il login");
        }
    }

    public String idFromToken(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build()
                .parseSignedClaims(token).getPayload().getSubject();
    }
}
