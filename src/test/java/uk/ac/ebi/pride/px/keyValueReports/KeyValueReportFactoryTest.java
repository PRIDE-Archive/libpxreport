package uk.ac.ebi.pride.px.keyValueReports;

import junit.framework.TestCase;
import uk.ac.ebi.pride.px.CompositeReport;
import uk.ac.ebi.pride.px.ReportFactory;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.keyValueReports
 * Timestamp: 2016-05-04 15:09
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class KeyValueReportFactoryTest extends TestCase {
    public void testGetUserFeedbackReport() throws Exception {
        assertTrue(ReportFactory.getFactory().getUserFeedbackReport() instanceof UserFeedbackKeyValueReport);
    }

    public void testGetSubmissionRecordReport() throws Exception {
        assertTrue(ReportFactory.getFactory().getSubmissionRecordReport() instanceof SubmissionRecordKeyValueReport);
    }

    public void testGetCompositeReport() throws Exception {
        assertTrue(ReportFactory.getFactory().getCompositeReport() instanceof CompositeReport);
    }
}