package pl.programistazacny.javadeveloperexercise.domain.port

import org.springframework.beans.factory.annotation.Autowired
import pl.programistazacny.javadeveloperexercise.SpringBaseSpecification
import pl.programistazacny.javadeveloperexercise.domain.dto.PaymentDto

abstract class PaymentRepositorySpecification extends SpringBaseSpecification {

    @Autowired
    protected PaymentRepository paymentRepository;

    def "should create payment and find it by id"() {
        given:
        PaymentDto paymentDto = randomPaymentDto()

        when:
        def paymentCreated = paymentRepository.create(paymentDto)
        def paymentFound = paymentRepository.findById(paymentCreated.getId())

        then:
        paymentFound.isPresent()
        paymentFound.get().getAmount() == paymentDto.getAmount()
        paymentFound.get().getCurrency() == paymentDto.getCurrency()
        paymentFound.get().getUserId() == paymentDto.getUserId()
        paymentFound.get().getTargetAccountNumber() == paymentDto.getTargetAccountNumber()
    }

    def "should not find payment by id"() {
        given:
        def paymentId = UUID.randomUUID()

        expect:
        paymentRepository.findById(paymentId).isEmpty()
    }

    def "should create 2 payments and get them"() {
        given:
        PaymentDto paymentDto1 = randomPaymentDto()
        PaymentDto paymentDto2 = randomPaymentDto()

        when:
        def payment1Id = paymentRepository.create(paymentDto1).getId()
        def payment2Id = paymentRepository.create(paymentDto2).getId()
        def paymentsIds = [payment1Id, payment2Id]
        def payments = paymentRepository.findAll()

        then:
        payments.stream().filter(payment -> payment.getId() in paymentsIds).count() == paymentsIds.size()
    }

    def "should create and delete payment"() {
        given:
        PaymentDto paymentDto = randomPaymentDto()

        when:
        def paymentCreatedId = paymentRepository.create(paymentDto).getId()
        def deleted = paymentRepository.delete(paymentCreatedId)

        then:
        deleted
    }

    def "should not delete nonexistent payment"() {
        given:
        def paymentId = UUID.randomUUID()

        expect:
        !paymentRepository.delete(paymentId)
    }

    def "should create and update payment"() {
        given:
        PaymentDto paymentDto = randomPaymentDto()
        PaymentDto paymentUpdateDto = new PaymentDto()
        paymentUpdateDto.setAmount(paymentDto.getAmount())
        paymentUpdateDto.setCurrency("GBP")
        paymentUpdateDto.setUserId(paymentDto.getUserId())
        paymentUpdateDto.setTargetAccountNumber(paymentDto.getTargetAccountNumber())

        when:
        def paymentCreated = paymentRepository.create(paymentDto)
        def paymentUpdated = paymentRepository.update(paymentCreated.getId(), paymentUpdateDto)

        then:
        paymentUpdated.isPresent()
        paymentUpdated.get().getAmount() == paymentDto.getAmount()
        paymentUpdated.get().getCurrency() == paymentUpdateDto.getCurrency()
        paymentUpdated.get().getUserId() == paymentDto.getUserId()
        paymentUpdated.get().getTargetAccountNumber() == paymentDto.getTargetAccountNumber()
    }

    def "should not update nonexistent payment"() {
        given:
        def paymentId = UUID.randomUUID()
        def paymentDto = randomPaymentDto()

        when:
        def updated = paymentRepository.update(paymentId, paymentDto)

        then:
        updated.isEmpty()
    }
}
