package io.vieira.todos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.requireNonNullElse;

public class RepositoryBasedTodosService implements TodosService {

    private final TodosRepository todosRepository;

    public RepositoryBasedTodosService(TodosRepository todosRepository) {
        this.todosRepository = todosRepository;
    }

    @Override
    public Todo create(String title, boolean completed) {
        return this.todosRepository.create(title, completed);
    }

    @Override
    public Optional<Todo> findById(UUID id) {
        return this.todosRepository.findById(id);
    }

    @Override
    public Optional<Todo> update(UUID id, String title, Boolean completed, Integer order) {
        return this.todosRepository
                .findById(id)
                .map(originalTodo -> new Todo(
                        id,
                        requireNonNullElse(title, originalTodo.title()),
                        requireNonNullElse(order, originalTodo.order()),
                        requireNonNullElse(completed, originalTodo.completed())
                ))
                .map(updatedTodo -> this.todosRepository.update(id, updatedTodo.title(), updatedTodo.completed(), updatedTodo.order()));
    }

    @Override
    public void deleteById(UUID id) {
        this.todosRepository.deleteById(id);
    }

    @Override
    public void deleteAllByCompleted(boolean completed) {
        this.todosRepository.deleteAllByCompleted(completed);
    }

    @Override
    public List<Todo> findAll() {
        return this.todosRepository.findAll();
    }
}
