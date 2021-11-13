package eu.europeana.entities.controller;

import org.springframework.http.ResponseEntity;

/**
 * CalculationResponse Class
 *
 * @author Jorge Ortiz
 */
public abstract class CalculationResponse {
    protected CalculationRecord record;

    protected ResponseEntity<Object> response;

    public ResponseEntity<Object> getResponse() {
        return response;
    }

    public abstract ResponseEntity<Object> getSuccessAcceptedResponseEntity();

    public abstract ResponseEntity<Object> getSuccessResponseEntity();

    public abstract ResponseEntity<Object> getFailureResponseEntity(Exception ex, String errorMessage);

    public void setResponse(ResponseEntity<Object> response) {
        this.response = response;
    }

    public CalculationRecord getRecord() {
        return record;
    }

    public void setRecord(CalculationRecord record) {
        this.record = record;
    }
}
