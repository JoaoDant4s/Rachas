package tech.grupo4.java.rachas.exception;

public class JogadorNaoEncontradoException extends NaoEncontradoException {

    public JogadorNaoEncontradoException() {
        super("Usuário ou senha são inválidos");
    }
}
