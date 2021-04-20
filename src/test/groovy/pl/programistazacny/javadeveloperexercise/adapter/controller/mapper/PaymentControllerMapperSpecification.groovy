package pl.programistazacny.javadeveloperexercise.adapter.controller.mapper

import pl.programistazacny.javadeveloperexercise.BaseSpecification
import pl.programistazacny.javadeveloperexercise.adapter.controller.mapper.PaymentControllerMapper
import pl.programistazacny.javadeveloperexercise.adapter.controller.model.PaymentRequest
import pl.programistazacny.javadeveloperexercise.domain.model.Payment
import spock.lang.Shared

class PaymentControllerMapperSpecification extends BaseSpecification {

    @Shared
    PaymentControllerMapper mapper = PaymentControllerMapper.INSTANCE

    def "should map from request to dto"() {
        given:
        PaymentRequest request = randomPaymentRequest()

        when:
        def dto = mapper.requestToDto(request)

        then:
        dto.getAmount() == request.getAmount()
        dto.getCurrency() == request.getCurrency()
        dto.getUserId() == request.getUserId()
        dto.getTargetAccountNumber() == request.getTargetAccountNumber()
    }

    def "should map from domain to response"() {
        given:
        Payment domain = randomPayment()

        when:
        def response = mapper.domainToResponse(domain)

        then:
        response.getId() == domain.getId()
        response.getAmount() == domain.getAmount()
        response.getCurrency() == domain.getCurrency()
        response.getUserId() == domain.getUserId()
        response.getTargetAccountNumber() == domain.getTargetAccountNumber()
    }
}
