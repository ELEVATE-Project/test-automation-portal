package com.shikshalokam.listners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.shikshalokam.annotation.Author;
import com.shikshalokam.client.ShikshaLokamClient;
import com.shikshalokam.constants.IAutoConst;
import com.shikshalokam.driver.DriverManager;
import com.shikshalokam.driver.GlobalSession;
import com.shikshalokam.report.ExtentManager;
import com.shikshalokam.report.ExtentTestManager;
import com.shikshalokam.report.ShikshaLokamReport;
import com.shikshalokam.utils.prop.PropUtlis;

import static com.shikshalokam.utils.logger.DebugLogger.debugLogger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** Purpose: initilize and start 
 * server
 */

public class TestListeners implements ITestListener {

    public static String mode;

    public void onTestStart(ITestResult result) {
        try {
            String className = result.getMethod().getTestClass().getName();
            String testName = result.getMethod().getMethodName() + "";
            debugLogger().info(className.substring(20));
            debugLogger().info("*********************onTestStart********************* " + testName);
            Author author = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Author.class);
            String description = result.getMethod().getDescription();
            ExtentTest testReport = ExtentTestManager.createTest(testName, description, PropUtlis.readConfig("browserConfig", "browser"), author);
            ShikshaLokamReport.setExtentTest(testReport);
            GlobalSession.get().getDriverManager().createDriver();
            ShikshaLokamClient.setShikshaLokamClient(new ShikshaLokamClient(DriverManager.getDriver()));
            debugLogger().info("instance created for Web");

        } catch (Exception e) {
            debugLogger().error("Exception onTestStart: " + e.getMessage());
            e.printStackTrace();
            
        }

    }

    public void onTestSuccess(ITestResult result) {
        try {
            debugLogger().info("@onTestSuccess");

            ShikshaLokamReport.getExtentTest().log(Status.PASS, result.getMethod().getMethodName() + " Passed");
            GlobalSession.get().getDriverManager().quitDriver();
            debugLogger().info("Driver closed");
        } catch (Exception e) {
            debugLogger().error("Exception onTestSuccess: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onTestFailure(ITestResult result) {
        try {
            debugLogger().error("OnTestFailure");
            debugLogger().error(result.getThrowable().toString());
            String base64img = ShikshaLokamClient.get().getScreenShot().captureBase64();
            ShikshaLokamReport.getExtentTest().fail(
                    result.getMethod().getMethodName() + " Failed " + result.getThrowable().getLocalizedMessage(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64img).build());


            GlobalSession.get().getDriverManager().quitDriver();
        } catch (Exception e) {
            debugLogger().error("Exception onTestFailure: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public void onStart(ITestContext context) {
        try {
            debugLogger().info("*********************onStart********************* mode " + mode);
            IAnnotation.enableTests();
            GlobalSession.set(new GlobalSession());
            DriverManager driverManager = new DriverManager();
            if (true) {
                GlobalSession.get().setDriverManager(driverManager);
                try {
                    if (mode == null || mode.equalsIgnoreCase(IAutoConst.MODE_LOCAL)) {
                        debugLogger().info("Running in local");
                        GlobalSession.get().setDriverManager(driverManager);                    }
                } catch (Exception e) {
                    debugLogger().error("Exception onStart: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            ExtentTestManager.InitReport();
        } catch (Exception e) {
            e.printStackTrace();
            debugLogger().error("Exception onStart: " + e.getMessage());
        }
    }

    public void onFinish(ITestContext context) {
        try {
            debugLogger().info("*********************onFinish*********************");
            ExtentManager.getExtent().flush();
        } catch (Exception e) {
            debugLogger().error("Exception onFinish: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
