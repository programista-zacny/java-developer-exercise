package pl.programistazacny.javadeveloperexercise.adapter.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import pl.programistazacny.javadeveloperexercise.adapter.dynamodb.model.PaymentDynamoDB;

import java.util.UUID;

@EnableScan
public interface PaymentDynamoDBCrudRepository extends CrudRepository<PaymentDynamoDB, UUID> {
}
