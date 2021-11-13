package eu.europeana.entities.controller;

import eu.europeana.TestTools;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test {@link CalculationRecordBuilder}
 *
 * @author Jorge Ortiz
 */
public class CalculationRecordBuilderTests {
    @Test
    public void testCreation() {

        assertEquals(TestTools.createCalculationRecord(), CalculationRecordBuilder.aCalculationRecord()
                .withSmalllestPositiveNumber(2520)
                .withFrom(1)
                .withTo(10)
                .withElapsedTime(10)
                .build());
    }
}
