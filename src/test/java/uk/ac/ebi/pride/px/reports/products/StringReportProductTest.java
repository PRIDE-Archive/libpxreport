package uk.ac.ebi.pride.px.reports.products;

import junit.framework.TestCase;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.products
 * Timestamp: 2016-05-04 15:59
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class StringReportProductTest extends TestCase {
    public void testGetAccessEndpoint() throws Exception {
        String testReport = "test_key=test_value";
        StringReportProduct subject = new StringReportProduct(testReport);
        assertNotNull(subject.getAccessEndpoint());
        assertEquals(subject.getAccessEndpoint().dumpReportContent(), testReport);
    }

}