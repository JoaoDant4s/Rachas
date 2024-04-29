package tech.grupo4.java.rachas.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.grupo4.java.rachas.racha.Racha;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SQLRestriction("active = true")
public class Jogador implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Column(unique = true)
    private String username;
    private String password;
    private double avaliacao = 0.0;
    @Enumerated(EnumType.STRING)
    private Role role = Role.BASIC;

    @ManyToMany(mappedBy = "jogadores")
    private Set<Racha> rachas = new HashSet<>();

    private boolean active = true;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;

    public enum Role {
        BASIC
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

}
