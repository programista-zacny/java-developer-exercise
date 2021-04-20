package pl.programistazacny.javadeveloperexercise


import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = "storage.mode=csv")
class CsvPaymentIntegrationSpecification extends PaymentIntegrationSpecification {

}
