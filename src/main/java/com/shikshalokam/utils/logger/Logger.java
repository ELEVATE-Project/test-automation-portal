package com.shikshalokam.utils.logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

import com.aventstack.extentreports.Status;
import com.shikshalokam.client.ShikshaLokamClient;
import com.shikshalokam.report.ShikshaLokamReport;

/**
 * Purpose: To initilize application logs
 * 
 */

public class Logger implements ILogger {

	private LogUtils logUtils;

	final private static boolean error = true;
	final private static boolean warning = true;
	final private static boolean info = false;
	final private static boolean debug = true;
	final private static boolean exception = true;
	final private static boolean printAll = true;
	public String path;

	enum LogType {
		ERROR, WARNING, INFO, DEBUG, EXCEPTION, EXCEPTION_WARNING;
	}

	public Logger(final String path) {
		this.path = path;
		try {
			this.logUtils = new LogUtils(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	final public void error(final String msg) {
		if (error) {
			printMessage(msg, LogType.ERROR);
		}
	}

	final public void warning(final String msg) {
		if (warning) {
			printMessage(msg, LogType.WARNING);

		}
	}

	final public void info(final String msg) {
		if (info || printAll) {
			printMessage(msg, LogType.INFO);
		}
	}

	final public void infoandReport(final String msg) {
		if (info || printAll) {
			printMessage(msg, LogType.INFO);
			ShikshaLokamReport.getExtentTest().log(Status.INFO, msg);
		}
	}

	final public void debug(final String msg) {
		if (debug) {
			printMessage(msg, LogType.DEBUG);
		}
	}

	public void exception(final Throwable e) {
		if (exception) {
			printMessage(getStackTrace(e), LogType.EXCEPTION);
			e.printStackTrace();
		}
	}

	public void expectedException(final Exception e) {
		if (warning) {
			System.out.println("jh");
			final StringBuilder message = new StringBuilder("Expected Exception: ").append(getStackTrace(e));
			printMessage(message.toString(), LogType.EXCEPTION_WARNING);
			e.printStackTrace();
		}
	}

	public static void logAndReportInfo(String msg) throws Exception {
		ShikshaLokamReport.getExtentTest().log(Status.INFO, msg);
		ShikshaLokamClient.get().logger().info(msg);
	}

	public static void logAndReportError(String msg) throws Exception {
		ShikshaLokamReport.getExtentTest().log(Status.ERROR, msg);
		ShikshaLokamClient.get().logger().error(msg);
	}

	public static void logAndReportWarning(String msg) throws Exception {
		ShikshaLokamReport.getExtentTest().log(Status.WARNING, msg);
		ShikshaLokamClient.get().logger().warning(msg);
	}

	public static void logAndReportPass(String msg) throws Exception {
		ShikshaLokamReport.getExtentTest().log(Status.PASS, msg);
		ShikshaLokamClient.get().logger().info(msg);
	}

	private String getStackTrace(final Throwable e) {
		String trace = "NotFound";
		try {
			final StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new PrintWriter(stringWriter));
			trace = stringWriter.toString();
		} catch (Exception e2) {
			this.error("Exception while finding trace.");
			e.printStackTrace();
		}

		return trace;

	}

	private void printMessage(final String msg, final LogType logType) {
		final StringBuilder logText = new StringBuilder(Calendar.getInstance().getTime().toString()).append(" ");
		logText.append(logType.toString()).append(" ");
		logText.append(getLogInfo()).append(" ");
		logText.append(msg);
		final StringBuilder consoleText = new StringBuilder();
		consoleText.append(logText);
		System.out.println(consoleText);
		System.out.println();
		this.logUtils.log(logText.toString());
	}

	final private String getLogInfo() {
		final StackTraceElement calledClass = Thread.currentThread().getStackTrace()[4];
		final StringBuilder builder = new StringBuilder();
		builder.append(calledClass.getClassName().substring(calledClass.getClassName().lastIndexOf(".") + 1,
				calledClass.getClassName().length()));
		builder.append("-").append(calledClass.getMethodName()).append("-").append(calledClass.getLineNumber());
		return builder.toString();
	}

	final public void close() throws IOException {
		this.logUtils.close();
	}

}