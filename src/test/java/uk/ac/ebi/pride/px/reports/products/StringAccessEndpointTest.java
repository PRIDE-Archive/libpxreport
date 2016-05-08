package uk.ac.ebi.pride.px.reports.products;

import junit.framework.TestCase;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.products
 * Timestamp: 2016-05-04 15:50
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class StringAccessEndpointTest extends TestCase {
    // Constant for testing
    private static final String TESTING_BUILT_REPORT = "testkey=testvalue";
    // Testing subject
    private StringAccessEndpoint subject;

    public void setUp() throws Exception {
        super.setUp();
        subject = new StringAccessEndpoint(TESTING_BUILT_REPORT);
    }

    public void tearDown() throws Exception {
        // Nothing to do right now
    }

    public void testGetUrl() throws Exception {
        assertNull(subject.getUrl());
    }

    public void testDumpReportContent() throws Exception {
        subject.dumpReportContent().equals(TESTING_BUILT_REPORT);
    }

    public void testToString() throws Exception {
        assertEquals(subject.toString(), "StringAccessEndpoint{" +
                "builtReport='" + TESTING_BUILT_REPORT + '\'' +
                '}');
    }

}