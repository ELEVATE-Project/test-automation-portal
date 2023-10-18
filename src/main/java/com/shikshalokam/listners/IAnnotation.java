package com.shikshalokam.listners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.shikshalokam.utils.gSheet.TestData;

public class IAnnotation implements IAnnotationTransformer {

	ArrayList<String> testName = null;
	public static int enableTestCount = 0;

	@SuppressWarnings({ "rawtypes" })
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		try {
			if (testName == null) {
				testName = TestData.getFullGoogleSheetValuesAsArrayList("TestCases!A:B");
				System.out.println("testName " + testName.toString());
			}
			if (containsString(testMethod.getName(), testName)) {
				annotation.setEnabled(true);
				enableTestCount++;
			} else {
				annotation.setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean containsString(String testString, ArrayList<String> list) {
		try {
			if (list.toString().contains(testString)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	static void enableTests() throws Exception {
		if (enableTestCount == 0) {
			throw new Exception("None of the Test cases Matched");
		}
	}

}