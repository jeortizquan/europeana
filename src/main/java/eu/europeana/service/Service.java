package eu.europeana.service;

import com.google.gson.Gson;
import eu.europeana.entities.controller.CalculationResponse;
import eu.europeana.entities.controller.CalculationResponseFactory;
import eu.europeana.entities.core.SmallestPositive;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import static eu.europeana.entities.controller.CalculationRecordBuilder.aCalculationRecord;

/**
 * Service class
 *
 * @author Jorge Ortiz
 */
public class Service {
    public static final Logger LOGGER = LoggerFactory.getLogger(Service.class);
    private SmallestPositive smallestPositive;
    private Gson gson;
    private StopWatch stopWatch;

    public Service(SmallestPositive smallestPositive, Gson gson, StopWatch stopWatch) {
        this.smallestPositive = smallestPositive;
        this.gson = gson;
        this.stopWatch = stopWatch;
    }

    /**
     * Set the maximum value to calculate
     *
     * @param headerType        String that indicates format to be processed xml or json
     * @param maxValueToProcess Maximum value
     * @return value in the format specified by the header type
     */
    public ResponseEntity<Object> setMaxValueToProcess(String headerType, long maxValueToProcess) {
        LOGGER.info("setting max value: {}, content-type: {}", maxValueToProcess, headerType);
        CalculationResponse calculationResponse = CalculationResponseFactory.getCalculationResponse(headerType, gson);
        try {
            if (maxValueToProcess > 0) {
                if (maxValueToProcess > 122) {
                    LOGGER.warn("limited to calculate until 122");
                    return calculationResponse.getFailureResponseEntity(new RuntimeException(), "the limit is until 122");
                }
                this.smallestPositive.setMaxValueToProcess(maxValueToProcess);

                calculationResponse.setRecord(aCalculationRecord()
                        .withFrom(1)
                        .withTo(smallestPositive.getMaxValueToProcess())
                        .withSmalllestPositiveNumber(0)
                        .withElapsedTime(0)
                        .build());

                return calculationResponse.getSuccessAcceptedResponseEntity();
            } else {
                LOGGER.warn("max value cannot be negative");
                return calculationResponse.getFailureResponseEntity(new RuntimeException(), "value has to be positive");
            }
        } catch (Exception ex) {
            LOGGER.error("unable to set max value", ex);
            return calculationResponse.getFailureResponseEntity(ex, "Unable to set max value");
        }
    }

    /**
     * Method that performs the calculation from 1 to the maxvalue
     *
     * @param headerType String that indicates format to be processed xml or json
     * @return calculated value in the format specified by the header type
     */
    public ResponseEntity<Object> calculate(String headerType) {
        LOGGER.info("starting calculation :: content-type: {}", headerType);
        stopWatch.reset();
        stopWatch.start();
        CalculationResponse calculationResponse = CalculationResponseFactory.getCalculationResponse(headerType, gson);
        try {
            long result = smallestPositive.calculate();
            stopWatch.stop();
            LOGGER.info("finished calculation :: {} is the smallest number that can be divided by 1 to {} elapsed time {}ms :: content-type: {}",
                    result,
                    smallestPositive.getMaxValueToProcess(),
                    stopWatch.getTime(),
                    headerType);
            calculationResponse.setRecord(aCalculationRecord()
                    .withFrom(1)
                    .withTo(smallestPositive.getMaxValueToProcess())
                    .withSmalllestPositiveNumber(result)
                    .withElapsedTime(stopWatch.getTime())
                    .build());
            return calculationResponse.getSuccessResponseEntity();
        } catch (Exception ex) {
            LOGGER.error("unable to calculate number", ex);
            return calculationResponse.getFailureResponseEntity(ex, "unable to calculate number");
        }
    }
}
