package tech.grupo4.java.rachas.partida;

import jakarta.validation.Valid;
import java.util.*;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/partidas")
@RequiredArgsConstructor
public class PartidaRestController {

    private final PartidaService service;

    @GetMapping
    public List<PartidaDto> listarTodos() {
        return this.service.listarTodos();
    }

    @GetMapping("/{numero}")
    public PartidaDto buscarPorNumero(@PathVariable int numero) {
        return this.service.buscarPorNumero(numero);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PartidaDto adicionarPartida(@Valid @RequestBody PartidaRequest partida) {
        return this.service.adicionarPartida(partida);
    }

    @DeleteMapping("/{numero}")
    public void excluir(@PathVariable int numero) {
        this.service.excluir(numero);
    }

}
