package api.m2.commerce.records.sales;

import api.m2.commerce.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record SaleToAdd(
        LocalDate date,
        @NotNull(message = "La moneda es requerida")
        Long currencyId,
        @NotNull(message = "El metodo de pago es requerido")
        PaymentMethod paymentMethod,
        String notes,
        @NotNull(message = "El workspace es requerido")
        Long workspaceId,
        @NotEmpty(message = "Debe incluir al menos un item")
        @Valid
        List<SaleItemToAdd> items
) {
}
