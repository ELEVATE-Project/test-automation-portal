package com.shikshalokam.utils.asserts;

import static com.shikshalokam.utils.logger.DebugLogger.debugLogger;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;




@SuppressWarnings("rawtypes")
public class Asserts {

	public WebDriver driver;

	public Asserts(WebDriver webDriver) {
		this.driver = webDriver;
	}

	public void asserts() {
		debugLogger().info("On Asserts");
	}

	public void assertListEquals(List actualList, List expectedList, String listName) {
		assertListContains(actualList, expectedList, listName);
		Assert.assertEquals(actualList.size(), expectedList.size(),
				"Value count in list" + listName + "  didn't match. \nExpected list values:  " + expectedList
						+ ", \nActual List values:  " + actualList);
	}

	public void assertListEqualsWithOrder(List actualList, List expectedList, String listName) {
		assertListEquals(actualList, expectedList, listName);
		Assert.assertEquals(actualList, expectedList,
				"List order didn't match, \nExpected: " + expectedList + "\nActual: " + actualList);
	}

	public void assertListContains(List actualList, List expectedList, String listName) {
		for (Object expectedValue : expectedList) {
			Assert.assertTrue(actualList.contains(expectedValue),
					"Value (" + expectedValue + ") is not present in List '" + listName + "'. \nActual list values: "
							+ actualList + "\nExpected list values: " + expectedList);
		}
	}

	public void assertListNotContains(List actualList, List expectedList, String listName) {
		for (Object expectedValue : expectedList) {
			Assert.assertTrue(!actualList.contains(expectedValue), "Value (" + expectedValue + ") is present in List '"
					+ listName + "'. \nActual list values: " + actualList + "\nExpected list values: " + expectedList);
		}
	}

	public void assertEquals(String actual, String expected, String msg) {
		Assert.assertEquals(actual, expected, msg);
	}

	public void assertNotEquals(String actual, String expected, String msg) {
		Assert.assertNotEquals(actual, expected, msg);
	}

	
	
}
