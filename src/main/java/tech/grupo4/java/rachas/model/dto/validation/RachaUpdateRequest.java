package tech.grupo4.java.rachas.model.dto.validation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RachaUpdateRequest extends RachaRequest {

    private UUID uuid;
    private Boolean disponivel;

    public RachaUpdateRequest(String localizacao, String clima, String data, int quantidadeMaxima, int quantidadeAtual, String esporte, int avaliacaoMinima, String duracao, String donoDaBola) {
        super(localizacao, clima, data, quantidadeMaxima, quantidadeAtual, esporte, avaliacaoMinima, duracao, donoDaBola);
    }
}
