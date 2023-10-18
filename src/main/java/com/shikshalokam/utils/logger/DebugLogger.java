package com.shikshalokam.utils.logger;

import com.shikshalokam.constants.IAutoConst;

/**
 *  Purpose: To initilize debug logs for
 *          Debugging and Trobleshooting
 */

public class DebugLogger {

	private static Logger logger;

	public static ILogger debugLogger() {
		if (logger == null)
			logger = new Logger(IAutoConst.DEBUG_LOG_PATH);
		return logger;
	}

}
