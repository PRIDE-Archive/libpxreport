package uk.ac.ebi.pride.px;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-04 10:36
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public interface SubmissionRecordReport extends Report {

    String getSubmissionReference();
    void setSubmissionReference(String submissionReference);
}
