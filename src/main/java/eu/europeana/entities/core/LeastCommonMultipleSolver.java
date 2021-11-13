package eu.europeana.entities.core;

/**
 * LeastCommonMultiple implements {@link Solver}
 *
 * Class to calculate the smallest positive number that can be divided by
 * a sequential range of numbers from 1 to maxValueToProcess without remainder using lcd algorithm
 * @author Jorge Ortiz
 */
public class LeastCommonMultipleSolver implements Solver {
    /**
     * Max value boundary of the calculation
     */
    private long maxValueToProcess = 1;

    /**
     * Calculate method using the Least Common Multiple Algorithm
     *
     * @return Smallest positive number
     */
    @Override
    public long calculate() {
        long smallestPositiveNumber = 1;
        for (long number = 2; number <= getMaxValueToProcess(); number++)
            smallestPositiveNumber = leastCommonMultiple(smallestPositiveNumber, number);
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

    /**
     * Calculate Greatest Common Divisor of two numbers
     *
     * @param a
     * @param b
     * @return gcd
     */
    private long greatestCommonDivisor(long a, long b) {
        if (a == 0)
            return b;
        return greatestCommonDivisor(b % a, a);
    }

    /**
     * Calculate Least Common Multiple of two numbers
     *
     * @param a
     * @param b
     * @return lcm
     */
    private long leastCommonMultiple(long a, long b) {
        return (a / greatestCommonDivisor(a, b)) * b;
    }


}
