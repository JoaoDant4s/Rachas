package tech.grupo4.java.rachas.exception;

public class TodoItemNaoEncontradoException extends NaoEncontradoException {

    public TodoItemNaoEncontradoException() {
        super("To-do Item não encontrado");
    }
}
