package pl.programistazacny.javadeveloperexercise.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.programistazacny.javadeveloperexercise.adapter.controller.PaymentController;
import pl.programistazacny.javadeveloperexercise.domain.port.PaymentRepository;
import pl.programistazacny.javadeveloperexercise.domain.PaymentService;

@Configuration
class PaymentConfiguration {

    @Bean
    PaymentService paymentService(PaymentRepository paymentRepository) {
        return new PaymentService(paymentRepository);
    }

    @Bean
    PaymentController paymentController(PaymentService paymentService) {
        return new PaymentController(paymentService);
    }
}
