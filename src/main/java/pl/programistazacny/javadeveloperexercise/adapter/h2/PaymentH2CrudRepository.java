package pl.programistazacny.javadeveloperexercise.adapter.h2;

import org.springframework.data.repository.CrudRepository;
import pl.programistazacny.javadeveloperexercise.adapter.h2.model.PaymentH2;

import java.util.UUID;

public interface PaymentH2CrudRepository extends CrudRepository<PaymentH2, UUID> {
}
