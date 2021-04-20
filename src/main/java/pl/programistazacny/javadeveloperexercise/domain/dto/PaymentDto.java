package pl.programistazacny.javadeveloperexercise.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentDto {
    private BigDecimal amount;
    private String currency;
    private UUID userId;
    private String targetAccountNumber;
}
