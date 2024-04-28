package tech.grupo4.java.rachas.racha;

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
@RequestMapping("/rachas")
@RequiredArgsConstructor
public class RachaController {

    private final RachaService service;

    @GetMapping
    public List<RachaDto> listar() {
        return this.service.listar();
    }

    @GetMapping(value = "consultar", params = "Dono")
    public List<RachaDto> consultarPorDonoDaBola(@RequestParam String Dono) {
        return this.service.consultarPorDonoDaBola(Dono);
    }

    @GetMapping("/{uuid}")
    public RachaDto buscarPorUuid(@PathVariable UUID uuid) {
        return this.service.buscarPorUuid(uuid);
    }

    @GetMapping("/esportes/{esporte}")
    public List<RachaDto> buscarPorEsporte(@PathVariable String esporte) {
        return this.service.buscarPorEsporte(esporte);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable UUID uuid, @RequestBody RachaUpdateRequest request) {
        this.service.atualizar(uuid, request);
    }

    @PatchMapping("/{uuid}/concluido")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void marcarComoIndisponivel(@PathVariable UUID uuid) {
        this.service.marcarIndisponivel(uuid);
    }

    @PatchMapping("/{uuid}/jogadores/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarJogador(@PathVariable UUID uuid, @PathVariable String username) {
        this.service.atribuirJogador(uuid, username);
    }

    @PatchMapping("/{uuid}/partidas/{numero}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarPartida(@PathVariable UUID uuid, @PathVariable int numero) {
        this.service.atribuirPartida(uuid, numero);
    }

    @Transactional
    @DeleteMapping("/{uuid}")
    public void excluir(@PathVariable UUID uuid) {
        this.service.excluir(uuid);
    }

}
