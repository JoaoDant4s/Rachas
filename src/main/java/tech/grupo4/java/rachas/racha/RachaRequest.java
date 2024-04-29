package tech.grupo4.java.rachas.racha;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class RachaRequest implements Serializable {

    private String localizacao;
    private String clima;
    private String data;
    private int quantidadeMaxima;
    private int quantidadeAtual;
    private String esporte;
    private int avaliacaoMinima;
    private String duracao;
    private String donoDaBola;
    private PrioridadeEnum prioridade;
    private List<String> jogadores;
    private List<Partida> partidas;
}