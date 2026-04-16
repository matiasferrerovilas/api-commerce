package api.m2.commerce.records.users;

import java.time.Instant;

public record UserRecord(
        Long id,
        String email,
        String givenName,
        String familyName,
        Instant createdAt
) {
}
