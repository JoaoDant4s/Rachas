package tech.grupo4.java.rachas.racha;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.grupo4.java.rachas.partida.Partida;
import tech.grupo4.java.rachas.racha.Racha.PrioridadeEnum;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RachaUpdateRequest extends RachaRequest {

    private UUID uuid;
    private Boolean disponivel;

    public RachaUpdateRequest(String localizacao, String clima, String data, int quantidadeMaxima, int quantidadeAtual, String esporte, int avaliacaoMinima, String duracao, String donoDaBola, PrioridadeEnum prioridade, List<String> jogadores, List<Partida> partidas) {
        super(localizacao, clima, data, quantidadeMaxima, quantidadeAtual, esporte, avaliacaoMinima, duracao, donoDaBola, prioridade, jogadores, partidas);
    }
}
