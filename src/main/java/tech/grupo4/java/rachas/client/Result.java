package tech.grupo4.java.rachas.client;

import java.util.List;

public record Result(List<Todo> todos, Long total, Long skip, Long limit) {

}
