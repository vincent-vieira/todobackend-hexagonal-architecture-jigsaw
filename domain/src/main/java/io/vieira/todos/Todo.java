package io.vieira.todos;

import java.util.UUID;

public record Todo(UUID id, String title, int order, boolean completed) {
}
