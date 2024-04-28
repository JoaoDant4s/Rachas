package tech.grupo4.java.rachas.exception;

public class UsuarioNaoEncontradoException extends NaoEncontradoException {

    public UsuarioNaoEncontradoException() {
        super("Usuário ou senha são inválidos");
    }
}
