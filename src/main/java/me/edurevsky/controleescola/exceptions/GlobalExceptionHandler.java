package me.edurevsky.controleescola.exceptions;

import me.edurevsky.controleescola.exceptions.appexceptions.NotImplementedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import me.edurevsky.controleescola.exceptions.models.StandardErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {
        StandardErrorMessage err = new StandardErrorMessage(e);
        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotImplementedException.class)
    public ResponseEntity<Object> handleNotImplementedException(Exception e, WebRequest request) {
        StandardErrorMessage err = new StandardErrorMessage(e);
        return new ResponseEntity<>(err, new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED);
    }
}
