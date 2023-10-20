package io.vieira.todosapi.rest.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record TodoCreationRequest(@JsonProperty("title") @NotBlank String title) {
}
