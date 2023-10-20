package io.vieira.todosapi.rest;

import io.vieira.todos.Todo;
import io.vieira.todos.TodosService;
import io.vieira.todosapi.rest.models.TodoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodosController {

    private final TodosService todosService;

    public TodosController(TodosService todosService) {
        this.todosService = todosService;
    }

    @GetMapping
    public List<TodoResponse> findAll() {
        return this.todosService
                .findAll()
                .stream()
                .map(this::toTodoResponse)
                .toList();
    }

    private TodoResponse toTodoResponse(Todo todo) {
        return new TodoResponse(
                todo.id(),
                todo.title(),
                todo.order(),
                todo.completed(),
                ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .pathSegment("todos", "{id}")
                        .build(todo.id())
                        .toString()
        );
    }
}
