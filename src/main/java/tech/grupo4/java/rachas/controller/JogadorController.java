package tech.grupo4.java.rachas.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import tech.grupo4.java.rachas.model.dto.JogadorDto;
import tech.grupo4.java.rachas.model.dto.validation.JogadorRequest;
import tech.grupo4.java.rachas.service.JogadorService;

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
