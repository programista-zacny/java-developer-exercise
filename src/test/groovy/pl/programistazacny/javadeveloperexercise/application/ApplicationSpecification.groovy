package pl.programistazacny.javadeveloperexercise.application

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
class ApplicationSpecification extends Specification {

    def "should load context"() {
        // that's all :)
    }
}
