package tech.grupo4.java.rachas.model.dto.validation;

import java.io.Serializable;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartidaRequest implements Serializable {

    private int numero;
    private String timeA;
    private String timeB;
    private String duracao;
    private String placar;
}