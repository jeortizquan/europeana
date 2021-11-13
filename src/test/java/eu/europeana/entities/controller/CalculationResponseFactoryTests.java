package eu.europeana.entities.controller;

import eu.europeana.entities.controller.CalculationResponseFactory;
import eu.europeana.entities.controller.JSONCalculationResponse;
import eu.europeana.entities.controller.XMLCalculationResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculationResponseFactoryTests {
    @Test
    public void testCalculationResponseXML() {

        assertTrue(CalculationResponseFactory.getCalculationResponse("application/xml") instanceof XMLCalculationResponse);
    }

    @Test
    public void testCalculationResponseJSON() {
        assertTrue(CalculationResponseFactory.getCalculationResponse("application/json") instanceof JSONCalculationResponse);
    }
}
