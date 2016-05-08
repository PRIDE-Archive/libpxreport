package uk.ac.ebi.pride.px.keyValueReports;

import junit.framework.TestCase;
import uk.ac.ebi.pride.px.UserFeedbackReport;
import uk.ac.ebi.pride.px.reports.builders.StringReportBuilder;

import java.util.List;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.keyValueReports
 * Timestamp: 2016-05-04 15:20
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class UserFeedbackKeyValueReportTest extends TestCase {
    private UserFeedbackKeyValueReport subject;
    private StringReportBuilder builder;

    public void setUp() throws Exception {
        super.setUp();
        // Create instance
        subject = new UserFeedbackKeyValueReport();
        builder = new StringReportBuilder();
    }

    public void tearDown() throws Exception {
        // Empty
    }

    public void testGetSetRating() throws Exception {
        subject.setRating(UserFeedbackReport.RATING_VERY_BAD);
        assertEquals(UserFeedbackKeyValueReport.RATING_VERY_BAD, subject.getRating());
        subject.setRating(UserFeedbackReport.RATING_BAD);
        assertEquals(UserFeedbackKeyValueReport.RATING_BAD, subject.getRating());
        subject.setRating(UserFeedbackReport.RATING_NEUTRAL);
        assertEquals(UserFeedbackKeyValueReport.RATING_NEUTRAL, subject.getRating());
        subject.setRating(UserFeedbackReport.RATING_GOOD);
        assertEquals(UserFeedbackKeyValueReport.RATING_GOOD, subject.getRating());
        subject.setRating(UserFeedbackReport.RATING_VERY_GOOD);
        assertEquals(UserFeedbackKeyValueReport.RATING_VERY_GOOD, subject.getRating());
    }

    public void testGetSetComments() throws Exception {
        String testComment = "Test Comment";
        assertNotNull(subject.getComments());
        assertTrue(subject.getComments().length() != 0);
        subject.setComments(testComment);
        assertTrue(subject.getComments().equals(testComment));
    }

    public void testGetKeys() throws Exception {
        List<String> keys = subject.getKeys();
        assertNotNull(keys);
        assertTrue(keys.size() > 0);
    }

    public void testSave() throws Exception {
        // This one is tricky, we already have tests that ensure every builder does its job, so we just test here that
        // there is no error running the building director
        subject.save(builder);
    }

}