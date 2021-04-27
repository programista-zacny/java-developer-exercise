package pl.programistazacny.javadeveloperexercise.adapter.dynamodb.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.programistazacny.javadeveloperexercise.adapter.dynamodb.model.PaymentDynamoDB;
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto;
import pl.programistazacny.javadeveloperexercise.domain.model.Payment;

@Mapper
public interface PaymentDynamoDBMapper {
    PaymentDynamoDBMapper INSTANCE = Mappers.getMapper(PaymentDynamoDBMapper.class);

    Payment dynamoDBToDomain(PaymentDynamoDB paymentDynamoDB);

    @Mapping(target = "id", ignore = true)
    PaymentDynamoDB dtoToDynamoDB(PaymentDto paymentDto);
}
