package uk.ac.ebi.pride.px.reports;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.builders
 * Timestamp: 2016-05-03 13:52
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 * ---
 *
 * This is a builder of reports consisting of (key,value) data pairs
 */
public interface ReportBuilder {

    /**
     * Build parts of the report
     * @param key value to act as key
     * @param value value for the given key
     * @throws ReportBuilderException in case of error
     */
    void build(String key, String value) throws ReportBuilderException;

    /**
     * Get the built product, i.e. the report
     * @return the built report
     */
    ReportProduct getProduct() throws ReportBuilderException;
}
