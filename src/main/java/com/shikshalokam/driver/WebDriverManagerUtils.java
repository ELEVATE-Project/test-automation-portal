package com.shikshalokam.driver;

import com.shikshalokam.enums.BrowserType;
import com.shikshalokam.utils.prop.PropUtlis;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static com.shikshalokam.utils.logger.DebugLogger.debugLogger;

import java.util.concurrent.TimeUnit;

/**
 * Purpose: Initilize the WebDriver
 */

public class WebDriverManagerUtils {

    WebDriver driver;

    WebDriverManagerUtils(WebDriver driver) {
        this.driver = driver;
    }


    @SuppressWarnings("deprecation")
	public WebDriver initialization() {
        try {
            debugLogger().info("On initialization ");
            String browserName = PropUtlis.readConfig("browserConfig", "browser");
            String headLess = PropUtlis.readConfig("browserConfig", "headLess");
            if (browserName.equalsIgnoreCase(BrowserType.CHROME.toString())) {
                WebDriverManager.chromedriver().setup();
                if (headLess.equalsIgnoreCase("true")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--window-size=1920,1200");
                    driver = new ChromeDriver(options);
                    debugLogger().debug("Chrome Browser launched in HeadLess mode");
                } else {
                    driver = new ChromeDriver();
                    debugLogger().debug("Chrome Browser launched in Head Mode");
                }
            } else if (browserName.equalsIgnoreCase(BrowserType.FIREFOX.toString())) {
                WebDriverManager.firefoxdriver().setup();
                if (headLess.equalsIgnoreCase("true")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--ignore-certificate-errors");
                    options.addArguments("--window-size=1920,1200");
                    driver = new FirefoxDriver(options);
                    debugLogger().debug("FireFox Browser launched in HeadLess Mode");
                } else {
                    driver = new FirefoxDriver();
                }
                debugLogger().debug("FireFox Browser launched in Head Mode");
            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);  //30
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);   //30
            driver.get(PropUtlis.readConfig("webAppConfig", "appUrl"));
        } catch (Exception e) {
            debugLogger().error("Exception in WebDriverManagerUtils initialization method");
            e.printStackTrace();
        }
        return driver;
    }


}
