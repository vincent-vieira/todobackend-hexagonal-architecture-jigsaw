package io.vieira.todos.infrastructure.data;

import io.vieira.todos.Todo;
import io.vieira.todos.TodosRepository;
import io.vieira.todos.infrastructure.data.jpa.TodoEntity;
import io.vieira.todos.infrastructure.data.jpa.TodosJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DelegatingJpaTodosRepository implements TodosRepository {

    private final TodosJpaRepository repository;

    public DelegatingJpaTodosRepository(TodosJpaRepository jpaRepository) {
        this.repository = jpaRepository;
    }

    @Override
    public Todo create(String title, boolean completed) {
        final var order = repository
                .findMaxOrder()
                .map(currentOrder -> currentOrder + 1)
                .orElse(1);

        final var entity = new TodoEntity(null, title, order, completed);
        return toTodo(repository.save(entity));
    }

    @Override
    public boolean existsById(UUID id) {
        return this.repository.existsById(id);
    }

    @Override
    public Optional<Todo> findById(UUID id) {
        return this.repository
                .findById(id)
                .map(this::toTodo);
    }

    @Override
    public Optional<Todo> findByTitle(String title) {
        return this.repository
                .findByTitle(title)
                .map(this::toTodo);
    }

    @Override
    public Todo update(UUID id, String title, boolean completed, int order) {
        return this.toTodo(this.repository.save(new TodoEntity(id, title, order, completed)));
    }

    private Todo toTodo(TodoEntity todoEntity) {
        return new Todo(todoEntity.getId(), todoEntity.getTitle(), todoEntity.getOrder(), todoEntity.isCompleted());
    }

    @Override
    public void deleteById(UUID id) {
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAllByCompleted(boolean completed) {
        this.repository.deleteAllByCompleted(completed);
    }

    @Override
    public List<Todo> findAll() {
        return this.repository
                .findAll()
                .stream()
                .map(this::toTodo)
                .toList();
    }
}
