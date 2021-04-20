package pl.programistazacny.javadeveloperexercise.adapter.h2.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class PaymentH2 {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @Column(name = "CURRENCY", nullable = false)
    private String currency;

    @Column(name = "USER_ID", nullable = false)
    @Type(type = "uuid-char")
    private UUID userId;

    @Column(name = "TARGET_ACCOUNT_NUMBER", nullable = false)
    private String targetAccountNumber;
}
