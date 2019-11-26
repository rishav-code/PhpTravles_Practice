package com.atmecs.practice1.reports;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

import com.atmecs.practice1.constant.FilePath;
/**
 * Logreport class
 * This class Generates Log report
 * @author rishav.kumar
 *
 */

public class LogReport {
	Logger logger = null;
	/*
	 * constructor used to get the logger to load the configuration file
	 */

	public LogReport() {
		getlogger();
		logger = Logger.getLogger(LogReport.class.getName());
	}
	/**
	 * method loads the config file from the filepath
	 */
	public void getlogger() {
		PropertyConfigurator.configure(FilePath.LOG4J_FILE);
	}

	/**
	 * the method takes input as string message
	 * 
	 * @param message is printed on the console
	 */
	public void info(String message) {
		logger.info(message);
		Reporter.log(message);

	}

}
