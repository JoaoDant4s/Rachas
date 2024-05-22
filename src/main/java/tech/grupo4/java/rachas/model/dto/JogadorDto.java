package tech.grupo4.java.rachas.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class JogadorDto {
    private String nome;
    private String username;
    private String password;
    private double avaliacao;
    private Role role;
    private boolean active;

    public enum Role {
        BASIC, ADMIN
    }
}
