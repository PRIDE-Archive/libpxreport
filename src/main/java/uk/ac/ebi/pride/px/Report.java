package uk.ac.ebi.pride.px;

import uk.ac.ebi.pride.px.reports.ReportBuilder;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-03 13:50
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 * ---
 *
 * Report Interface
 */
public interface Report {
    /**
     * Add reports to a report
     *
     * @param report report to add
     */
    void add(Report report);

    /**
     * Save this report using the given ReportBuilder
     * @param builder ReportBuilder to use when saving the report
     * @return true if success, false otherwise
     */
    boolean save(ReportBuilder builder);
}
