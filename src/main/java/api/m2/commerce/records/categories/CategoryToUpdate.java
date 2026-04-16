package api.m2.commerce.records.categories;

public record CategoryToUpdate(
        String name,
        String description,
        Boolean isActive
) {
}
