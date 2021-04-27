package pl.programistazacny.javadeveloperexercise.adapter.dynamodb


import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.testcontainers.spock.Testcontainers
import pl.programistazacny.javadeveloperexercise.DynamoDBTestConfiguration
import pl.programistazacny.javadeveloperexercise.domain.port.PaymentRepositorySpecification

@TestPropertySource(properties = ["storage.mode=dynamodb", "dynamodb.access-key=accessKey", "dynamodb.secret-key=secretKey"])
@Testcontainers
@Import(DynamoDBTestConfiguration.class)
class PaymentDynamoDBRepositorySpecification extends PaymentRepositorySpecification {

    def "repository should be PaymentDynamoDBRepository"() {
        expect:
        PaymentDynamoDBRepository.class.isInstance(paymentRepository)
    }
}
