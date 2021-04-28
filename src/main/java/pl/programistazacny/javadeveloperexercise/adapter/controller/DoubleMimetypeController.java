package pl.programistazacny.javadeveloperexercise.adapter.controller;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.UUID;

@RestController
public class DoubleMimetypeController {

    @PostMapping(value = "/double-mimetype", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    ResponseEntity<Response> doubleMimetype(@RequestBody @Valid Request request) {
        return ResponseEntity.ok(Response.builder()
                .id(request.getId())
                .prop1(request.getProp1())
                .prop2(request.getProp2())
                .build());
    }


    @Data
    @XmlRootElement(name = "request")
    @XmlAccessorType(XmlAccessType.NONE)
    static class Request {
        @NotNull
        @XmlAttribute(name = "id")
        private UUID id;

        @NotBlank
        @XmlElement(name = "prop-1")
        private String prop1;

        @NotBlank
        @XmlElement(name = "prop-2")
        private String prop2;
    }

    @Data
    @Builder
    static class Response {
        private UUID id;
        private String prop1;
        private String prop2;
    }
}
