package uk.ac.ebi.pride.px.keyValueReports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.pride.px.Report;
import uk.ac.ebi.pride.px.reports.ReportBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-03 13:59
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public abstract class KeyValueReport implements Report {
    private static final Logger logger = LoggerFactory.getLogger(KeyValueReport.class);

    // (Key,value) internal storage
    private Map<String, String> kvStore;

    public KeyValueReport() {
        kvStore = new HashMap<String, String>();
    }

    public void add(Report report) {
        logger.info("Report Aggregation is not supported for this kind of report");
    }

    /**
     * It stores a (key,value) pair
     * @param key key
     * @param value value
     * @return the given value or, if the key was already associated with a value, it returns that previous value
     */
    protected String storeKeyValuePair(String key, String value) {
        String previous = kvStore.put(key, value);
        if (previous != null) {
            return previous;
        }
        return value;
    }

    /**
     * Get the value for the given key
     * @param key key
     * @return the value associated with the given key, null if there is no value
     */
    protected String getValueForKey(String key) {
        if (key != null) {
            return kvStore.get(key);
        }
        return null;
    }

    /**
     * Get the list of keys of data stored in this report.
     * Delegate to subclasses
     * @return a list of keys
     */
    abstract List<String> getKeys();

    /**
     * This will clear all the (key,value) pairs that could be stored
     */
    protected void clearKeyValuePairs() {
        kvStore.clear();
    }

    /**
     * Main algorithm for saving a (key,value) report
     * @param builder ReportBuilder to use when saving the report
     * @return true if success, false otherwise
     */
    public boolean save(ReportBuilder builder) {
        for (String key: getKeys()) {
            builder.build(key, getValueForKey(key));
        }
        return false;
    }
}
