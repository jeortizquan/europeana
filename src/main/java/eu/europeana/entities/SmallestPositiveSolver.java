package eu.europeana.entities;

/**
 * SmallestPositiveSolver implements {@link Solver}
 *
 * Class to calculate the smallest positive number that can be divided by
 * a sequential range of numbers from 1 to maxValueToProcess without remainder
 */
public class SmallestPositiveSolver implements Solver {

    private long maxValueToProcess;

    /**
     * Calculate method
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