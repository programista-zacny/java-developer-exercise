package pl.programistazacny.javadeveloperexercise.adapter.h2

import org.springframework.test.context.TestPropertySource
import pl.programistazacny.javadeveloperexercise.domain.port.PaymentRepositorySpecification

@TestPropertySource(properties = "storage.mode=h2")
class PaymentH2RepositorySpecification extends PaymentRepositorySpecification {

    def "repository should be PaymentH2Repository"() {
        expect:
        PaymentH2Repository.class.isInstance(paymentRepository)
    }
}
