package tech.grupo4.java.rachas.item;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Integer> {

    List<TodoItem> findByTituloContainingIgnoreCase(String titulo);

    Optional<TodoItem> findByUuid(UUID uuid);

    @Modifying
    @Query("update TodoItem set concluido = true where uuid = :uuid")
    void marcarConcluido(@Param("uuid") UUID uuid);

    List<TodoItem> findByUsuarioUsername(String username);

    void deleteByUuid(UUID uuid);
}