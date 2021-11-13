package eu.europeana.entities.core;

import eu.europeana.entities.core.LeastCommonMultipleSolver;
import eu.europeana.entities.core.LoopSolver;
import eu.europeana.entities.core.SmallestPositive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit Tests for {@link SmallestPositive}
 *
 * @author Jorge Ortiz
 */
public class SmallestPositiveTests {

    private SmallestPositive smallestPositive;

    @AfterEach
    public void tearDown() {
        smallestPositive = null;
    }

    /**
     * Method to provide input data and expected values
     *
     * @return Stream of Arguments indicating [strategy: 1=Loop 2=LCM :: maxValue :: expectedValue]
     */
    private static Stream<Arguments> provideInputDataAndExpectedValues() {
        return Stream.of(
                Arguments.of(1, 1L, 1L),
                Arguments.of(2, 2L, 2L),
                Arguments.of(1, 3L, 6L),
                Arguments.of(2, 4L, 12L),
                Arguments.of(1, 5L, 60L),
                Arguments.of(2, 10L, 2520L),
                Arguments.of(1, 20L, 232792560L),
                Arguments.of(2, 25L, 26771144400L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputDataAndExpectedValues")
    public void testSmallerPositive(final int strategy, final long maxValueToProcess, final long expectedSmallestNumber) {
        if (strategy % 2 == 0) {
            smallestPositive = new SmallestPositive(new LeastCommonMultipleSolver());
        } else {
            smallestPositive = new SmallestPositive(new LoopSolver());
        }
        smallestPositive.setMaxValueToProcess(maxValueToProcess);

        assertEquals(expectedSmallestNumber, smallestPositive.calculate(),"Unexpected smallest positive number");
    }
}
