package eu.europeana.entities.controller;

public class CalculationRecord {
    private long smalllestPositiveNumber;
    private long from;
    private long to;
    private long elapsedTime;

    public long getSmalllestPositiveNumber() {
        return smalllestPositiveNumber;
    }

    public void setSmalllestPositiveNumber(long smalllestPositiveNumber) {
        this.smalllestPositiveNumber = smalllestPositiveNumber;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
