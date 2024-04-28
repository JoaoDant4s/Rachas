package tech.grupo4.java.rachas.partida;

import java.io.Serializable;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PartidaDto implements Serializable {

    private int numero;
    private String timeA;
    private String timeB;
    private String duracao;
    private String placar;
}