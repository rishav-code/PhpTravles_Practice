package com.atmecs.practice1.reports;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

import com.atmecs.practice1.constant.FilePath;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporterNG  implements IReporter {
	public WebDriver driver;
	public ExtentReports extent;
	public static ExtentTest test;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputdirectory) {
		extent = new ExtentReports(FilePath.EXTENT_REPORT_FILE, true);

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				try {
					buildTestNodes(context.getPassedTests(), LogStatus.PASS);
					buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
					buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

		extent.flush();
		extent.close();
	}

	public void buildTestNodes(IResultMap tests, LogStatus status) throws Exception {
		

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
                
                String message = "Test " + status.toString().toLowerCase() + "ed";
                if (result.getThrowable() != null)
                message = result.getThrowable().getClass() +": "+ result.getThrowable().getMessage();
                test.log(status, message);
                for (String testMessage : Reporter.getOutput(result))
                {
                	test.log(LogStatus.INFO, testMessage);
                }
                if(status.toString().equalsIgnoreCase("FAIL"))
                {
                	String screenshotPath = ExtentReporterNG.getScreenshot(driver, result.getName());
    		         test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath)); 
                }
				extent.endTest(test);
			}
		}
	}

	public Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
	public static String getScreenshot(WebDriver driver, String testname) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File source = scrShot.getScreenshotAs(OutputType.FILE);
		String destination = FilePath.FAILED_SCREENSHOT_FILE + testname
				+ dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
