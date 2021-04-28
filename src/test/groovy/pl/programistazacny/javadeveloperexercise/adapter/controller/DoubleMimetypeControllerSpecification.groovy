package pl.programistazacny.javadeveloperexercise.adapter.controller

import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import pl.programistazacny.javadeveloperexercise.SpringBaseSpecification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@TestPropertySource(properties = "storage.mode=csv")
class DoubleMimetypeControllerSpecification extends SpringBaseSpecification {

    def "should post json request and get response"() {
        given:
        def request = """
{
    "id": "231ea3a5-002a-4c6b-8763-a6a8b7147156",
    "prop1": "Jacek",
    "prop2": "Sracek"
}
        """

        when:
        def response = toObject(
                mvc.perform(
                        post("/double-mimetype")
                                .content(request)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString()
        )

        then:
        UUID.fromString(response["id"])
        response["prop1"] == "Jacek"
        response["prop2"] == "Sracek"
    }

    def "should post xml request and get response"() {
        given:
        def request = """
<request id="231ea3a5-002a-4c6b-8763-a6a8b7147156">
    <prop-1>Jacek</prop-1>
    <prop-2>Sracek</prop-2>
</request>
        """

        when:
        def response = toObject(
                mvc.perform(
                        post("/double-mimetype")
                                .content(request)
                                .contentType(MediaType.APPLICATION_XML)
                                .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString()
        )

        then:
        UUID.fromString(response["id"])
        response["prop1"] == "Jacek"
        response["prop2"] == "Sracek"
    }
}
