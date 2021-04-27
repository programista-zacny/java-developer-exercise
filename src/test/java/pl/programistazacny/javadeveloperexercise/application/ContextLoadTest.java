package pl.programistazacny.javadeveloperexercise.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(value = "classpath:test.properties", properties = {"storage.mode=csv"})
class ContextLoadTest {

    @Test
    void contextLoads() {
    }

}
