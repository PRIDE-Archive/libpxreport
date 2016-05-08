package uk.ac.ebi.pride.px.keyValueReports;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-03 16:50
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class KeyValueReportException extends RuntimeException {
    public KeyValueReportException() {
    }

    public KeyValueReportException(String message) {
        super(message);
    }

    public KeyValueReportException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyValueReportException(Throwable cause) {
        super(cause);
    }

    public KeyValueReportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
