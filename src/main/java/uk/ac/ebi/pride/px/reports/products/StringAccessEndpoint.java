package uk.ac.ebi.pride.px.reports.products;

import uk.ac.ebi.pride.px.reports.ReportAccessEndpoint;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.products
 * Timestamp: 2016-05-04 13:53
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class StringAccessEndpoint implements ReportAccessEndpoint {
    private String builtReport;

    public StringAccessEndpoint(String builtReport) {
        this.builtReport = builtReport;
    }

    public String getUrl() {
        return null;
    }

    public Object dumpReportContent() {
        return builtReport;
    }

    @Override
    public String toString() {
        return "StringAccessEndpoint{" +
                "builtReport='" + builtReport + '\'' +
                '}';
    }
}
