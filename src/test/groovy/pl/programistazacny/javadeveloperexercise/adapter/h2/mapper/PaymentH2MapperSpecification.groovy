package pl.programistazacny.javadeveloperexercise.adapter.h2.mapper

import pl.programistazacny.javadeveloperexercise.BaseSpecification
import pl.programistazacny.javadeveloperexercise.adapter.h2.mapper.PaymentH2Mapper
import pl.programistazacny.javadeveloperexercise.adapter.h2.model.PaymentH2
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto
import spock.lang.Shared

class PaymentH2MapperSpecification extends BaseSpecification {

    @Shared
    PaymentH2Mapper mapper = PaymentH2Mapper.INSTANCE

    def "should map from h2 to domain"() {
        given:
        PaymentH2 h2 = randomPaymentH2()

        when:
        def domain = mapper.h2ToDomain(h2)

        then:
        domain.getId() == h2.getId()
        domain.getAmount() == h2.getAmount()
        domain.getCurrency() == h2.getCurrency()
        domain.getUserId() == h2.getUserId()
        domain.getTargetAccountNumber() == h2.getTargetAccountNumber()
    }

    def "should map from dto to h2"() {
        given:
        PaymentDto dto = randomPaymentDto()

        when:
        def h2 = mapper.dtoToH2(dto)

        then:
        h2.getId() == null
        h2.getAmount() == dto.getAmount()
        h2.getCurrency() == dto.getCurrency()
        h2.getUserId() == dto.getUserId()
        h2.getTargetAccountNumber() == dto.getTargetAccountNumber()
    }
}
