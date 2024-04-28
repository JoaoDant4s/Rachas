//package tech.grupo4.java.rachas.racha.dummy;
//
//import java.util.List;
//import java.util.UUID;
//import lombok.RequiredArgsConstructor;
//import tech.grupo4.java.rachas.client.Todo;
//import tech.grupo4.java.rachas.client.TodoRestRepository;
//import tech.grupo4.java.rachas.racha.RachaDto;
//import tech.grupo4.java.rachas.racha.RachaRequest;
//import tech.grupo4.java.rachas.racha.RachaService;
//
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/todo-itens")
//@RequiredArgsConstructor
//public class TodoDummyRestController {
//
//    private final RachaService service;
//    private final TodoRestRepository todoRestRepository;
//
//    @PostMapping("/carregar-dummy")
//    public Boolean carregarFromDummy() {
//        List<Todo> dummyList = this.todoRestRepository.getAll(10L).todos();
//        dummyList.stream()
//            .map(this::converterTodoEmRacha)
//            .forEach(this.service::adicionar);
//        return true;
//    }
//
//    private RachaRequest converterTodoEmRacha(Todo todo) {
//        RachaRequest todoItem = new RachaRequest();
//        todoItem.setTitulo(todo.todo());
//        todoItem.setDetalhes(todo.todo());
//        return todoItem;
//    }
//
//    @PatchMapping("/{uuid}/atribuir-jogador/{username}")
//    public RachaDto atribuirJogador(@PathVariable UUID uuid, @PathVariable String username) {
//        return this.service.atribuirJogador(uuid, username);
//    }
//}
