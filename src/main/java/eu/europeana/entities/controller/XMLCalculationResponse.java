package eu.europeana.entities.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.time.LocalDateTime;

public class XMLCalculationResponse extends CalculationResponse {

    public ResponseEntity<Object> getSuccessResponseEntity() {
        this.response = ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_XML)
                .body(calculationRecordToXML(this.record));
        return this.response;
    }

    public ResponseEntity<Object> getFailureResponseEntity(Exception ex, String errorMessage) {
        this.response = ResponseEntity
                .status(400)
                .contentType(MediaType.APPLICATION_XML)
                .body(exceptionToXML(new FailureResponse(LocalDateTime.now().toString(),
                        400,
                        errorMessage,
                        ex.getMessage())));
        return this.response;
    }

    public String calculationRecordToXML(CalculationRecord record) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CalculationRecord.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(record, sw);
            return sw.toString();

        } catch (JAXBException e) {
            return exceptionToXML(new FailureResponse(LocalDateTime.now().toString(),
                    400,
                    "failed to create xml",
                    e.getMessage()));
        }
    }

    public String exceptionToXML(FailureResponse response) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FailureResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(response, sw);
            return sw.toString();

        } catch (JAXBException e) {
            return "exceptionToXML: " + e.getMessage();
        }
    }
}
