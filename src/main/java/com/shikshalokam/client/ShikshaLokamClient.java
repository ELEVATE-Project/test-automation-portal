package com.shikshalokam.client;

import com.aventstack.extentreports.ExtentTest;
import com.shikshalokam.constants.IAutoConst;
import com.shikshalokam.report.ShikshaLokamReport;
import com.shikshalokam.utils.asserts.Asserts;
import com.shikshalokam.utils.gSheet.GoogleSheet;
import com.shikshalokam.utils.gestures.Gestures;
import com.shikshalokam.utils.logger.Logger;
import com.shikshalokam.utils.prop.PropUtlis;
import com.shikshalokam.utils.screenshot.ScreenShot;

import static com.shikshalokam.utils.logger.DebugLogger.debugLogger;

import org.openqa.selenium.WebDriver;

public class ShikshaLokamClient {

    private static final ThreadLocal<ShikshaLokamClient> shikshalokamClient = new ThreadLocal<>();
    private Asserts asserts;
    private Gestures gestures;
    private GoogleSheet googleSheet;
    private Logger logger;
    private PropUtlis propUtils;
    private ScreenShot screenShot;
    private final WebDriver webDriver;

    public ShikshaLokamClient(WebDriver webDriver) {
        debugLogger().info("On Constructor ShikshaLokamClient");
        this.webDriver = webDriver;
    }

    public static void setShikshaLokamClient(ShikshaLokamClient slClient) {
        debugLogger().info("On SetGoodWorkerClient");
        shikshalokamClient.set(slClient);
    }

    public static ShikshaLokamClient get() {
        debugLogger().info("On ShikshaLokamClient");
        return shikshalokamClient.get();

    }
    
    
    

    public WebDriver driver() throws Exception {
        if (this.webDriver == null) {
            debugLogger().info("On Driver");
            return this.webDriver;
        }
        return this.webDriver;
    }

    public Asserts getAsserts() {
        if (this.asserts == null) {
            this.asserts = new Asserts(this.webDriver);
            debugLogger().info("On GetAsserts");
        }
        return asserts;
    }

    public GoogleSheet getGoogleSheet() {
        if (this.googleSheet == null) {
            this.googleSheet = new GoogleSheet();
            debugLogger().info("On GetGoogleSheet");
        }
        return googleSheet;
    }

    public Logger logger() {
        try {
            if (this.logger == null) {
                this.logger = new Logger(IAutoConst.APP_LOG);
                debugLogger().info("On GetLogUtlis");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return logger;
    }

    public PropUtlis getPropUtils() {
        debugLogger().info("On GetPropUtils");
        if (this.propUtils == null) {
            this.propUtils = new PropUtlis();
        }
        return propUtils;
    }

    public ScreenShot getScreenShot() throws Exception {
        debugLogger().info("on GetScreenShot");
        if (this.screenShot == null) {
            this.screenShot = new ScreenShot(this.webDriver);
        }
        return screenShot;
    }

    public ExtentTest report() {
        return ShikshaLokamReport.getExtentTest();
    }

    public Gestures gestures() {
        if (this.gestures == null) {
            this.gestures = new Gestures(this.webDriver);
            debugLogger().info("On Gestures");
        }
        return this.gestures;
    }


}
