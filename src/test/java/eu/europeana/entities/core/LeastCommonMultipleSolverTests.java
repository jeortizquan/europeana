package eu.europeana.entities.core;

import eu.europeana.entities.core.LeastCommonMultipleSolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests {@link LeastCommonMultipleSolver}
 *
 * @author Jorge Ortiz
 */
public class LeastCommonMultipleSolverTests {

    private LeastCommonMultipleSolver leastCommonMultipleSolver;

    @BeforeEach
    public void setUp() {
        leastCommonMultipleSolver = new LeastCommonMultipleSolver();
    }

    @AfterEach
    public void tearDown() {
        leastCommonMultipleSolver = null;
    }

    /**
     * Method to provide input data and expected values
     *
     * @return Stream of Arguments indicating [maxValue :: expectedValue]
     */
    private static Stream<Arguments> provideInputDataAndExpectedValues() {
        return Stream.of(
                Arguments.of(1L, 1L),
                Arguments.of(2L, 2L),
                Arguments.of(3L, 6L),
                Arguments.of(4L, 12L),
                Arguments.of(5L, 60L),
                Arguments.of(10L, 2520L),
                Arguments.of(12L, 27720L),
                Arguments.of(25L, 26771144400L)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInputDataAndExpectedValues")
    public void testSolver(final long maxValueToProcess, final long expectedSmallestNumber) {
        leastCommonMultipleSolver.setMaxValueToProcess(maxValueToProcess);

        assertEquals(expectedSmallestNumber, leastCommonMultipleSolver.calculate(),"Unexpected smallest positive number");
    }
}
