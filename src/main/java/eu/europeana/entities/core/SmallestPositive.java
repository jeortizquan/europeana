package eu.europeana.entities.core;

public class SmallestPositive {
    private Solver solver;

    public SmallestPositive(Solver solver) {
        this.solver = solver;
    }

    public long calculate() {
        return solver.calculate();
    }

    public long getMaxValueToProcess() {
        return solver.getMaxValueToProcess();
    }

    public void setMaxValueToProcess(final long maxValueToProcess) {
        this.solver.setMaxValueToProcess(maxValueToProcess);
    }
}
