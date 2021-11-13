package eu.europeana.entities.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class JSONCalculationResponse extends CalculationResponse {

    @Autowired
    private Gson gson;

    public ResponseEntity<Object> getSuccessResponseEntity() {
        this.response = ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(this.record));
        return this.response;
    }

    public ResponseEntity<Object> getFailureResponseEntity(Exception ex, String errorMessage) {

        this.response = ResponseEntity
                .status(400)
                .contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(new FailureResponse(LocalDateTime.now().toString(),
                        400,
                        errorMessage,
                        ex.getMessage())));
        return this.response;
    }
}
