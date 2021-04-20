package pl.programistazacny.javadeveloperexercise

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import pl.programistazacny.javadeveloperexercise.application.Application

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
@AutoConfigureMockMvc
abstract class SpringBaseSpecification extends BaseSpecification {

    @Autowired
    protected MockMvc mvc

    protected String toJson(Object object) {
        JsonOutput.toJson(object)
    }

    protected Object toObject(String json) {
        new JsonSlurper().parseText(json)
    }
}
