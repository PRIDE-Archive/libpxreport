package uk.ac.ebi.pride.px;

import uk.ac.ebi.pride.px.keyValueReports.KeyValueReportFactory;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-04 10:11
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 * ---
 *
 * Abstract Factory pattern to create all related report objects
 */
public abstract class ReportFactory {
    private static final ReportFactory currentFactory = new KeyValueReportFactory();

    public static final ReportFactory getFactory() {
        // We use the default factory here
        return currentFactory;
    }

    public abstract UserFeedbackReport getUserFeedbackReport();
    public abstract SubmissionRecordReport getSubmissionRecordReport();
    public CompositeReport getCompositeReport() {
        return new CompositeReport();
    }
}
