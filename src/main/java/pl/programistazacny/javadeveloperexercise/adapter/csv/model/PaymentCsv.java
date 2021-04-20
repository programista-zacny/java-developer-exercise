package pl.programistazacny.javadeveloperexercise.adapter.csv.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PaymentCsv {

    @CsvBindByPosition(position = 0)
    private UUID id;

    @CsvBindByPosition(position = 1)
    private BigDecimal amount;

    @CsvBindByPosition(position = 2)
    private String currency;

    @CsvBindByPosition(position = 3)
    private UUID userId;

    @CsvBindByPosition(position = 4)
    private String targetAccountNumber;

    public void updateFrom(PaymentDto paymentDto) {
        setAmount(paymentDto.getAmount());
        setCurrency(paymentDto.getCurrency());
        setUserId(paymentDto.getUserId());
        setTargetAccountNumber(paymentDto.getTargetAccountNumber());
    }
}
