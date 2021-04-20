package pl.programistazacny.javadeveloperexercise.adapter.csv

import org.springframework.test.context.TestPropertySource
import pl.programistazacny.javadeveloperexercise.domain.port.PaymentRepositorySpecification

@TestPropertySource(properties = "storage.mode=csv")
class PaymentCsvRepositorySpecification extends PaymentRepositorySpecification {

    def "repository should be PaymentCsvRepository"() {
        expect:
        PaymentCsvRepository.class.isInstance(paymentRepository)
    }
}
