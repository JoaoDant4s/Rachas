package tech.grupo4.java.rachas.racha;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tech.grupo4.java.rachas.model.Jogador;
import tech.grupo4.java.rachas.partida.Partida;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Racha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    private String donoDaBola;
    @Enumerated(EnumType.STRING)
    private PrioridadeEnum prioridade;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "racha_jogador",
            joinColumns = @JoinColumn(name = "racha_id"),
            inverseJoinColumns = @JoinColumn(name = "jogador_id"))
    private Set<Jogador> jogadores = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "racha", orphanRemoval = true)
    private List<Partida> partidas = new ArrayList<>();

    public enum PrioridadeEnum {
        ALTA,
        MEDIA,
        BAIXA
    }

}
