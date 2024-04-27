package tech.grupo4.java.rachas.item;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import tech.grupo4.java.rachas.exception.TodoItemNaoEncontradoException;
import tech.grupo4.java.rachas.exception.UsuarioNaoEncontradoException;
import tech.grupo4.java.rachas.item.TodoItem.PrioridadeEnum;
import tech.grupo4.java.rachas.model.usuario.UsuarioRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoItemService {

    private final TodoItemRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    public List<TodoItemDto> listar() {
        return this.repository.findAll().stream()
            .map(mapToDto())
            .toList();
    }

    public List<TodoItemDto> consultarPorTitulo(String titulo) {
        return this.repository.findByTituloContainingIgnoreCase(titulo).stream()
            .map(mapToDto())
            .toList();
    }

    private Function<TodoItem, TodoItemDto> mapToDto() {
        return todoItem -> this.modelMapper.map(todoItem, TodoItemDto.class);
    }

    public TodoItemDto buscarPorUuid(UUID uuid) {
        return this.repository.findByUuid(uuid)
            .map(mapToDto())
            .orElseThrow(TodoItemNaoEncontradoException::new);
    }

    public List<TodoItemDto> buscarPorUsuario(String username) {
        return this.repository.findByUsuarioUsername(username).stream()
            .map(mapToDto())
            .toList();
    }

    public TodoItemDto adicionar(TodoItemRequest request) {
        TodoItem todoItem = this.modelMapper.map(request, TodoItem.class);
        todoItem.setUuid(UUID.randomUUID());
        todoItem.setConcluido(false);
        todoItem.setPrioridade(PrioridadeEnum.BAIXA);
        TodoItem novoTodoItem = this.repository.save(todoItem);
        return this.modelMapper.map(novoTodoItem, TodoItemDto.class);
    }

    public TodoItemDto adicionar(String username, TodoItemRequest request) {
        TodoItem todoItem = this.modelMapper.map(request, TodoItem.class);
        this.setUserForItem(username, todoItem);
        TodoItem novoTodoItem = this.repository.save(todoItem);
        return this.modelMapper.map(novoTodoItem, TodoItemDto.class);
    }

    private void setUserForItem(String username, TodoItem item) {
        item.setUsuario(this.usuarioRepository.findByUsername(username)
            .orElseThrow(UsuarioNaoEncontradoException::new));
    }

    public void atualizar(UUID uuid, TodoItemUpdateRequest request) {
        TodoItem todoItem = this.repository.findByUuid(uuid).orElseThrow(TodoItemNaoEncontradoException::new);
        TodoItem itemAtualizado = this.modelMapper.map(request, TodoItem.class);
        itemAtualizado.setId(todoItem.getId());
        itemAtualizado.setUsuario(todoItem.getUsuario());
        this.repository.save(itemAtualizado);
    }

    public void marcarConcluido(UUID uuid) {
        this.repository.marcarConcluido(uuid);
    }

    @Transactional
    public void excluir(UUID uuid) {
        this.repository.deleteByUuid(uuid);
    }

    public TodoItemDto atribuirUsuario(UUID uuid, String username) {
        TodoItem item = this.repository.findByUuid(uuid).orElseThrow(UsuarioNaoEncontradoException::new);
        this.setUserForItem(username, item);
        return this.modelMapper.map(item, TodoItemDto.class);
    }
}