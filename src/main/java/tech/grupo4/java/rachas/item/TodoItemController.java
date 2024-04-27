package tech.grupo4.java.rachas.item;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo-itens")
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService service;

    @GetMapping
    public List<TodoItemDto> listar() {
        return this.service.listar();
    }

    @GetMapping(value = "consultar", params = "titulo")
    public List<TodoItemDto> consultarPorTitulo(@RequestParam String titulo) {
        return this.service.consultarPorTitulo(titulo);
    }

    @GetMapping("/{uuid}")
    public TodoItemDto buscarPorUuid(@PathVariable UUID uuid) {
        return this.service.buscarPorUuid(uuid);
    }

    @GetMapping("/usuarios/{username}")
    public List<TodoItemDto> buscarPorUsuario(@PathVariable String username) {
        return this.service.buscarPorUsuario(username);
    }

    @PostMapping("/usuarios/{username}")
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItemDto adicionar(@PathVariable String username, @RequestBody TodoItemRequest request) {
        return this.service.adicionar(username, request);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable UUID uuid, @RequestBody TodoItemUpdateRequest request) {
        this.service.atualizar(uuid, request);
    }

    @PatchMapping("/{uuid}/concluido")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void marcarComoConcluido(@PathVariable UUID uuid) {
        this.service.marcarConcluido(uuid);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void excluir(@PathVariable UUID uuid) {
        this.service.excluir(uuid);
    }

}
