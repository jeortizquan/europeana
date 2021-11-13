package eu.europeana.entities.controller;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit Tests for {@link CalculationResponseFactory}
 *
 * @author Jorge Ortiz
 */
public class CalculationResponseFactoryTests {
    @Test
    public void testCalculationResponseXML() {

        assertTrue(CalculationResponseFactory.getCalculationResponse("application/xml", new GsonBuilder().create()) instanceof XMLCalculationResponse);
    }

    @Test
    public void testCalculationResponseJSON() {
        assertTrue(CalculationResponseFactory.getCalculationResponse("application/json", new GsonBuilder().create()) instanceof JSONCalculationResponse);
    }
}
