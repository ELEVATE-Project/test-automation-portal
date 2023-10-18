package com.shikshalokam.utils.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogUtils {

	final private BufferedWriter bufferedWriter;

	public LogUtils(final String completeFilePath) throws IOException {
		this.bufferedWriter = new BufferedWriter(new FileWriter(completeFilePath, false));
	}

	final public void log(final String text) {
		this.print(text);
	}

	final private void print(final String text) {
		this.writeToFile(text);
	}

	final private void writeToFile(final String text) {
		try {
			this.bufferedWriter.write(text);
			this.bufferedWriter.newLine();
			this.bufferedWriter.flush();
		} catch (Exception e) {
			System.out.println("while writing logs: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void close() throws IOException {
		this.bufferedWriter.close();
	}
}
