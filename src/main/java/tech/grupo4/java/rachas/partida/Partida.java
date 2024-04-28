package tech.grupo4.java.rachas.partida;

import jakarta.persistence.*;
import lombok.*;
import tech.grupo4.java.rachas.racha.Racha;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int numero;
    private String timeA;
    private String timeB;
    private String duracao;
    private String placar;

    @ManyToOne
    @JoinColumn(name = "racha_id")
    private Racha racha;

}
