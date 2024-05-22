package tech.grupo4.java.rachas.partida;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Integer> {

    Optional<Partida> findByNumero(int numero);

    Partida save(Partida partida);

    void deleteByNumero(int numero);

}