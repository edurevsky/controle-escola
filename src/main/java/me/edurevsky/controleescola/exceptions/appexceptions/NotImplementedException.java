package me.edurevsky.controleescola.exceptions.appexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class NotImplementedException extends RuntimeException {

    private final static String STANDARD_MESSAGE = "This resource is not available";

    public NotImplementedException() {
        super(STANDARD_MESSAGE);
    }

    public NotImplementedException(String message) {
        super(message);
    }
}
