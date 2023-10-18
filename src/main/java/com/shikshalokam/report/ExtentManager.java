package com.shikshalokam.report;

import static com.shikshalokam.utils.logger.DebugLogger.debugLogger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.shikshalokam.constants.IAutoConst;

/**
 * Purpose: Create instance for report and
 *          set extent configs
 */

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getExtent() {
		if (extent == null) {
			try {
				debugLogger().info("On GetExtent");
				extent = new ExtentReports();
				extent.attachReporter(getHtmlReporter());
			} catch (Exception e) {
				debugLogger().info("Exception while creating report html file.");
				e.printStackTrace();

			}
		}

		return extent;

	}

	private static ExtentHtmlReporter getHtmlReporter() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(IAutoConst.REPORT_DIR_PATH);
		htmlReporter.config().enableTimeline(false);
		htmlReporter.config().setReportName("Test Automation");
		htmlReporter.loadXMLConfig(IAutoConst.EXTENT_CONFIG);
		return htmlReporter;
	}

}
