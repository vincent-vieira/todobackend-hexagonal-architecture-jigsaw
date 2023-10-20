package io.vieira.todos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodosRepository {

    Todo create(String title, boolean completed);

    boolean existsById(UUID id);

    Optional<Todo> findById(UUID id);

    Optional<Todo> findByTitle(String title);

    Todo update(UUID id, String title, boolean completed, int order);

    void deleteById(UUID id);

    void deleteAllByCompleted(boolean completed);

    List<Todo> findAll();
}
