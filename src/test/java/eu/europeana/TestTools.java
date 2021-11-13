package eu.europeana;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.europeana.entities.controller.CalculationRecord;
import eu.europeana.entities.controller.JSONCalculationResponse;
import eu.europeana.entities.controller.XMLCalculationResponse;
import org.springframework.http.ResponseEntity;

import static eu.europeana.entities.controller.CalculationRecordBuilder.aCalculationRecord;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test Tools Class
 *
 * @author Jorge Ortiz
 */
public class TestTools {
    /**
     * Creates a calculation record
     *
     * @return {@link CalculationRecord}
     */
    public static CalculationRecord createCalculationRecord() {
        final CalculationRecord expectedRecord = new CalculationRecord();
        expectedRecord.setElapsedTime(10);
        expectedRecord.setFrom(1);
        expectedRecord.setTo(10);
        expectedRecord.setSmallestPositiveNumber(2520);
        return expectedRecord;
    }

    /**
     * Creates an expected CalculatedJSONResponse
     *
     * @return {@link ResponseEntity}
     */
    public static ResponseEntity<Object> expectedCalculatedJSONResponseEntity() {
        final JSONCalculationResponse jsonCalculationResponse = new JSONCalculationResponse(new GsonBuilder().create());
        jsonCalculationResponse.setRecord(aCalculationRecord()
                .withFrom(1)
                .withTo(10)
                .withSmalllestPositiveNumber(2520)
                .withElapsedTime(0)
                .build());
        return jsonCalculationResponse.getSuccessResponseEntity();
    }

    /**
     * Creates an expected CalculatedXMLResponse
     *
     * @return {@link ResponseEntity}
     */
    public static ResponseEntity<Object> expectedCalculatedXMLResponseEntity() {
        final XMLCalculationResponse xmlCalculationResponse = new XMLCalculationResponse();
        xmlCalculationResponse.setRecord(aCalculationRecord()
                .withFrom(1)
                .withTo(10)
                .withSmalllestPositiveNumber(2520)
                .withElapsedTime(0)
                .build());
        return xmlCalculationResponse.getSuccessResponseEntity();
    }

    /**
     * Creates an expected Accepted JSONResponseEntity
     *
     * @return {@link ResponseEntity}
     */
    public static ResponseEntity<Object> expectedAcceptedJSONResponseEntity() {
        final JSONCalculationResponse jsonCalculationResponse = new JSONCalculationResponse(new GsonBuilder().create());
        jsonCalculationResponse.setRecord(aCalculationRecord()
                .withFrom(1)
                .withTo(10)
                .withSmalllestPositiveNumber(0)
                .withElapsedTime(0)
                .build());
        return jsonCalculationResponse.getSuccessAcceptedResponseEntity();
    }

    /**
     * Creates an expected Accepted XMLResponseEntity
     *
     * @return {@link ResponseEntity}
     */
    public static ResponseEntity<Object> expectedAcceptedXMLResponseEntity() {
        final XMLCalculationResponse xmlCalculationResponse = new XMLCalculationResponse();
        xmlCalculationResponse.setRecord(aCalculationRecord()
                .withFrom(1)
                .withTo(10)
                .withSmalllestPositiveNumber(0)
                .withElapsedTime(0)
                .build());
        return xmlCalculationResponse.getSuccessAcceptedResponseEntity();
    }

    /**
     * Check the results expected vs. calculated
     *
     * @param expectedResponse expected response
     * @param calculated       calculated response
     */
    public static void assertCalculatedResponse(ResponseEntity<Object> expectedResponse, ResponseEntity<Object> calculated) {
        assertEquals(expectedResponse.getStatusCodeValue(), calculated.getStatusCodeValue(), "Invalid Status Value");
        assertEquals(expectedResponse.getHeaders().getContentType(), calculated.getHeaders().getContentType(), "Invalid Content Type");

        if (expectedResponse.getHeaders().getContentType().toString().contains("xml")) {
            assertCalculatedXML(expectedResponse, calculated);
        } else if (expectedResponse.getHeaders().getContentType().toString().contains("json")) {
            assertCalculatedJSON(expectedResponse, calculated);
        }
    }

    /**
     * Check the results expected vs. calculated in json format
     *
     * @param expectedResponse expected response
     * @param calculated       calculated response
     */
    public static void assertCalculatedJSON(ResponseEntity<Object> expectedResponse, ResponseEntity<Object> calculated) {
        Gson gson = new GsonBuilder().create();
        CalculationRecord expectedRecord = gson.fromJson(expectedResponse.getBody().toString(), CalculationRecord.class);
        CalculationRecord calculatedRecord = gson.fromJson(calculated.getBody().toString(), CalculationRecord.class);
        assertEquals(expectedRecord.getSmallestPositiveNumber(), calculatedRecord.getSmallestPositiveNumber(), "Invalid Smallest Positive Number");
        assertEquals(expectedRecord.getTo(), calculatedRecord.getTo(), "Invalid Initial Range");
        assertEquals(expectedRecord.getFrom(), calculatedRecord.getFrom(), "Invalid Max Number To Process");
        assertTrue(calculatedRecord.getElapsedTime() >= 0, "Invalid elapsed time");
    }

    /**
     * Check the results expected vs. calculated in xml format
     *
     * @param expectedResponse expected response
     * @param calculated       calculated response
     */
    public static void assertCalculatedXML(ResponseEntity<Object> expectedResponse, ResponseEntity<Object> calculated) {
        CalculationRecord expectedRecord = XMLCalculationResponse.xmlToCalculationRecord(expectedResponse.getBody().toString());
        CalculationRecord calculatedRecord = XMLCalculationResponse.xmlToCalculationRecord(calculated.getBody().toString());
        assertEquals(expectedRecord.getSmallestPositiveNumber(), calculatedRecord.getSmallestPositiveNumber(), "Invalid Smallest Positive Number");
        assertEquals(expectedRecord.getTo(), calculatedRecord.getTo(), "Invalid Initial Range");
        assertEquals(expectedRecord.getFrom(), calculatedRecord.getFrom(), "Invalid Max Number To Process");
        assertTrue(calculatedRecord.getElapsedTime() >= 0, "Invalid elapsed time");
    }
}
