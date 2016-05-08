package uk.ac.ebi.pride.px;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Project: libpxreport
 * Package: uk.ac.ebi.pride.px
 * Timestamp: 2016-05-08 17:55
 * ---
 * © 2016 Manuel Bernal Llinares <mbdebian@gmail.com>
 * All rights reserved.
 */
public class ReportConfigManager {
    private static final Logger logger = LoggerFactory.getLogger(ReportConfigManager.class);

    private static final String configFileName = "config.properties";

    // Singleton
    private static final ReportConfigManager instance = new ReportConfigManager();
    // Properties
    private Properties prop = null;

    private ReportConfigManager() {
        InputStream is = getClass().getClassLoader().getResourceAsStream(configFileName);
        if (is != null) {
            prop = new Properties();
            try {
                prop.load(is);
            } catch (IOException e) {
                prop = null;
                String msg = "There was a problem loading configuration file '" + configFileName + "'";
                logger.error(msg);
            }
        }
    }

    // Get property
    public static String getConfigurationProperty(String key) {
        String value = "";
        if ((instance.prop != null) && (key != null)) {
            value = instance.prop.getProperty(key);
            if (value == null) {
                value = "";
            }
        }
        return value;
    }
}
