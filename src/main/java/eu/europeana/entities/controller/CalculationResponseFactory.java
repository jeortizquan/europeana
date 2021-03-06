package eu.europeana.entities.controller;

import com.google.gson.Gson;

/**
 * CalculationResponseFactory using factory pattern to create object according to the header
 *
 * @author Jorge Ortiz
 */
public class CalculationResponseFactory {
    public static CalculationResponse getCalculationResponse(final String headerType,
                                                             final Gson gson) {
        CalculationResponse response = null;
        if (headerType.toLowerCase().contains("xml")) {
            response = new XMLCalculationResponse();
        } else if (headerType.toLowerCase().contains("json")) {
            response = new JSONCalculationResponse(gson);
        }
        return response;
    }
}
