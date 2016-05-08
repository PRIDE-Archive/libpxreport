package uk.ac.ebi.pride.px.keyValueReports;

import uk.ac.ebi.pride.px.ReportFactory;
import uk.ac.ebi.pride.px.SubmissionRecordReport;
import uk.ac.ebi.pride.px.UserFeedbackReport;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.keyValueReports
 * Timestamp: 2016-05-04 10:40
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class KeyValueReportFactory extends ReportFactory {

    @Override
    public UserFeedbackReport getUserFeedbackReport() {
        return new UserFeedbackKeyValueReport();
    }

    @Override
    public SubmissionRecordReport getSubmissionRecordReport() {
        return new SubmissionRecordKeyValueReport();
    }
}
