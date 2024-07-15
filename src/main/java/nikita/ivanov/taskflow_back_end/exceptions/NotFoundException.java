package nikita.ivanov.taskflow_back_end.exceptions;

import org.aspectj.weaver.ast.Not;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("Record con id: "+ id + " non trovato");
    }

    public NotFoundException(String email){
        super("Utente con l'email: " + email + " non trovato");
    }
}
