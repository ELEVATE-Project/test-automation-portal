package com.shikshalokam.driver;

import org.openqa.selenium.WebDriver;

import static com.shikshalokam.utils.logger.DebugLogger.debugLogger;

import java.util.concurrent.TimeUnit;

/**
 * Purpose: To initilize all the required
 * variables and set driver instance
 */

public class DriverManager {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private WebDriver driver;

    public DriverManager() {
    }


    public static void setDriverToThreadLocal(WebDriver driver) {
        debugLogger().info("On SetDriverToThreadLocal");
        webDriver.set(driver);
    }

    public static WebDriver getDriver() {
        debugLogger().info("On GetAppiumDriver");
        return webDriver.get();
    }

    @SuppressWarnings("deprecation")
	public void setDriver() throws Exception {
        try {
            debugLogger().info("on **************** setDriver");
            WebDriverManagerUtils webDriverManagerUtils = new WebDriverManagerUtils(this.driver);
            this.driver = webDriverManagerUtils.initialization();
            DriverManager.setDriverToThreadLocal(this.driver);
            debugLogger().info("Driver is set");
            this.driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createDriver() throws Exception {
        try {
            debugLogger().info("On Create Driver...");
            setDriver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void quitDriver() throws Exception {
        if (this.driver != null) {
            debugLogger().info("On QuitDriver");
            this.driver.quit();
            debugLogger().info("***************************QUTTING**************************");
        }
    }
   


}
