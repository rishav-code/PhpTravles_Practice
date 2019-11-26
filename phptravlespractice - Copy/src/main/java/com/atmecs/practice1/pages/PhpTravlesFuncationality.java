package com.atmecs.practice1.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.atmecs.practice1.constant.FilePath;
import com.atmecs.practice1.constant.Findloc;
import com.atmecs.practice1.constant.TimeOut;
import com.atmecs.practice1.helper.CommonUtlity;
import com.atmecs.practice1.helper.Waits;
import com.atmecs.practice1.reports.LogReport;
import com.atmecs.practice1.util.ExcelReader;
import com.atmecs.practice1.util.NullCellValueException;

public class PhpTravlesFuncationality {
	WebDriver driver;
	CommonUtlity commonutility;
	LogReport log;
	ExcelReader excelFile;
	Waits wait;
	 
	Findloc loc;

	public PhpTravlesFuncationality(WebDriver driver) {
		this.driver = driver;
		commonutility = new CommonUtlity(driver);
		loc = new Findloc();
		log=new LogReport();
		excelFile = new ExcelReader();
		wait= new Waits();
	}

	public void loginFunctionality() {
		try {
		log.info("login funcationality starts");
		System.out.println("Hi");
		commonutility.clickAndSendText(loc.getlocator("loc.username.sendtext"), TimeOut.TIMEOUT_INSECONDS, excelFile.getCellData(FilePath.TESTDATA_FILE, "logindata", "User_Name", "Test_ID1"));
		commonutility.clickAndSendText(loc.getlocator("loc.password.sendtext"), TimeOut.TIMEOUT_INSECONDS, excelFile.getCellData(FilePath.TESTDATA_FILE, "logindata", "Password", "Test_ID1"));
		commonutility.clickElement(loc.getlocator("loc.search.button"));
		log.info("login fincationality ends");
		
		}
	
		
	catch (NullCellValueException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}
	public void accountPageValidation() throws NullCellValueException {
		log.info("Hi Demo User Text validation starts");
		wait.hardWait(TimeOut.WAIT_TIMEOUT_INSECONDS);
		String demouserTest=commonutility.getElement(loc.getlocator("loc.demouservalidation.text")).getText();
		commonutility.assertion(demouserTest, excelFile.getCellData(FilePath.TESTDATA_FILE, "logindata", "Demo _User_Validation", "Test_ID1"));
		log.info("Booking Tab Text Validation ");
		String bookingValidationText=commonutility.getElement(loc.getlocator("loc.bookingtab.text")).getText();
		commonutility.assertion(bookingValidationText,excelFile.getCellData(FilePath.TESTDATA_FILE, "logindata", "Booking_Page_validation", "Test_ID1"));
	}
		public void timeAndDateValidations() {
		log.info("Time and date validation starts");
		Calendar cal = Calendar. getInstance();
		Date date=cal. getTime();
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		String formattedDate=dateFormat. format(date);
		System.out.println("Current time of the day using Calendar - 24 hour format: "+ formattedDate);
		String[]splitTime=formattedDate.split(":", 3);
		System.out.println(splitTime[0]+""+splitTime[1]);
		String siteTime=commonutility.getElement(loc.getlocator("loc.time.text")).getText();
		String[]splitSiteTime=siteTime.split(":", 3);
		assertEquals(splitTime[0], splitSiteTime[0]);
		log.info("Hours matched ");
		assertEquals(splitTime[1], splitSiteTime[1]);
		log.info("Minutes matched");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMMM yyyy");
		LocalDate now = LocalDate.now();
		String SiteDate=commonutility.getElement(loc.getlocator("loc.fulldate.text")).getText();
        commonutility.assertion(SiteDate, dtf.format(now))	;
        log.info("validating booking id");
        commonutility.clickElement(loc.getlocator("loc.clickoninvoice1.click"));
        
        Set<String> allWindowHandles = driver.getWindowHandles();

		for(String handle : allWindowHandles)
		{
			System.out.println("Window handle - > " + handle);
			driver.switchTo().window(handle);
			
		}
		
        String currentUrl = driver.getCurrentUrl();
		String bookkingId= commonutility.getElement(loc.getlocator("loc.bookingid.text")).getText();
        assertTrue(currentUrl.contains(bookkingId));
        driver.close();
        log.info("assertion done for bookind id through url");
		}
        public void randomNumberValidation() {
        	System.out.println("hi");
        	List<WebElement> l=commonutility.getElementsList( loc.getlocator("loc.list.size"));
        	System.out.println(l);
        	int size=l.size();
        	System.out.println(size);
        	Random rand = new Random();
        	rand.nextInt(size);
        	System.out.println(rand.nextInt(size));
        	
        	
        }
        }
	


