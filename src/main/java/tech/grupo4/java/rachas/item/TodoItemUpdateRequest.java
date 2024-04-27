package tech.grupo4.java.rachas.item;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.grupo4.java.rachas.item.TodoItem.PrioridadeEnum;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TodoItemUpdateRequest extends TodoItemRequest {

    private UUID uuid;
    private Boolean concluido;

    public TodoItemUpdateRequest(String titulo, String detalhes, LocalDateTime data, PrioridadeEnum prioridade) {
        super(titulo, detalhes, data, prioridade);
    }
}
