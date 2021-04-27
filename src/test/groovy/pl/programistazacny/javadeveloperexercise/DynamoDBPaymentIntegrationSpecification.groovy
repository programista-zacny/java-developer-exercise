package pl.programistazacny.javadeveloperexercise

import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource
import org.testcontainers.spock.Testcontainers

@TestPropertySource(properties = ["storage.mode=dynamodb", "dynamodb.access-key=accessKey", "dynamodb.secret-key=secretKey"])
@Testcontainers
@Import(DynamoDBTestConfiguration.class)
class DynamoDBPaymentIntegrationSpecification extends PaymentIntegrationSpecification {

}
