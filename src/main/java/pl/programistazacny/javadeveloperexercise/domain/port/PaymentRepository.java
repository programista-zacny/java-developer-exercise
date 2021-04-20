package pl.programistazacny.javadeveloperexercise.domain.port;

import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    Optional<Payment> findById(UUID id);

    List<Payment> findAll();

    Payment create(PaymentDto paymentDto);

    Optional<Payment> update(UUID id, PaymentDto paymentDto);

    boolean delete(UUID id);
}
