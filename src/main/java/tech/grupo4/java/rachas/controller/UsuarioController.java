package tech.grupo4.java.rachas.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import tech.grupo4.java.rachas.model.UsuarioRequest;
import tech.grupo4.java.rachas.model.dto.UsuarioDto;
import tech.grupo4.java.rachas.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public List<UsuarioDto> listarTodos() {
        return this.service.listarTodos();
    }

    @GetMapping("/{username}")
    public UsuarioDto buscarPorUsername(@PathVariable String username) {
        return this.service.buscarPorUsername(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDto adicionarUsuario(@Valid @RequestBody UsuarioRequest usuario) {
        return this.service.adicionarUsuario(usuario);
    }

    @DeleteMapping("/{username}")
    public void excluir(@PathVariable String username) {
        this.service.excluir(username);
    }

}
