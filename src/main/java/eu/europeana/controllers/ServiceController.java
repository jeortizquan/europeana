package eu.europeana.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ServiceController.class);

    public static final String API_INDEX = "Welcome to Europeana Smallest Positive Number Calculator API";

    @Autowired
    private ServiceController service;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        return API_INDEX;
    }

    @PostMapping(path = "/maxvalue/{id}")
    public ResponseEntity<Object> maxValue() {

        return null;
    }
}
