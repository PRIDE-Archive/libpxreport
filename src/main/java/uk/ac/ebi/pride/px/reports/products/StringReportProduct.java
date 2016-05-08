package uk.ac.ebi.pride.px.reports.products;

import uk.ac.ebi.pride.px.reports.ReportAccessEndpoint;
import uk.ac.ebi.pride.px.reports.ReportProduct;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.products
 * Timestamp: 2016-05-04 13:50
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class StringReportProduct implements ReportProduct {
    private StringAccessEndpoint accessEndpoint;

    public StringReportProduct(String result) {
        accessEndpoint = new StringAccessEndpoint(result);
    }

    public ReportAccessEndpoint getAccessEndpoint() {
        return accessEndpoint;
    }
}
