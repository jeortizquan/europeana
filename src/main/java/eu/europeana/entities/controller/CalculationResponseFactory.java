package eu.europeana.entities.controller;

public class CalculationResponseFactory {
    public static CalculationResponse getCalculationResponse(String headerType) {
        CalculationResponse response = null;
        if (headerType.toLowerCase().contains("xml")) {
            response = new XMLCalculationResponse();
        } else if (headerType.toLowerCase().contains("json")) {
            response = new JSONCalculationResponse();
        }
        return response;
    }
}
