package pl.programistazacny.javadeveloperexercise.application;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.programistazacny.javadeveloperexercise.adapter.csv.PaymentCsvRepository;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@ConditionalOnProperty(prefix = "storage", name = "mode", havingValue = "csv")
@Slf4j
class CsvConfiguration {

    @Bean
    CsvProperties csvProperties() {
        return new CsvProperties();
    }

    @Bean
    PaymentCsvRepository csvPaymentRepository(CsvProperties csvProperties) {
        createCsvIfNotExists(csvProperties);
        return new PaymentCsvRepository(csvProperties.getFilename());
    }

    private void createCsvIfNotExists(CsvProperties csvProperties) {
        Path pathToFile = Paths.get(csvProperties.getFilename());
        if (Files.notExists(pathToFile)) {
            log.warn("Csv file doesn't exist. Creating...");
            try {
                Path createdCsv = Files.createFile(pathToFile);
                log.warn("New csv file was created: " + createdCsv);
            } catch (IOException e) {
                throw new RuntimeException("Problem with creating empty csv file!", e);
            }
        }
    }

    @ConfigurationProperties(prefix = "csv")
    @Data
    static class CsvProperties {

        @NotBlank
        private String filename = "payments.csv";
    }
}
