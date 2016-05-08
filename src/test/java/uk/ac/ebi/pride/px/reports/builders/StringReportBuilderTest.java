package uk.ac.ebi.pride.px.reports.builders;

import junit.framework.TestCase;
import uk.ac.ebi.pride.px.reports.products.StringAccessEndpoint;
import uk.ac.ebi.pride.px.reports.products.StringReportProduct;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.builders
 * Timestamp: 2016-05-04 16:01
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class StringReportBuilderTest extends TestCase {
    // Testing constants
    private static final String TEST_KEY = "test_key";
    private static final String TEST_VALUE = "test_value";

    private StringReportBuilder subject;

    public void setUp() throws Exception {
        super.setUp();
        subject = new StringReportBuilder();
    }

    public void tearDown() throws Exception {
        // Empty
    }

    public void testBuild() throws Exception {
        subject.build(TEST_KEY, TEST_VALUE);
        assertTrue(true);
    }

    public void testGetProduct() throws Exception {
        assertNotNull(subject.getProduct());
        assertTrue(subject.getProduct() instanceof StringReportProduct);
        assertTrue(subject.getProduct().getAccessEndpoint() instanceof StringAccessEndpoint);
    }

}