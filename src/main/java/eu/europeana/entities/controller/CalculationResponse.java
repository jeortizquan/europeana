package eu.europeana.entities.controller;

import org.springframework.http.ResponseEntity;

public abstract class CalculationResponse {
    protected CalculationRecord record;

    protected ResponseEntity<Object> response;

    public ResponseEntity<Object> getResponse() {
        return response;
    }

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
