package io.vieira.todosapi.rest.models;

import java.util.UUID;

public record TodoResponse(UUID id, String title, int order, boolean completed, String url) {
}
