package tech.grupo4.java.rachas.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.grupo4.java.rachas.model.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

    void deleteByUsername(String username);

    Optional<Jogador> findByUsername(String username);
}