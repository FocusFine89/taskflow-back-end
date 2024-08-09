package nikita.ivanov.taskflow_back_end.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    //BadRequest Handler
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleBadRequest(BadRequestException ex){
        if(ex.getErrorList()!=null){
            String message = ex.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            return new ErrorsDTO(message);
        }else{
            return new ErrorsDTO(ex.getMessage());
        }
    }

    //NotFound Handler
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO handleNotFound(NotFoundException ex){
        return new ErrorsDTO(ex.getMessage());
    }

    //InternaleError Handler
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handleInternalError(Exception ex){
        ex.printStackTrace();
        return new ErrorsDTO("Problema lato Server, risolveremo il prima possibile.");
    }

    //UnauthorizedError Handler
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorsDTO handleUnauthorizedError(UnauthorizedException ex){
        return new ErrorsDTO(ex.getMessage());
    }
}
