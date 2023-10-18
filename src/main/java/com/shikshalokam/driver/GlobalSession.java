package com.shikshalokam.driver;

import static com.shikshalokam.utils.logger.DebugLogger.debugLogger;

/**
 * Purpose: Assign driver instance to Global sessions
 *
 */

public class GlobalSession {


    private static final ThreadLocal<GlobalSession> globalSession = new ThreadLocal<>();
    private DriverManager driverManager;

    public static void set(GlobalSession globalSession) {
        GlobalSession.globalSession.set(globalSession);
    }

    public static GlobalSession get() {
        debugLogger().info("On GetAppiumManger Global");
        return globalSession.get();
    }

    public DriverManager getDriverManager() {
        return this.driverManager;
    }

    public void setDriverManager(DriverManager driverManager) {
        debugLogger().info("SetAppiumManager Global");
        this.driverManager = driverManager;
    }


}
