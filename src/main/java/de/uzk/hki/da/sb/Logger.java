package de.uzk.hki.da.sb;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

class Logger {

	private File logfile;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	public Logger(String dataPath) {
		
		logfile = new File(dataPath + "/sipbuilder_errors.log");
	}

	public void log(String message) {
		
		try {
			PrintWriter writer = new PrintWriter(
								 new BufferedWriter(
								 new FileWriter(logfile, true)));
			writer.println(dateFormat.format(new Date()) + " " + message + "\n");
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("Failed to create log file " + logfile.getAbsolutePath(), e);
		}
	}
	
	public void log(String message, Exception exception) {
		
		try {
			PrintWriter writer = new PrintWriter(
								 new BufferedWriter(
								 new FileWriter(logfile, true)));
			writer.println(dateFormat.format(new Date()) + " " + message);
			exception.printStackTrace(writer);						
			writer.print("\n");
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException("Failed to create log file " + logfile.getAbsolutePath(), e);
		}
	}
}
