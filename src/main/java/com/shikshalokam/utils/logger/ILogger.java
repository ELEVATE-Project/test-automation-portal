package com.shikshalokam.utils.logger;

public interface ILogger {

	void error(final String msg);

	void warning(final String msg);

	void info(final String msg);

	void debug(final String msg);

	void exception(final Throwable e);

	void expectedException(final Exception e);

}
