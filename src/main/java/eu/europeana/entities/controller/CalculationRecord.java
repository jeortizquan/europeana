package eu.europeana.entities.controller;

import java.util.Objects;

/**
 * CalculationRecord Data Transfer Object in the response of the controller
 *
 * @author Jorge Ortiz
 */
public class CalculationRecord {
    private long smallestPositiveNumber;
    private long from;
    private long to;
    private long elapsedTime;

    public long getSmallestPositiveNumber() {
        return smallestPositiveNumber;
    }

    public void setSmallestPositiveNumber(long smallestPositiveNumber) {
        this.smallestPositiveNumber = smallestPositiveNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalculationRecord that = (CalculationRecord) o;
        return smallestPositiveNumber == that.smallestPositiveNumber
                && from == that.from
                && to == that.to
                && elapsedTime == that.elapsedTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(smallestPositiveNumber, from, to, elapsedTime);
    }

    @Override
    public String toString() {
        return "["+ smallestPositiveNumber +" is the smallest number that can be divided by "
                +from+" to "+to+" calculation took: "+elapsedTime+" ms]";
    }
}
