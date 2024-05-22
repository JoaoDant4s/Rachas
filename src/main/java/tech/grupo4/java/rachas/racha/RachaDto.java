package tech.grupo4.java.rachas.racha;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.grupo4.java.rachas.model.dto.JogadorDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RachaDto implements Serializable {

    private UUID uuid;
    private String localizacao;
    private String clima;
    private String data;
    private int quantidadeMaxima;
    private int quantidadeAtual;
    private boolean disponivel;
    private String esporte;
    private int avaliacaoMinima;
    private String duracao;
    @NotNull
    private String donoDaBola;
    private List<String> jogadores = new ArrayList<>();
    private List<Integer> partidas = new ArrayList<>();
}