package pl.programistazacny.javadeveloperexercise.domain;

import lombok.RequiredArgsConstructor;
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;
import pl.programistazacny.javadeveloperexercise.domain.port.PaymentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Optional<Payment> get(UUID id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    public Payment add(PaymentDto paymentDto) {
        return paymentRepository.create(paymentDto);
    }

    public Optional<Payment> edit(UUID id, PaymentDto paymentDto) {
        return paymentRepository.update(id, paymentDto);
    }

    public boolean delete(UUID id) {
        return paymentRepository.delete(id);
    }
}
