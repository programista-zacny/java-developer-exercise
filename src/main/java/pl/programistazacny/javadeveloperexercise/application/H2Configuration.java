package pl.programistazacny.javadeveloperexercise.application;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.programistazacny.javadeveloperexercise.adapter.h2.PaymentH2CrudRepository;
import pl.programistazacny.javadeveloperexercise.adapter.h2.PaymentH2Repository;
import pl.programistazacny.javadeveloperexercise.adapter.h2.model.PaymentH2;

@Configuration
@ConditionalOnProperty(prefix = "storage", name = "mode", havingValue = "h2")
@EnableJpaRepositories(basePackageClasses = PaymentH2CrudRepository.class)
@EntityScan(basePackageClasses = PaymentH2.class)
@PropertySource("classpath:h2.properties")
@Import(DataSourceAutoConfiguration.class)
class H2Configuration {

    @Bean
    PaymentH2Repository paymentH2Repository(PaymentH2CrudRepository paymentH2CrudRepository) {
        return new PaymentH2Repository(paymentH2CrudRepository);
    }
}
