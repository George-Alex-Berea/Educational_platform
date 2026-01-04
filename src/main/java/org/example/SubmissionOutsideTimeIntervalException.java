package org.example;

public class SubmissionOutsideTimeIntervalException extends RuntimeException {
    public SubmissionOutsideTimeIntervalException(String message) {
        super(message);
    }
}
