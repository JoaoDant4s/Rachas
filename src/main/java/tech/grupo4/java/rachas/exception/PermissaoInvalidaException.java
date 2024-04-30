package tech.grupo4.java.rachas.exception;

import org.springframework.http.HttpStatus;

public class PermissaoInvalidaException extends AbstractException {

    public PermissaoInvalidaException() {
        super("Permissão inválida");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
