package com.atmecs.practice1.testbase;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
/**
 * class for grid connection
 * @author rishav.kumar
 *In this class, grid connection is being done
 */

public class Grid {
	 public URL url;
		String nodeurl;
		/**
		 * In this method, hub is created
		 */

		public  WebDriver getConnection(WebDriver driver, String browser) {
			String nodeurl = "http://55.55.52.127:4444/wd/hub";

			DesiredCapabilities capability = new DesiredCapabilities();

			try {
				url = new URL(nodeurl);

			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			switch (browser) {
			case "firefox":
				capability = DesiredCapabilities.firefox();
				capability.setBrowserName("firefox");
				break;
			case "chrome":
				capability = DesiredCapabilities.chrome();
				capability.setBrowserName("chrome");
				break;
			case "ie":
				capability = DesiredCapabilities.internetExplorer();
				capability.setBrowserName("internet explorer");
				break;
			default:
				System.out.println("Browser not found");
			}

			capability.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(url, capability);
			System.out.println("start");
			return driver;
			
		}
}
