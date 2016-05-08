package uk.ac.ebi.pride.px;

import junit.framework.TestCase;
import uk.ac.ebi.pride.px.keyValueReports.KeyValueReportFactory;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-04 14:35
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class ReportFactoryTest extends TestCase {
    public void testGetFactory() throws Exception {
        assertTrue(ReportFactory.getFactory() instanceof KeyValueReportFactory);
    }

}