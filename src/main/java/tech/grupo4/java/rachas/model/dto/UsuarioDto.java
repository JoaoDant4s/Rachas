package tech.grupo4.java.rachas.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.grupo4.java.rachas.model.Usuario.Role;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDto implements Serializable {

    private String nome;
    private Double avaliacao;
    private String username;
    private String password;
    private Role role;
    private boolean active;

}