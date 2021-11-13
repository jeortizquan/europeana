package eu.europeana.entities.core;

/**
 * LoopSolver implements {@link Solver}
 * <p>
 * Class to calculate the smallest positive number that can be divided by
 * a sequential range of numbers from 1 to maxValueToProcess without remainder using loops
 */
public class LoopSolver implements Solver {
    /**
     * Max value boundary of the calculation
     */
    private long maxValueToProcess = 1;

    /**
     * Calculate method using Loops
     *
     * @return Smallest positive number
     */
    @Override
    public long calculate() {
        long smallestPositiveNumber = 1;
        boolean smallestNumberIsFound = false;
        long divisorNumber;
        boolean withoutRemainder;

        while (!smallestNumberIsFound) {
            divisorNumber = 1;
            withoutRemainder = true;
            while (withoutRemainder && divisorNumber <= getMaxValueToProcess()) {
                withoutRemainder = smallestPositiveNumber % divisorNumber == 0;
                if (withoutRemainder) divisorNumber++;
            }
            smallestNumberIsFound = withoutRemainder && (divisorNumber - 1) == getMaxValueToProcess();
            if (!smallestNumberIsFound) smallestPositiveNumber++;
        }
        return smallestPositiveNumber;
    }

    /**
     * Set the upper boundary
     *
     * @param maxValueToProcess upper boundary of the calculation.
     */
    @Override
    public void setMaxValueToProcess(long maxValueToProcess) {
        this.maxValueToProcess = maxValueToProcess;
    }

    /**
     * @return number representing the max boundary of the calculation.
     */
    @Override
    public long getMaxValueToProcess() {
        return this.maxValueToProcess;
    }
}