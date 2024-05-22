package tech.grupo4.java.rachas.racha;

import java.io.Serializable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @NotBlank(message = "O dono da bola é obrigatório")
    private String donoDaBola;
}
