package eu.europeana.entities.controller;

public final class CalculationRecordBuilder {
    private long smalllestPositiveNumber;
    private long from;
    private long to;
    private long elapsedTime;

    private CalculationRecordBuilder() {
    }

    public static CalculationRecordBuilder aCalculationRecord() {
        return new CalculationRecordBuilder();
    }

    public CalculationRecordBuilder withSmalllestPositiveNumber(long smalllestPositiveNumber) {
        this.smalllestPositiveNumber = smalllestPositiveNumber;
        return this;
    }

    public CalculationRecordBuilder withFrom(long from) {
        this.from = from;
        return this;
    }

    public CalculationRecordBuilder withTo(long to) {
        this.to = to;
        return this;
    }

    public CalculationRecordBuilder withElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
        return this;
    }

    public CalculationRecord build() {
        CalculationRecord calculationRecord = new CalculationRecord();
        calculationRecord.setSmalllestPositiveNumber(smalllestPositiveNumber);
        calculationRecord.setFrom(from);
        calculationRecord.setTo(to);
        calculationRecord.setElapsedTime(elapsedTime);
        return calculationRecord;
    }
}
