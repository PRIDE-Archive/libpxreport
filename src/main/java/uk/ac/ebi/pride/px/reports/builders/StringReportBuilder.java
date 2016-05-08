package uk.ac.ebi.pride.px.reports.builders;

import uk.ac.ebi.pride.px.reports.ReportBuilder;
import uk.ac.ebi.pride.px.reports.ReportBuilderException;
import uk.ac.ebi.pride.px.reports.ReportProduct;
import uk.ac.ebi.pride.px.reports.products.StringReportProduct;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.builders
 * Timestamp: 2016-05-04 14:02
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class StringReportBuilder implements ReportBuilder {
    // Constants
    private static final String ENTRY_SEPARATOR = ";";
    private StringBuilder stringBuilder = new StringBuilder();

    public void build(String key, String value) throws ReportBuilderException {
        if (stringBuilder.length() > 0) {
            stringBuilder.append(ENTRY_SEPARATOR);
        }
        stringBuilder.append(key).append("=").append(value);
    }

    public ReportProduct getProduct() {
        return new StringReportProduct(stringBuilder.toString());
    }
}
