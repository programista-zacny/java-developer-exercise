package pl.programistazacny.javadeveloperexercise.adapter.dynamodb;

import lombok.RequiredArgsConstructor;
import pl.programistazacny.javadeveloperexercise.adapter.dynamodb.mapper.PaymentDynamoDBMapper;
import pl.programistazacny.javadeveloperexercise.adapter.dynamodb.model.PaymentDynamoDB;
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;
import pl.programistazacny.javadeveloperexercise.domain.port.PaymentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public class PaymentDynamoDBRepository implements PaymentRepository {

    private final PaymentDynamoDBCrudRepository paymentDynamoDBCrudRepository;

    @Override
    public Optional<Payment> findById(UUID id) {
        return paymentDynamoDBCrudRepository.findById(id).map(paymentDynamoDB -> PaymentDynamoDBMapper.INSTANCE.dynamoDBToDomain(paymentDynamoDB));
    }

    @Override
    public List<Payment> findAll() {
        return StreamSupport.stream(paymentDynamoDBCrudRepository.findAll().spliterator(), false)
                .map(paymentDynamoDB -> PaymentDynamoDBMapper.INSTANCE.dynamoDBToDomain(paymentDynamoDB))
                .collect(Collectors.toList());
    }

    @Override
    public Payment create(PaymentDto paymentDto) {
        PaymentDynamoDB paymentDynamoDB = PaymentDynamoDB.builder()
                .amount(paymentDto.getAmount())
                .currency(paymentDto.getCurrency())
                .userId(paymentDto.getUserId())
                .targetAccountNumber(paymentDto.getTargetAccountNumber())
                .build();
        return PaymentDynamoDBMapper.INSTANCE.dynamoDBToDomain(paymentDynamoDBCrudRepository.save(paymentDynamoDB));
    }

    @Override
    public Optional<Payment> update(UUID id, PaymentDto paymentDto) {
        if (paymentDynamoDBCrudRepository.existsById(id)) {
            PaymentDynamoDB paymentDynamoDB = PaymentDynamoDB.builder()
                    .amount(paymentDto.getAmount())
                    .currency(paymentDto.getCurrency())
                    .userId(paymentDto.getUserId())
                    .targetAccountNumber(paymentDto.getTargetAccountNumber())
                    .build();
            paymentDynamoDB.setId(id);
            return Optional.of(PaymentDynamoDBMapper.INSTANCE.dynamoDBToDomain(paymentDynamoDBCrudRepository.save(paymentDynamoDB)));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(UUID id) {
        if (paymentDynamoDBCrudRepository.existsById(id)) {
            paymentDynamoDBCrudRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
