package api.m2.commerce.records.categories;

import java.time.Instant;

public record CategoryRecord(
        Long id,
        String name,
        String description,
        Boolean isActive,
        Long workspaceId,
        Instant createdAt
) {
}
