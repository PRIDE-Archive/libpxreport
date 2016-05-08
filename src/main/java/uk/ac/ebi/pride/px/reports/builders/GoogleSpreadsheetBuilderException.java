package uk.ac.ebi.pride.px.reports.builders;

import uk.ac.ebi.pride.px.reports.ReportBuilderException;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.builders
 * Timestamp: 2016-05-06 22:52
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class GoogleSpreadsheetBuilderException extends ReportBuilderException {
    public GoogleSpreadsheetBuilderException() {
    }

    public GoogleSpreadsheetBuilderException(String message) {
        super(message);
    }

    public GoogleSpreadsheetBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoogleSpreadsheetBuilderException(Throwable cause) {
        super(cause);
    }

    public GoogleSpreadsheetBuilderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
