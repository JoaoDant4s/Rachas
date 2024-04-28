package tech.grupo4.java.rachas.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.grupo4.java.rachas.model.Jogador.Role;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JogadorDto implements Serializable {

    private String nome;
    private String username;
    private String senha;
    private double avaliacao;
    private Role role;
    private boolean active;

}