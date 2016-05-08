package uk.ac.ebi.pride.px.reports;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.builders
 * Timestamp: 2016-05-03 14:13
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class ReportBuilderException extends RuntimeException {
    public ReportBuilderException() {
    }

    public ReportBuilderException(String message) {
        super(message);
    }

    public ReportBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportBuilderException(Throwable cause) {
        super(cause);
    }

    public ReportBuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
