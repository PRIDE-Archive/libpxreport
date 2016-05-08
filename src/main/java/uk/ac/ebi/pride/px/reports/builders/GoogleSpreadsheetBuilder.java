package uk.ac.ebi.pride.px.reports.builders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.px.reports.ReportBuilder;
import uk.ac.ebi.pride.px.reports.ReportProduct;
import uk.ac.ebi.pride.px.reports.products.GoogleSpreadsheetReportProduct;

import java.util.HashMap;
import java.util.Map;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.builders
 * Timestamp: 2016-05-05 13:03
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class GoogleSpreadsheetBuilder implements ReportBuilder {
    private static final Logger logger = LoggerFactory.getLogger(GoogleSpreadsheetBuilder.class);

    // This is the product, which represents the spreadsheet itself
    private GoogleSpreadsheetReportProduct product;
    // All parameters we need to provide the product
    // This service's name, when accessing google API
    // Internal representation of (key,value) given pairs
    private Map<String, String> store = new HashMap<String, String>();

    /**
     * Constructor, it takes in different parameters in order to be able to use Spreadsheet Service Google API
     * @param pkcsFilePath private key to access google services
     * @param accountId account ID
     * @param spreadsheetTitle title of the spreadsheet to use
     * @param worksheetTitle title of the worksheet that will be the destination for the data
     */
    public GoogleSpreadsheetBuilder(String pkcsFilePath, String accountId, String spreadsheetTitle, String worksheetTitle, String serviceName) {
        product = new GoogleSpreadsheetReportProduct(pkcsFilePath, accountId, spreadsheetTitle, worksheetTitle, serviceName);
    }

    public void build(String key, String value) throws GoogleSpreadsheetBuilderException {
        if ((key != null) && (value != null)) {
            logger.debug("build ('" + key + "', '" + value + "')");
            store.put(key, value);
        }
    }

    public ReportProduct getProduct() throws GoogleSpreadsheetBuilderException {
        logger.debug("Sending data to Google Spreadsheets");
        product.addRow(store);
        return product;
    }
}
