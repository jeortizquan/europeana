package eu.europeana.controllers;

import eu.europeana.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {

    public static final String API_INDEX = "Welcome to Europeana Smallest Positive Number Calculator API";

    @Autowired
    private Service service;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        return API_INDEX;
    }

    @PostMapping(path = "/maxvalue/{id}")
    public ResponseEntity<Object> maxValue(@RequestHeader(value = "accept") String acceptHeader,
                                           @PathVariable(value = "id") Long maxValueToProcess) {
        return service.setMaxValueToProcess(acceptHeader, maxValueToProcess);
    }

    @PostMapping(path = "/calculate")
    public ResponseEntity<Object> calculate(@RequestHeader(value = "accept") String acceptHeader) {

        return service.calculate(acceptHeader);
    }
}
