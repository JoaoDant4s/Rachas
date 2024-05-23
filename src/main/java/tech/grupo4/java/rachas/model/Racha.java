package tech.grupo4.java.rachas.model;

import jakarta.persistence.*;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "racha_jogador",
            joinColumns = @JoinColumn(name = "racha_id"),
            inverseJoinColumns = @JoinColumn(name = "jogador_id"))
    private Set<Jogador> jogadores = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "racha", orphanRemoval = true)
    private List<Partida> partidas = new ArrayList<>();
}
