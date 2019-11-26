package com.atmecs.practice1.testbase;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.atmecs.practice1.constant.FilePath;
import com.atmecs.practice1.constant.TimeOut;
import com.atmecs.practice1.util.ReadProp;

public class TestBase {
	public WebDriver driver;
	public Properties baseClass;
	public String url;
	public String browser;
	int downloadFile;
	String con;
	String environmentVariable;

	String huburl;

	/**
	 * This method choose which browser invoke and also choose normal execution
	 * happen or grid execution happen
	 * 
	 * @param browser
	 * @param url
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	//@Parameters({ "browser" })
	@BeforeTest
	public void intitailizeBrowser(ITestContext context) throws IOException {
		//this.browser = browser;
		baseClass =ReadProp.loadProperty(FilePath.CONFIG_FILE);
		//url = baseClass.getProperty("url");
		browser = baseClass.getProperty("browser");
		con = baseClass.getProperty("connection");
		huburl = baseClass.getProperty("remoteconnectionurl");
		//environmentVariable=System.getenv("browser");
		//this.url = url;
		String suiteName=context.getSuite().getName();
		System.out.println("browser is " + browser);
		if (con.equals("normal")) {

			if (browser.equalsIgnoreCase("chrome")) {
				//WebDriverManager.chromedriver().arch64().setup();
				System.setProperty("webdriver.chrome.driver", FilePath.CHROME_PATH);
				driver = new ChromeDriver();
				// driver.get(url);

			} else if (browser.equalsIgnoreCase("firefox")) {
				//WebDriverManager.firefoxdriver().arch64().setup();
				System.setProperty("webdriver.gecko.driver", FilePath.FIREFOX_PATH);
				driver = new FirefoxDriver();
				// driver.get(url);
			} else if (browser.equalsIgnoreCase("internet explorer")) {
				System.setProperty("webdriver.ie.driver", FilePath.IE_PATH);
			//	WebDriverManager.iedriver().arch32().setup();
				DesiredCapabilities ieCaps = DesiredCapabilities.internetExplorer();
				ieCaps.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
				driver = new InternetExplorerDriver(ieCaps);
			}
		} else if (con.equalsIgnoreCase("grid")) {
			//WebDriver drv = new GridConnection().gridCon(driver, browser, huburl);
			//this.driver = drv;

		}
		
		String url = baseClass.getProperty(suiteName);
		System.out.println(url+""+suiteName);
		driver.get(url);
	
		//ThreadPool.set(driver);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TimeOut.TIMEOUT_INSECONDS, TimeUnit.SECONDS);
	}
	
	



	/**
	 * This method used to close the driver.
	 */
//	@AfterTest()
//	public void endTest() {
//		driver.quit();
	//}
}

