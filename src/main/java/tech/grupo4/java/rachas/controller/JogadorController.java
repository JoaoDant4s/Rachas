package tech.grupo4.java.rachas.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.grupo4.java.rachas.model.JogadorRequest;
import tech.grupo4.java.rachas.model.dto.JogadorDto;
import tech.grupo4.java.rachas.service.JogadorService;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
@RequiredArgsConstructor
public class JogadorController {

    private final JogadorService service;

    @GetMapping
    public List<JogadorDto> listarTodos() {
        return this.service.listarTodos();
    }

    @GetMapping("/{username}")
    public JogadorDto buscarPorLogin(@PathVariable String username) {
        return this.service.buscarPorLogin(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JogadorDto adicionarJogador(@Valid @RequestBody JogadorRequest jogador) {
        return this.service.adicionarJogador(jogador);
    }

    @DeleteMapping("/{username}")
    public void excluir(@PathVariable String username) {
        this.service.excluir(username);
    }
}
