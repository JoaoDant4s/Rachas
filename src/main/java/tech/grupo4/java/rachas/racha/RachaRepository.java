package tech.grupo4.java.rachas.racha;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RachaRepository extends JpaRepository<Racha, Integer> {

    List<Racha> findByEsporte(String esporte);

    Optional<Racha> findByUuid(UUID uuid);

    @Modifying
    @Query("update Racha set disponivel = false where uuid = :uuid")
    void marcarIndisponivel(@Param("uuid") UUID uuid);

    @Query("select r from Racha r where r.disponivel = true")
    List<Racha> findByDisponivel(boolean disponivel);

    List<Racha> findByDonoDaBolaContainingIgnoreCase(String username);

    void deleteByUuid(UUID uuid);
}