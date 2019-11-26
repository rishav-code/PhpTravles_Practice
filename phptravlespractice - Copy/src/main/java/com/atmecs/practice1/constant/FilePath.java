package com.atmecs.practice1.constant;

import java.io.File;

/**
 * FilePath
 * Constant
 * In this Class, All the file paths which has been used is saved
 * @author rishav.kumar
 *
 */
public class FilePath {

	public final static String USER_HOME = System.getProperty("user.dir") + File.separator;
	public final static String RESOURCES_HOME = USER_HOME + "src" + File.separator + "test" + File.separator
			+ "resources" + File.separator;
	public final static String LOCATOR_HOME = RESOURCES_HOME + "locators" + File.separator;
	public final static String LIB_HOME = USER_HOME + "lib" + File.separator;

	public final static String CONFIG_FILE = RESOURCES_HOME + "testdata" + File.separator + "config.properties";
	public final static String CHROME_PATH = LIB_HOME + "chromedriver.exe";
	public final static String FIREFOX_PATH=LIB_HOME + "geckodriver.exe";
	public final static String IE_PATH = LIB_HOME + "IEDriverServer.exe";
	public final static String LOCATOR_FILE = LOCATOR_HOME + "locators.properties";
	public final static String TESTDATA_FILE = RESOURCES_HOME + "testdata" + File.separator + "phpdata.xlsx";
	public final static String TESTDATA_FILE2 = RESOURCES_HOME + "testdata" + File.separator + "dateinputs.xlsx";
	public final static String VALIDATION_FILE = RESOURCES_HOME + "testdata" + File.separator+
			 "validationdata.properties";
	public final static String LOG4J_FILE= RESOURCES_HOME+"log4j"+File.separator+"log4j.properties";
	public static final String EXTENT_REPORT_FILE = USER_HOME + "test-output" + File.separator + "ExtentReport.html";
	public static final String EXTENT_CONFIG = USER_HOME + "extent-config.xml";
	public static final String FAILED_SCREENSHOT_FILE = USER_HOME + "FailedTestScreenShots" + File.separator;
}
