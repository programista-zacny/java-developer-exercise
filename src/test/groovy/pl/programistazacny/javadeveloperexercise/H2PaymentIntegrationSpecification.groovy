package pl.programistazacny.javadeveloperexercise


import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = "storage.mode=h2")
class H2PaymentIntegrationSpecification extends PaymentIntegrationSpecification {

}
