package pl.programistazacny.javadeveloperexercise.adapter.h2;

import lombok.RequiredArgsConstructor;
import pl.programistazacny.javadeveloperexercise.adapter.h2.mapper.PaymentH2Mapper;
import pl.programistazacny.javadeveloperexercise.adapter.h2.model.PaymentH2;
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;
import pl.programistazacny.javadeveloperexercise.domain.port.PaymentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class PaymentH2Repository implements PaymentRepository {

    private final PaymentH2CrudRepository paymentH2CrudRepository;

    @Override
    public Optional<Payment> findById(UUID id) {
        return paymentH2CrudRepository.findById(id).map(PaymentH2Mapper.INSTANCE::h2ToDomain);
    }

    @Override
    public List<Payment> findAll() {
        return StreamSupport.stream(paymentH2CrudRepository.findAll().spliterator(), false)
                .map(PaymentH2Mapper.INSTANCE::h2ToDomain)
                .toList();
    }

    @Override
    public Payment create(PaymentDto paymentDto) {
        PaymentH2 paymentH2 = PaymentH2.builder()
                .amount(paymentDto.getAmount())
                .currency(paymentDto.getCurrency())
                .userId(paymentDto.getUserId())
                .targetAccountNumber(paymentDto.getTargetAccountNumber())
                .build();
        return PaymentH2Mapper.INSTANCE.h2ToDomain(paymentH2CrudRepository.save(paymentH2));
    }

    @Override
    public Optional<Payment> update(UUID id, PaymentDto paymentDto) {
        if (paymentH2CrudRepository.existsById(id)) {
            PaymentH2 paymentH2 = PaymentH2.builder()
                    .amount(paymentDto.getAmount())
                    .currency(paymentDto.getCurrency())
                    .userId(paymentDto.getUserId())
                    .targetAccountNumber(paymentDto.getTargetAccountNumber())
                    .build();
            paymentH2.setId(id);
            return Optional.of(PaymentH2Mapper.INSTANCE.h2ToDomain(paymentH2CrudRepository.save(paymentH2)));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(UUID id) {
        if (paymentH2CrudRepository.existsById(id)) {
            paymentH2CrudRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
