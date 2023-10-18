package com.shikshalokam.constants;

import java.io.File;

/**
 *Purpose: paths required for projects
 */

public interface IAutoConst {

    String PROJECT_PATH = System.getProperty("user.dir");

    String MODE_LOCAL = "local";

    String MODE_CLOUD = "cloud"; //Not used in Framework

    String CONFIG_JSON = PROJECT_PATH + File.separator + "config" + File.separator + "config.json";

    String REPORT_DIR_PATH = PROJECT_PATH + File.separator + "Reports" + File.separator
            + "ExtentReport.html";

    String EXTENT_CONFIG = PROJECT_PATH + File.separator + "src" + File.separator + "main"
            + File.separator + "resources" + File.separator + "extent-config.xml";

    String APP_LOG = PROJECT_PATH + File.separator + "logs" + File.separator + "app.log";

    String DEBUG_LOG_PATH = PROJECT_PATH + File.separator + "logs" + File.separator + "debug.log";

    String GOOGLE_AUTHFILE = PROJECT_PATH + File.separator + "src" + File.separator + "main"
            + File.separator + "resources" + File.separator + "sikshalokam-8542b87e21f7.p12";


}
