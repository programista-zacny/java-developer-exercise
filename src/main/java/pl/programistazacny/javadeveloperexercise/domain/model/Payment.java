package pl.programistazacny.javadeveloperexercise.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Payment {
    private UUID id;
    private BigDecimal amount;
    private String currency;
    private UUID userId;
    private String targetAccountNumber;
}
