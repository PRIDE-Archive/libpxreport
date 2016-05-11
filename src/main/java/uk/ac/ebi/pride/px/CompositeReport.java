package uk.ac.ebi.pride.px;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.px.reports.ReportBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-04 16:37
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class CompositeReport implements Report {
    private static final Logger logger = LoggerFactory.getLogger(CompositeReport.class);
    private List<Report> reports = new ArrayList<Report>();

    public void add(Report report) {
        logger.debug("Adding report '" + Report.class + "' to composite");
        reports.add(report);
    }

    public boolean save(ReportBuilder builder) {
        logger.debug("Saving " + reports.size() + " report(s)");
        for (Report report : reports) {
            if (!report.save(builder))
                return false;
        }
        return true;
    }
}
