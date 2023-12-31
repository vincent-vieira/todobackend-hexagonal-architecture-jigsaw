package io.vieira.todos.rest;

import io.vieira.todos.Todo;
import io.vieira.todos.TodosService;
import io.vieira.todos.rest.models.TodoCreationRequest;
import io.vieira.todos.rest.models.TodoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> findById(@PathVariable("id") UUID id) {
        return ResponseEntity.of(this.todosService
                .findById(id)
                .map(this::toTodoResponse)
        );
    }

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody @Valid TodoCreationRequest creationRequest) {
        final var createdTodo = this.todosService.create(creationRequest.title(), false);
        return ResponseEntity
                .created(buildUri(createdTodo))
                .body(this.toTodoResponse(createdTodo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") UUID id) {
        this.todosService.deleteById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAll(@RequestParam(name = "completed") boolean completed) {
        this.todosService.deleteAllByCompleted(completed);
    }

    private TodoResponse toTodoResponse(Todo todo) {
        return new TodoResponse(
                todo.id(),
                todo.title(),
                todo.order(),
                todo.completed(),
                buildUri(todo).toString()
        );
    }

    private static URI buildUri(Todo todo) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .pathSegment("todos", "{id}")
                .build(todo.id());
    }
}
