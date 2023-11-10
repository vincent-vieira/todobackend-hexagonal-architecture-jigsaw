package io.vieira.todos.infrastructure.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TodosJpaRepository extends JpaRepository<TodoEntity, UUID> {

    @Query("select max(t.order) from TodoEntity t")
    Optional<Integer> findMaxOrder();

    @Query("from TodoEntity where title = :title")
    Optional<TodoEntity> findByTitle(@Param("title") String title);

    @Modifying
    @Query("delete from TodoEntity where completed = :completed")
    void deleteAllByCompleted(@Param("completed") boolean completed);
}
