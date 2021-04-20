package pl.programistazacny.javadeveloperexercise.adapter.controller.model;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Value
public class PaymentRequest {
    @NotNull
    private final BigDecimal amount;
    @NotNull
    private final String currency;
    @NotNull
    private final UUID userId;
    @NotBlank
    private final String targetAccountNumber;
}
