package uk.ac.ebi.pride.px.reports.builders;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.px.ReportConfigManager;
import uk.ac.ebi.pride.px.reports.ReportProduct;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.builders
 * Timestamp: 2016-05-08 15:45
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class GoogleSpreadsheetBuilderTest extends TestCase {
    // NOTE - Well, we don't usually put a logger in an automated test, but this is a quick/difficult test whose results
    // I need to visually inspect
    private static final Logger logger = LoggerFactory.getLogger(GoogleSpreadsheetBuilderTest.class);
    // TODO - All these testing parameters should be in an external configuration file used when running the testing goal
    // PKCS private access key
    private String pkcsFilePath = ReportConfigManager.getConfigurationProperty("pkcs.filename");
    // Account ID for testing
    private String accountId = ReportConfigManager.getConfigurationProperty("account.id");
    // Client Service Name
    private String serviceName = ReportConfigManager.getConfigurationProperty("client.service.name");
    // Spreadsheet title
    private String spreadsheetTittle = ReportConfigManager.getConfigurationProperty("spreadsheet.title");
    // Worksheet title
    private String worksheetTitle = ReportConfigManager.getConfigurationProperty("spreadsheet.worksheet.title");
    // Testing subject
    private GoogleSpreadsheetBuilder subject;

    public void setUp() throws Exception {
        super.setUp();
        // WARNING - This is run before every fucking test method
        // TODO - Load the parameters for the test from some external configuration file
        logger.debug("UnitTests setUp");
        subject = new GoogleSpreadsheetBuilder(pkcsFilePath, accountId, spreadsheetTittle, worksheetTitle, serviceName);
    }

    public void tearDown() throws Exception {
        // Empty
    }

    public void testBuildAndGetProduct() throws Exception {
        subject.build("submissionReference", "1-23476-23874685_unit_testing");
        subject.build("userFeedbackRating", "5");
        subject.build("userFeedbackComments", "Comment from automated unit tests of the GoogleSpreadsheetBuilder for libpxreport");
        ReportProduct product = subject.getProduct();
        //logger.debug("Product as String: " + product.getAccessEndpoint().toString());
        //logger.debug("Product access endpoint: " + product.getAccessEndpoint().getUrl());
        //assertTrue(true);
    }
}