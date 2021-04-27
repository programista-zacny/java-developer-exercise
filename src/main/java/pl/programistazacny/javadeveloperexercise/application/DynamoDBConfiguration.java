package pl.programistazacny.javadeveloperexercise.application;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import lombok.Data;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import pl.programistazacny.javadeveloperexercise.adapter.dynamodb.PaymentDynamoDBCrudRepository;
import pl.programistazacny.javadeveloperexercise.adapter.dynamodb.PaymentDynamoDBRepository;

import javax.validation.constraints.NotBlank;

@Configuration
@ConditionalOnProperty(prefix = "storage", name = "mode", havingValue = "dynamodb")
@EnableDynamoDBRepositories(basePackageClasses = PaymentDynamoDBCrudRepository.class)
class DynamoDBConfiguration {

    @Bean
    DynamoDBProperties dynamoDBProperties() {
        return new DynamoDBProperties();
    }

    @Bean
    AWSCredentials awsCredentials(DynamoDBProperties dynamoDBProperties) {
        return new BasicAWSCredentials(dynamoDBProperties.getAccessKey(), dynamoDBProperties.getSecretKey());
    }

    @Bean
    AWSCredentialsProvider amazonAWSCredentialsProvider(AWSCredentials awsCredentials) {
        return new AWSStaticCredentialsProvider(awsCredentials);
    }

    @Bean
    AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider awsCredentialsProvider) {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withCredentials(awsCredentialsProvider)
                .withRegion(Regions.EU_CENTRAL_1)
                .build();
    }

    @Bean
    PaymentDynamoDBRepository paymentDynamoDBRepository(PaymentDynamoDBCrudRepository paymentDynamoDBCrudRepository) {
        return new PaymentDynamoDBRepository(paymentDynamoDBCrudRepository);
    }

    @ConfigurationProperties(prefix = "dynamodb")
    @Validated
    @Data
    static class DynamoDBProperties {

        @NotBlank
        private String accessKey;

        @NotBlank
        private String secretKey;

    }
}
