package io.vieira.todos.infrastructure.data.jpa;

import jakarta.persistence.*;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "todos")
public class TodoEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    @Column(name = "slot")
    private int order;

    private boolean completed;

    @PersistenceCreator
    public TodoEntity(UUID id, String title, int order, boolean completed) {
        this.id = id;
        this.title = title;
        this.order = order;
        this.completed = completed;
    }

    protected TodoEntity() {}

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getOrder() {
        return order;
    }

    public boolean isCompleted() {
        return completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoEntity todo = (TodoEntity) o;
        return order == todo.order && completed == todo.completed && Objects.equals(id, todo.id) && Objects.equals(title, todo.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, order, completed);
    }
}
