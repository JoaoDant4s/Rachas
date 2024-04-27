package tech.grupo4.java.rachas.exception;

import org.springframework.http.HttpStatus;

public abstract class AbstractException extends RuntimeException {

    protected AbstractException(String message) {
        super(message);
    }

    public abstract HttpStatus getStatus();

}
