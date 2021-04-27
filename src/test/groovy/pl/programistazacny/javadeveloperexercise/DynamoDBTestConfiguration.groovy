package pl.programistazacny.javadeveloperexercise

import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.*
import groovy.util.logging.Slf4j
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.testcontainers.containers.GenericContainer
import pl.programistazacny.javadeveloperexercise.adapter.dynamodb.model.PaymentDynamoDB

@TestConfiguration
@Slf4j
class DynamoDBTestConfiguration {

    @Bean
    GenericContainer dynamoDBLocalContainer() {
        return DynamoDBLocalContainerWrapper.startAndGet()
    }

    @Bean
    @Primary
    AmazonDynamoDB amazonDynamoDBTest(AWSCredentialsProvider awsCredentialsProvider) {
        def client = AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        DynamoDBLocalContainerWrapper.address(),
                        Regions.EU_CENTRAL_1.getName())
                )
                .withCredentials(awsCredentialsProvider)
                .build()
        try {
            client.createTable(paymentTableRequest())
        } catch (ResourceInUseException e) {
            log.warn("Table already exists, skipping...", e)
        }
        return client
    }

    private CreateTableRequest paymentTableRequest() {
        new CreateTableRequest()
                .withAttributeDefinitions(new AttributeDefinition("id", ScalarAttributeType.S))
                .withTableName(PaymentDynamoDB.TABLE)
                .withKeySchema(new KeySchemaElement("id", KeyType.HASH))
                .withProvisionedThroughput(new ProvisionedThroughput(1, 1))
    }
}
