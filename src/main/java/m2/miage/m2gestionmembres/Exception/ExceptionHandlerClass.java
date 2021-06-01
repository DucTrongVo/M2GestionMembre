package m2.miage.m2gestionmembres.Exception;

import javassist.NotFoundException;
import m2.miage.m2gestionmembres.entities.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> errorException(NotFoundException exception, WebRequest webRequest){
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ExceptionResponse> errorException(ForbiddenException exception, WebRequest webRequest){
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.FORBIDDEN.value(), exception.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(GeneralErreurException.class)
    public ResponseEntity<ExceptionResponse> errorException(GeneralErreurException exception, WebRequest webRequest){
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> errorException(Exception exception, WebRequest webRequest){
        return new ResponseEntity<>(new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
