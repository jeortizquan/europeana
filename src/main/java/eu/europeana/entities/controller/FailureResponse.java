package eu.europeana.entities.controller;

public class FailureResponse {
    private String timeStamp;
    private long status;
    private String error;
    private String exceptionMessage;

    public FailureResponse(String timeStamp, long status, String errorMessage, String exceptionMessage) {
        this.timeStamp = timeStamp;
        this.status = status;
        this.error = errorMessage;
        this.exceptionMessage = exceptionMessage;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
