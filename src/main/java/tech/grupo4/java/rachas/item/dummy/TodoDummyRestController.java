package tech.grupo4.java.rachas.item.dummy;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import tech.grupo4.java.rachas.client.Todo;
import tech.grupo4.java.rachas.client.TodoRestRepository;
import tech.grupo4.java.rachas.item.TodoItemDto;
import tech.grupo4.java.rachas.item.TodoItemRequest;
import tech.grupo4.java.rachas.item.TodoItemService;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo-itens")
@RequiredArgsConstructor
public class TodoDummyRestController {

    private final TodoItemService service;
    private final TodoRestRepository todoRestRepository;

    @PostMapping("/carregar-dummy")
    public Boolean carregarFromDummy() {
        List<Todo> dummyList = this.todoRestRepository.getAll(10L).todos();
        dummyList.stream()
            .map(this::converterTodoEmTodoItem)
            .forEach(this.service::adicionar);
        return true;
    }

    private TodoItemRequest converterTodoEmTodoItem(Todo todo) {
        TodoItemRequest todoItem = new TodoItemRequest();
        todoItem.setTitulo(todo.todo());
        todoItem.setDetalhes(todo.todo());
        return todoItem;
    }

    @PatchMapping("/{uuid}/atribuir-usuario/{username}")
    public TodoItemDto atribuirUsuario(@PathVariable UUID uuid, @PathVariable String username) {
        return this.service.atribuirUsuario(uuid, username);
    }
}
