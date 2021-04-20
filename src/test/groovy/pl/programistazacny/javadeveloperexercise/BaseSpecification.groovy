package pl.programistazacny.javadeveloperexercise

import pl.programistazacny.javadeveloperexercise.adapter.controller.model.PaymentRequest
import pl.programistazacny.javadeveloperexercise.adapter.csv.model.PaymentCsv
import pl.programistazacny.javadeveloperexercise.adapter.h2.model.PaymentH2
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto
import pl.programistazacny.javadeveloperexercise.domain.model.Payment
import spock.lang.Specification

abstract class BaseSpecification extends Specification {

    protected PaymentRequest randomPaymentRequest() {
        return new PaymentRequest(
                new BigDecimal(BigInteger.valueOf(new Random().nextInt(10001)), 2),
                "PLN",
                UUID.randomUUID(),
                "PL32109024025139464873117912"
        )
    }

    protected PaymentRequest emptyPaymentRequest() {
        return new PaymentRequest(null, null, null, null)
    }

    protected Payment randomPayment() {
        Payment payment = new Payment()
        payment.setId(UUID.randomUUID())
        payment.setAmount(new BigDecimal(BigInteger.valueOf(new Random().nextInt(10001)), 2))
        payment.setCurrency("PLN")
        payment.setUserId(UUID.randomUUID())
        payment.setTargetAccountNumber("PL32109024025139464873117912")
        return payment
    }

    protected PaymentCsv randomPaymentCsv() {
        PaymentCsv paymentCsv = new PaymentCsv()
        paymentCsv.setId(UUID.randomUUID())
        paymentCsv.setAmount(new BigDecimal(BigInteger.valueOf(new Random().nextInt(10001)), 2))
        paymentCsv.setCurrency("PLN")
        paymentCsv.setUserId(UUID.randomUUID())
        paymentCsv.setTargetAccountNumber("PL32109024025139464873117912")
        return paymentCsv
    }

    protected PaymentDto randomPaymentDto() {
        PaymentDto paymentDto = new PaymentDto()
        paymentDto.setAmount(new BigDecimal(BigInteger.valueOf(new Random().nextInt(10001)), 2))
        paymentDto.setCurrency("PLN")
        paymentDto.setUserId(UUID.randomUUID())
        paymentDto.setTargetAccountNumber("PL32109024025139464873117912")
        return paymentDto
    }

    protected PaymentH2 randomPaymentH2() {
        return new PaymentH2(
                UUID.randomUUID(),
                new BigDecimal(BigInteger.valueOf(new Random().nextInt(10001)), 2),
                "PLN",
                UUID.randomUUID(),
                "PL32109024025139464873117912"
        )
    }
}
