package uk.ac.ebi.pride.px;

import junit.framework.TestCase;
import uk.ac.ebi.pride.px.reports.builders.StringReportBuilder;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-04 16:41
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class CompositeReportTest extends TestCase {
    private CompositeReport subject;
    private StringReportBuilder builder;

    public void setUp() throws Exception {
        super.setUp();
        subject = new CompositeReport();
        builder = new StringReportBuilder();
    }

    public void tearDown() throws Exception {
        // Empty
    }

    public void testAdd() throws Exception {
        // Mainly testing that no exceptions arise while adding reports
        SubmissionRecordReport srecReport = ReportFactory.getFactory().getSubmissionRecordReport();
        srecReport.setSubmissionReference("1-2451345-1345624563467");
        UserFeedbackReport ufReport = ReportFactory.getFactory().getUserFeedbackReport();
        ufReport.setRating(ufReport.RATING_VERY_GOOD);
        ufReport.setComments("testing additional comments");
        subject.add(srecReport);
        subject.add(ufReport);
    }

    public void testSave() throws Exception {
        subject.save(builder);
    }

}