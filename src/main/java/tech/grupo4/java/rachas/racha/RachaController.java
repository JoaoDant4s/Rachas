package tech.grupo4.java.rachas.racha;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public RachaDto adicionar(@RequestBody RachaRequest request) {
        return this.service.adicionar(request);
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
    @DeleteMapping("/{uuid}/{username}")
    public void excluir(@PathVariable UUID uuid, @PathVariable String username) {
        this.service.excluir(uuid, username);
    }

}
