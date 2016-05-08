package uk.ac.ebi.pride.px.reports;

import java.io.Serializable;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.products
 * Timestamp: 2016-05-04 9:57
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 * ---
 *
 * This Interface represents an access object to a built report (product)
 */
public interface ReportAccessEndpoint extends Serializable {

    String getUrl();
    Object dumpReportContent();
}
