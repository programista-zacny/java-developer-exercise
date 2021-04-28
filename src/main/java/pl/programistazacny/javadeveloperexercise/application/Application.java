package pl.programistazacny.javadeveloperexercise.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import pl.programistazacny.javadeveloperexercise.adapter.controller.DoubleMimetypeController;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    DoubleMimetypeController doubleMimetypeController() {
        return new DoubleMimetypeController();
    }

}
