package nikita.ivanov.taskflow_back_end.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException{
    private List<ObjectError> errorList;

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(List<ObjectError> errorList){
        super("Ci sono stati errori nella validazione del payload");
        this.errorList = errorList;
    }

    public List<ObjectError> getErrorList() {
        return errorList;
    }
}
