package uk.ac.ebi.pride.px.keyValueReports;

import junit.framework.TestCase;
import uk.ac.ebi.pride.px.reports.builders.StringReportBuilder;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.keyValueReports
 * Timestamp: 2016-05-04 15:34
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class SubmissionRecordKeyValueReportTest extends TestCase {
    // Testing constants
    private static final String TEST_SUBMISSION_REFERENCE = "1-9823745-13450928765";

    private SubmissionRecordKeyValueReport subject;
    private StringReportBuilder builder;

    public void setUp() throws Exception {
        super.setUp();
        subject = new SubmissionRecordKeyValueReport();
        builder = new StringReportBuilder();
    }

    public void tearDown() throws Exception {
        // Empty
    }

    public void testGetSetSubmissionReference() throws Exception {
        // Test there is always some reference, even if it's not been set
        assertNotNull(subject.getSubmissionReference());
        // Test that we set one, and get the same
        subject.setSubmissionReference(TEST_SUBMISSION_REFERENCE);
        assertEquals(subject.getSubmissionReference(), TEST_SUBMISSION_REFERENCE);
    }

    public void testGetKeys() throws Exception {
        // We test how many, not their specific content
        assertNotNull(subject.getKeys());
        assertTrue(subject.getKeys().size() > 0);
    }

    public void testSave() throws Exception {
        // This one is tricky, we already have tests that ensure every builder does its job, so we just test here that
        // there is no error running the building director
        subject.save(builder);
    }

}