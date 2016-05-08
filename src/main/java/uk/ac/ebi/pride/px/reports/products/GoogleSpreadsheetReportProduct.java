package uk.ac.ebi.pride.px.reports.products;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.px.reports.ReportAccessEndpoint;
import uk.ac.ebi.pride.px.reports.ReportProduct;
import uk.ac.ebi.pride.px.reports.builders.GoogleSpreadsheetBuilder;
import uk.ac.ebi.pride.px.reports.builders.GoogleSpreadsheetBuilderException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px.reports.products
 * Timestamp: 2016-05-05 13:04
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 * ---
 *
 * This class implements the report product and the access endpoint as well
 */
public class GoogleSpreadsheetReportProduct implements ReportProduct, ReportAccessEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(GoogleSpreadsheetBuilder.class);

    // Google credentials for accessing Google Spreadsheets
    private GoogleCredential credential;
    // Spreadsheet Service
    private SpreadsheetService service;
    private String serviceName;
    // Spreadsheet URL
    // NOTE - Should we parameterize this, as it may change in the future? But it is very likely that the API itself will
    // change as well
    private static final String spreadsheetFeedUrl = "https://spreadsheets.google.com/feeds/spreadsheets/private/full";
    // Scopes Array
    // NOTE - Should we parameterize this, as it may change in the future? But it is very likely that the API itself will
    // change as well
    private String[] scopesArray = {"https://spreadsheets.google.com/feeds", "https://spreadsheets.google.com/feeds/spreadsheets/private/full", "https://docs.google.com/feeds"};
    // P12 authorization certificate
    private String pkcsFilePath;
    // Account ID
    private String accountId;
    // Spreadsheet title, and worksheet title
    private String spreadsheetTitle;
    private String worksheetTitle;
    // Computed Values
    private SpreadsheetFeed feed;
    private SpreadsheetEntry currentSpreadsheet;
    private WorksheetEntry currentWorksheet;

    public GoogleSpreadsheetReportProduct(String pkcsFilePath, String accountId, String spreadsheetTitle, String worksheetTitle, String serviceName) {
        this.pkcsFilePath = pkcsFilePath;
        this.accountId = accountId;
        this.spreadsheetTitle = spreadsheetTitle;
        this.worksheetTitle = worksheetTitle;
        this.serviceName = serviceName;
        service = null;
        credential = null;
        feed = null;
        currentSpreadsheet = null;
        currentWorksheet = null;
    }

    /**
     * Get Google Credentials for the given configuration according to this object's attributes
     *
     * @return Google Credentials
     */
    private GoogleCredential getCredential() throws GoogleSpreadsheetBuilderException {
        if (credential == null) {
            // Lazy instantiation
            logger.debug("Lazy instantiation of Google Credentials");
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();
            File p12 = new File(pkcsFilePath);
            logger.debug("Using PKCS File at '" + p12.getAbsolutePath() + "'");
            final List scopes = Arrays.asList(scopesArray);
            logger.debug("Account ID '" + accountId + "'");
            try {
                credential = new GoogleCredential.Builder()
                        .setTransport(httpTransport)
                        .setJsonFactory(jsonFactory)
                        .setServiceAccountId(accountId)
                        .setServiceAccountScopes(scopes)
                        .setServiceAccountPrivateKeyFromP12File(p12)
                        .build();
            } catch (GeneralSecurityException e) {
                logger.error("General Security Exception");
                throw new GoogleSpreadsheetBuilderException(e);
            } catch (IOException e) {
                logger.error("IO Exception");
                throw new GoogleSpreadsheetBuilderException(e);
            }
        }
        return credential;
    }

    /**
     * Get the Spreadsheet instance, using the configured service name, and credentials
     *
     * @return Google API Spreadsheet service instance, already authorized
     */
    private SpreadsheetService getService() {
        if (service == null) {
            // Lazy instantiation
            logger.debug("Instantiating Spreadsheet Service");
            service = new SpreadsheetService(serviceName);
            logger.debug("Setting oAuth 2.0 credentials");
            service.setOAuth2Credentials(getCredential());
        }
        return service;
    }

    private SpreadsheetFeed getFeed() {
        // Get the feed to locate Spreadsheets
        logger.debug("Getting feed");
        if (feed == null) {
            try {
                logger.debug("Instantiating Service Feed from Service");
                feed = getService().getFeed(new URL(spreadsheetFeedUrl), SpreadsheetFeed.class);
            } catch (IOException e) {
                logger.error("IO Exception when getting feed for the spreadsheet");
                throw new GoogleSpreadsheetBuilderException(e);
            } catch (ServiceException e) {
                logger.error("Service Exception when getting feed for the spreadsheet");
                throw new GoogleSpreadsheetBuilderException(e);
            }
        }
        return feed;
    }

    private void clearCurrentWorksheet() {
        currentWorksheet = null;
    }
    private void clearCurrentSpreadsheet() {
        clearCurrentWorksheet();
        currentSpreadsheet = null;
    }

    public SpreadsheetEntry getCurrentSpreadsheet() {
        return currentSpreadsheet;
    }

    public WorksheetEntry getCurrentWorksheet() {
        return currentWorksheet;
    }

    private SpreadsheetEntry getSpreadsheetByTitle(String title) {
        if ((currentSpreadsheet == null) || (!title.equals(currentSpreadsheet.getTitle().getPlainText()))) {
            logger.debug("Locating spreadsheet '" + title + "'");
            clearCurrentSpreadsheet();
            // Get the spreadsheets in the feed
            List<SpreadsheetEntry> spreadsheets = getFeed().getEntries();
            // Look for the configured spreadsheet
            if (spreadsheets.size() == 0) {
                throw new GoogleSpreadsheetBuilderException("There are no spreadsheets to connect to");
            }
            for (SpreadsheetEntry item :
                    spreadsheets) {
                if (spreadsheetTitle.equals(item.getTitle().getPlainText())) {
                    logger.debug("Spreadsheet '" + spreadsheetTitle + "' FOUND!");
                    currentSpreadsheet = item;
                    break;
                }
            }
            // Check whether we found the spreadsheet or not
            if (currentSpreadsheet == null) {
                String msg = "Spreadsheet '" + spreadsheetTitle + "' NOT FOUND!";
                logger.error(msg);
                throw new GoogleSpreadsheetBuilderException(msg);
            }
        }
        return currentSpreadsheet;
    }

    private WorksheetEntry getWorksheetByTitle(String title) throws IOException, ServiceException {
        if ((currentWorksheet == null) || (!title.equals(currentWorksheet.getTitle().getPlainText()))) {
            if (currentSpreadsheet == null) {
                String msg = "Worksheet '" + title + "' CANNOT BE FOUND if no spreadsheet has been selected!!!";
                logger.error(msg);
                throw new GoogleSpreadsheetBuilderException(msg);
            }
            // Locate the specified worksheet
            List<WorksheetEntry> worksheets = currentSpreadsheet.getWorksheets();
            WorksheetEntry worksheetEntry = null;
            for (WorksheetEntry wentry :
                    worksheets) {
                if (worksheetTitle.equals(wentry.getTitle().getPlainText())) {
                    logger.debug("Worksheet '" + worksheetTitle + "' FOUND!!!");
                    worksheetEntry = wentry;
                    break;
                }
            }
            // Check if we found the worksheet we were looking for
            if (worksheetEntry == null) {
                String msg = "Worksheet '" + worksheetTitle + "' NOT FOUND!!!";
                logger.error(msg);
                throw new GoogleSpreadsheetBuilderException(msg);
            }
            currentWorksheet = worksheetEntry;
        }
        return currentWorksheet;
    }

    public void addRow(Map<String, String> store) {
        SpreadsheetEntry spreadsheetEntry = getSpreadsheetByTitle(spreadsheetTitle);
        WorksheetEntry worksheetEntry = null;
        try {
            worksheetEntry = getWorksheetByTitle(worksheetTitle);
        } catch (IOException e) {
            String msg = "IOException while trying to get the worksheet entry";
            logger.error(msg);
            throw new GoogleSpreadsheetBuilderException(msg);
        } catch (ServiceException e) {
            String msg = "ServiceException while trying to get the worksheet entry";
            logger.error(msg);
            throw new GoogleSpreadsheetBuilderException(msg);
        }
        // We found the worksheet, now let's add the new row
        // WARNING - TODO - We shoud implement something that checks if there is a header in the spreadsheet, creating it otherwise
        // WARNING - We proceed assuming the spreadsheet already contains a header
        // Get the worksheet feed URL
        URL listFeedUrl = worksheetEntry.getListFeedUrl();
        // Get access to the rows as a ListFeed
        ListFeed listFeed = null;
        try {
            listFeed = getService().getFeed(listFeedUrl, ListFeed.class);
        } catch (IOException e) {
            String msg = "IOException while trying to get the feed for the rows in the spreadsheet";
            logger.error(msg);
            throw new GoogleSpreadsheetBuilderException(msg);
        } catch (ServiceException e) {
            String msg = "ServiceException while trying to get the feed for the rows in the spreadsheet";
            logger.error(msg);
            throw new GoogleSpreadsheetBuilderException(msg);
        }
        // Create a new row
        logger.debug("Creating the new row in the spreadsheet worksheet");
        ListEntry row = new ListEntry();
        for (String key :
                store.keySet()) {
            String value = store.get(key);
            logger.debug("New (key,value) = ('" + key + "', '" + value + "')");
            row.getCustomElements().setValueLocal(key, value);
        }
        // Insert the row in the worksheet
        try {
            row = getService().insert(worksheetEntry.getListFeedUrl(), row);
        } catch (IOException e) {
            String msg = "IOException while inserting new rows in a spreadsheet";
            logger.error(msg);
            throw new GoogleSpreadsheetBuilderException(msg);
        } catch (ServiceException e) {
            String msg = "Service Exception while inserting new rows in a spreadsheet";
            e.printStackTrace();
            logger.error(msg);
            throw new GoogleSpreadsheetBuilderException(msg);
        }
        logger.info("The new row has been inserted in the spreadsheet");
    }

    public String getUrl() {
        return getCurrentWorksheet().getHtmlLink().getHref();
    }

    public Object dumpReportContent() {
        return "This operation is not supported";
    }

    public ReportAccessEndpoint getAccessEndpoint() {
        return this;
    }
}
