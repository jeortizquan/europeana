package eu.europeana.service;

import eu.europeana.config.ServiceConfiguration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static eu.europeana.TestTools.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit Tests for {@link Service}
 *
 * @author Jorge Ortiz
 */
@SpringBootTest
@Import(ServiceConfiguration.class)
public class ServiceTests {

    @Autowired
    private Service service;

    /**
     * Method to provide input data and expected values
     *
     * @return Stream of Arguments indicating [media Type :: expected ResponseEntity ]
     */
    private static Stream<Arguments> provideInputDataAndExpectedValues() {
        return Stream.of(
                Arguments.of(MediaType.APPLICATION_JSON, expectedAcceptedJSONResponseEntity()),
                Arguments.of(MediaType.APPLICATION_XML, expectedAcceptedXMLResponseEntity())
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputDataAndExpectedValues")
    public void setMaxValueToProcess(MediaType mediaType, ResponseEntity<Object> expectedResponse) {
        assertEquals(expectedResponse, service.setMaxValueToProcess(mediaType.toString(), 10));
    }

    /**
     * Method to provide input data and expected values
     *
     * @return Stream of Arguments indicating [media Type :: expected ResponseEntity ]
     */
    private static Stream<Arguments> provideCalculateDataAndExpectedValues() {
        return Stream.of(
                Arguments.of(MediaType.APPLICATION_JSON, expectedCalculatedJSONResponseEntity()),
                Arguments.of(MediaType.APPLICATION_XML, expectedCalculatedXMLResponseEntity())
        );
    }

    @ParameterizedTest
    @MethodSource("provideCalculateDataAndExpectedValues")
    public void calculate(MediaType mediaType, ResponseEntity<Object> expectedResponse) {
        service.setMaxValueToProcess(mediaType.toString(), 10);
        assertCalculatedResponse(expectedResponse, service.calculate(mediaType.toString()));
    }
}
