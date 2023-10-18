package com.shikshalokam.report;

import static com.shikshalokam.utils.logger.DebugLogger.debugLogger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.shikshalokam.annotation.Author;

/**
 *  Purpose: overloaded methods as we use it
 *          for send information to report
 * 
 */

public class ExtentTestManager {

	private static ExtentTest extentTest;
	public static ExtentReports extentReports;

	public static void InitReport() {
		debugLogger().info("On InitReports");
		extentReports = ExtentManager.getExtent();
	}



	public synchronized static ExtentTest createTest(String name, String description, String browser, Author author) {
		String testName = name.substring(0, 1).toUpperCase() + name.substring(1);
		extentTest = extentReports.createTest(testName, description).assignCategory(browser)
				.assignAuthor(author.name());
		return extentTest;

	}
}
