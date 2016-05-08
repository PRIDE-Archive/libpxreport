package uk.ac.ebi.pride.px.reports;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.products
 * Timestamp: 2016-05-03 14:15
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 * ---
 *
 * This interface defines a produced report, that can be accessible
 */
public interface ReportProduct {

    ReportAccessEndpoint getAccessEndpoint();
}
