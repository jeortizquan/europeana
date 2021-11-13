package eu.europeana.entities.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.time.LocalDateTime;

/**
 * Calculation XML format response
 *
 * @author Jorge Ortiz
 */
public class XMLCalculationResponse extends CalculationResponse {

    public ResponseEntity<Object> getSuccessAcceptedResponseEntity() {
        this.response = ResponseEntity.accepted()
                .contentType(MediaType.APPLICATION_XML)
                .body(calculationRecordToXML(this.record));
        return this.response;
    }

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

    public static String calculationRecordToXML(CalculationRecord record) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CalculationRecord.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            JAXBElement<CalculationRecord> jaxbElement =
                    new JAXBElement<>(new QName("", "CalculationRecord"),
                            CalculationRecord.class,
                            record);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(jaxbElement, sw);
            return sw.toString();

        } catch (JAXBException e) {
            return exceptionToXML(new FailureResponse(LocalDateTime.now().toString(),
                    400,
                    "failed to create xml",
                    e.getMessage()));
        }
    }

    public static CalculationRecord xmlToCalculationRecord(String xmlValue) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CalculationRecord.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            InputStream is = new ByteArrayInputStream(xmlValue.getBytes());
            JAXBElement<CalculationRecord> xml = jaxbUnmarshaller.unmarshal(new StreamSource(is), CalculationRecord.class);
            return xml.getValue();
        } catch (JAXBException e) {
            return null;
        }
    }

    public static String exceptionToXML(FailureResponse response) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(FailureResponse.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            JAXBElement<FailureResponse> jaxbElement =
                    new JAXBElement<>(new QName("", "FailureResponse"),
                            FailureResponse.class,
                            response);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(jaxbElement, sw);
            return sw.toString();

        } catch (JAXBException e) {
            return "Unable to Transform: " + e.getMessage();
        }
    }
}
