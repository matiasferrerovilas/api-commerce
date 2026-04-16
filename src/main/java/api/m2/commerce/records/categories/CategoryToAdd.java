package api.m2.commerce.records.categories;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryToAdd(
        @NotBlank(message = "El nombre es requerido")
        String name,
        String description,
        @NotNull(message = "El workspace es requerido")
        Long workspaceId
) {
}
