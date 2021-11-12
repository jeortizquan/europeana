package eu.europeana.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests {@link SmallestPositiveSolver}
 *
 * @author Jorge Ortiz
 */
public class SmallestPositiveSolverTests {

    @Test
    public void testSolverFirst() {
        SmallestPositiveSolver smallestPositiveSolver = new SmallestPositiveSolver();
        smallestPositiveSolver.setMaxValueToProcess(10);

        assertEquals( 2520, smallestPositiveSolver.calculate());
    }

    @Test
    public void testSolverSecond() {
        SmallestPositiveSolver smallestPositiveSolver = new SmallestPositiveSolver();
        smallestPositiveSolver.setMaxValueToProcess(15);

        assertEquals( 360360, smallestPositiveSolver.calculate());
    }
}
