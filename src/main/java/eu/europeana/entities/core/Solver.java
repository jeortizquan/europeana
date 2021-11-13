package eu.europeana.entities.core;

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
