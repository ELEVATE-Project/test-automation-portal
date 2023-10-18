package com.shikshalokam.report;

import com.aventstack.extentreports.ExtentTest;

public class ShikshaLokamReport {

	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}

	public static void setExtentTest(ExtentTest extentTest) {
		ShikshaLokamReport.extentTest.set(extentTest);
	}

}
