package uk.ac.ebi.pride.px.keyValueReports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.px.SubmissionRecordReport;
import uk.ac.ebi.pride.px.reports.ReportBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-03 13:54
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class SubmissionRecordKeyValueReport extends KeyValueReport implements SubmissionRecordReport {
    private static final Logger logger = LoggerFactory.getLogger(SubmissionRecordKeyValueReport.class);

    private String submissionReference = "--- NONE SPECIFIED ---";
    // Model Keys
    private static final String KEY_SUBMISSION_REFERENCE = "submissionReference";

    public String getSubmissionReference() {
        return submissionReference;
    }

    public void setSubmissionReference(String submissionReference) {
        this.submissionReference = submissionReference;
    }

    List<String> getKeys() {
        return Arrays.asList(KEY_SUBMISSION_REFERENCE);
    }

    @Override
    public boolean save(ReportBuilder builder) {
        logger.debug("Saving Submission Record");
        clearKeyValuePairs();
        storeKeyValuePair(KEY_SUBMISSION_REFERENCE, submissionReference);
        return super.save(builder);
    }
}
