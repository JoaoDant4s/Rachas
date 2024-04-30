package tech.grupo4.java.rachas.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.grupo4.java.rachas.model.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer> {

    Optional<Partida> findByNumero(int numero);

    void deleteByNumero(int numero);

}