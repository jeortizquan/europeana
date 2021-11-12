package eu.europeana.entities;

/**
 * Interface Solver
 *
 * @author Jorge Ortiz
 */
public interface Solver {
    long calculate();
    void setMaxValueToProcess(long maxValueToProcess);
    long getMaxValueToProcess();
}
