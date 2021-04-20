package pl.programistazacny.javadeveloperexercise.adapter.controller.model;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class PaymentResponse {
    private UUID id;
    private BigDecimal amount;
    private String currency;
    private UUID userId;
    private String targetAccountNumber;
}
