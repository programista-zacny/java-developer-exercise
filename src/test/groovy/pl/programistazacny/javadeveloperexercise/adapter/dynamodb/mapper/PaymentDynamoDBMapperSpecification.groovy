package pl.programistazacny.javadeveloperexercise.adapter.dynamodb.mapper

import pl.programistazacny.javadeveloperexercise.BaseSpecification
import pl.programistazacny.javadeveloperexercise.adapter.dynamodb.model.PaymentDynamoDB
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto
import spock.lang.Shared

class PaymentDynamoDBMapperSpecification extends BaseSpecification {

    @Shared
    PaymentDynamoDBMapper mapper = PaymentDynamoDBMapper.INSTANCE

    def "should map from dynamoDB to domain"() {
        given:
        PaymentDynamoDB dynamoDB = randomPaymentDynamoDB()

        when:
        def domain = mapper.dynamoDBToDomain(dynamoDB)

        then:
        domain.getId() == dynamoDB.getId()
        domain.getAmount() == dynamoDB.getAmount()
        domain.getCurrency() == dynamoDB.getCurrency()
        domain.getUserId() == dynamoDB.getUserId()
        domain.getTargetAccountNumber() == dynamoDB.getTargetAccountNumber()
    }

    def "should map from dto to dynamoDB"() {
        given:
        PaymentDto dto = randomPaymentDto()

        when:
        def dynamoDB = mapper.dtoToDynamoDB(dto)

        then:
        dynamoDB.getId() == null
        dynamoDB.getAmount() == dto.getAmount()
        dynamoDB.getCurrency() == dto.getCurrency()
        dynamoDB.getUserId() == dto.getUserId()
        dynamoDB.getTargetAccountNumber() == dto.getTargetAccountNumber()
    }
}
