package io.vieira.todos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodosService {

    Todo create(String title, boolean completed);

    Optional<Todo> findById(UUID id);

    Optional<Todo> update(UUID id, String title, Boolean completed, Integer order);

    void removeById(UUID id);

    void removeAllByCompleted(boolean completed);

    List<Todo> findAll();
}
