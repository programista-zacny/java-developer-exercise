package pl.programistazacny.javadeveloperexercise.adapter.csv.mapper

import pl.programistazacny.javadeveloperexercise.BaseSpecification
import pl.programistazacny.javadeveloperexercise.adapter.csv.mapper.PaymentCsvMapper
import pl.programistazacny.javadeveloperexercise.adapter.csv.model.PaymentCsv
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto
import spock.lang.Shared

class PaymentCsvMapperSpecification extends BaseSpecification {

    @Shared
    PaymentCsvMapper mapper = PaymentCsvMapper.INSTANCE

    def "should map from csv to domain"() {
        given:
        PaymentCsv csv = randomPaymentCsv()

        when:
        def domain = mapper.csvToDomain(csv)

        then:
        domain.getId() == csv.getId()
        domain.getAmount() == csv.getAmount()
        domain.getCurrency() == csv.getCurrency()
        domain.getUserId() == csv.getUserId()
        domain.getTargetAccountNumber() == csv.getTargetAccountNumber()
    }

    def "should map from dto to csv"() {
        given:
        PaymentDto dto = randomPaymentDto()

        when:
        def csv = mapper.dtoToCsv(dto)

        then:
        csv.getId() == null
        csv.getAmount() == dto.getAmount()
        csv.getCurrency() == dto.getCurrency()
        csv.getUserId() == dto.getUserId()
        csv.getTargetAccountNumber() == dto.getTargetAccountNumber()
    }
}
