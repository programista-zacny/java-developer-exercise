package pl.programistazacny.javadeveloperexercise

import org.springframework.http.MediaType
import pl.programistazacny.javadeveloperexercise.adapter.controller.model.PaymentRequest

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

abstract class PaymentIntegrationSpecification extends SpringBaseSpecification {

    def "should get 404 for nonexistent payment"() {
        given: "random UUID"
        def paymentId = UUID.randomUUID()

        expect: "404"
        mvc.perform(get("/payment/$paymentId")).andExpect(status().isNotFound())
    }

    def "should save payment"() {
        given: "payment with random data"
        def paymentRequest = randomPaymentRequest()

        when: "save new payment"
        def payment = toObject(
                mvc.perform(
                        post("/payment")
                                .content(toJson(paymentRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString()
        )

        then: "check result of saving new payment"
        UUID.fromString(payment["id"])
        payment["amount"] == paymentRequest.getAmount()
        payment["currency"] == paymentRequest.getCurrency()
        payment["userId"] == paymentRequest.getUserId().toString()
        payment["targetAccountNumber"] == paymentRequest.getTargetAccountNumber()
    }

    def "should get 400 for saving payment without required data"() {
        given: "payment without required data"
        def paymentRequest = emptyPaymentRequest()

        expect: "400"
        mvc.perform(
                post("/payment")
                        .content(toJson(paymentRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
    }

    def "should save 3 payments ang get list of them"() {
        given: "3 payments with random data"
        def paymentRequest1 = randomPaymentRequest()
        def paymentRequest2 = randomPaymentRequest()
        def paymentRequest3 = randomPaymentRequest()

        when: "save new payments and get list of payments"
        def payment1Id = toObject(
                mvc.perform(
                        post("/payment")
                                .content(toJson(paymentRequest1))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString()
        )["id"]
        def payment2Id = toObject(
                mvc.perform(
                        post("/payment")
                                .content(toJson(paymentRequest2))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString()
        )["id"]
        def payment3Id = toObject(
                mvc.perform(
                        post("/payment")
                                .content(toJson(paymentRequest3))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString()
        )["id"]
        def paymentsIds = [payment1Id, payment2Id, payment3Id]
        List payments = toObject(
                mvc.perform(
                        get("/payments"))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString()
        )

        then: "check list of payments"
        payments.stream().filter(payment -> payment["id"] in paymentsIds).count() == paymentsIds.size()
    }

    def "should save and delete payment"() {
        given: "payment with random data"
        def paymentRequest = randomPaymentRequest()

        when: "save new payment"
        def paymentId = toObject(
                mvc.perform(
                        post("/payment")
                                .content(toJson(paymentRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString()
        )["id"]

        then: "delete new payment"
        mvc.perform(delete("/payment/$paymentId")).andExpect(status().isNoContent())
    }

    def "should get 404 for deleting nonexistent payment"() {
        given: "random UUID"
        def paymentId = UUID.randomUUID()

        expect: "404"
        mvc.perform(delete("/payment/$paymentId")).andExpect(status().isNotFound())
    }

    def "should save and edit payment"() {
        given: "payment with random data"
        def paymentRequest = randomPaymentRequest()
        def paymentEditRequest = new PaymentRequest(
                paymentRequest.getAmount().add(new BigDecimal("200.00")),
                paymentRequest.getCurrency(),
                paymentRequest.getUserId(),
                paymentRequest.getTargetAccountNumber()
        )

        when: "save new payment and edit it"
        def payment = toObject(
                mvc.perform(
                        post("/payment")
                                .content(toJson(paymentRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse().getContentAsString()
        )
        def paymentId = payment["id"]
        def paymentEdited = toObject(
                mvc.perform(
                        put("/payment/$paymentId")
                                .content(toJson(paymentEditRequest))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString()
        )

        then: "check edited payment"
        payment["id"] == paymentEdited["id"]
        payment["amount"] != paymentEdited["amount"]
        paymentEdited["amount"] == paymentEditRequest.getAmount()
        payment["currency"] == paymentEdited["currency"]
        payment["userId"] == paymentEdited["userId"]
        payment["targetAccountNumber"] == paymentEdited["targetAccountNumber"]
    }

    def "should get 404 for editing nonexistent payment"() {
        given: "random UUID"
        def paymentId = UUID.randomUUID()
        def paymentEditRequest = randomPaymentRequest()

        expect: "404"
        mvc.perform(
                put("/payment/$paymentId")
                        .content(toJson(paymentEditRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
    }

}
