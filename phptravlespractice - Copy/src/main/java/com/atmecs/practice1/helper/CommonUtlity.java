package com.atmecs.practice1.helper;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.atmecs.practice1.constant.TimeOut;
import com.atmecs.practice1.testbase.TestBase;

public class CommonUtlity extends TestBase {
	WebDriver driver;
	WebElement element;

	public CommonUtlity(WebDriver driver) {

		this.driver = driver;

		/*
		 * method name -selectloc
		 * 
		 * @param locator
		 * 
		 * @return
		 */
	}

	public WebElement selectloc(String loc) {
		String[] locator = loc.split(":", 2);

		switch (locator[0]) {
		case "id":
			element = driver.findElement(By.id(locator[1]));

			break;
		case "name":
			element = driver.findElement(By.name(locator[1]));

			break;
		case "class":
			element = driver.findElement(By.className(locator[1]));

			break;
		case "linktext":
			element = driver.findElement(By.linkText(locator[1]));

			break;
		case "partiallinktext":
			element = driver.findElement(By.partialLinkText(locator[1]));

			break;
		case "tagname":
			element = driver.findElement(By.tagName(locator[1]));

			break;
		case "css":
			element = driver.findElement(By.cssSelector(locator[1]));

			break;
		case "xpath":
			element = driver.findElement(By.xpath(locator[1]));

			break;

		}
		return element;

	}
	/*
	 * method click the Element using the fluent wait concepts ignoring the
	 * ElementClickInterceptedException.
	 * 
	 * @param driver
	 * 
	 * @param xpath
	 */

	public void clickElement(final String loc) {
		try {

			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.ignoring(ElementClickInterceptedException.class)
					.pollingEvery(Duration.ofSeconds(TimeOut.POLLING_TIMEOUT_INSECONDS))
					.withTimeout(Duration.ofSeconds(TimeOut.TIMEOUT_INSECONDS));
			fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					selectloc(loc);
					element.click();
					return true;
				}

			});
		} catch (Exception e) {

			System.out.println("Some problem occured while clicking on the element");
			e.printStackTrace();

		}
	}
	/*
	 * this method will text and select the items from the drop down.
	 * 
	 * @param driver
	 * 
	 * @param xpath
	 * 
	 * @param timeOut
	 * 
	 * @param text
	 */

	public void selectDropdown(final String loc, int timeOut, final String text) {
		try {

			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.pollingEvery(Duration.ofSeconds(TimeOut.POLLING_TIMEOUT_INSECONDS))
					.withTimeout(Duration.ofSeconds(TimeOut.TIMEOUT_INSECONDS));
			fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					WebElement ele = selectloc(loc);
					System.out.println("ytfuyfuytfutf");
					Select select = new Select(ele);

					select.selectByVisibleText(text);
					return true;
				}

			});
		} catch (Exception e) {
			System.out.println("Some problem occured while selecting the drop down");
			e.printStackTrace();

		}
	}

	/*
	 * method click the blank fields and send the text to be entered
	 * 
	 * @param driver
	 * 
	 * @param xpath
	 * 
	 * @param timeOutInSeconds
	 * 
	 * @param text
	 */
	public void clickAndSendText(final String loc, int timeOutInSeconds, final String text) {
		try {

			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.ignoring(ElementClickInterceptedException.class)
					.pollingEvery(Duration.ofSeconds(TimeOut.POLLING_TIMEOUT_INSECONDS))
					.withTimeout(Duration.ofSeconds(TimeOut.TIMEOUT_INSECONDS));

			fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					selectloc(loc);
					element.sendKeys(text);
					;
					return true;
				}
			});
		} catch (Exception e) {
			System.out.println("Some problem occured while clicking and sending the text");
			e.printStackTrace();

		}
	}

	public void clickOnElement(String loc) {
		try {

			WebDriverWait wait = new WebDriverWait(driver, TimeOut.TIMEOUT_INSECONDS);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loc)));
			selectloc(loc);
			element.click();

		} catch (Exception e) {
			System.out.println("Some problem occured while clicking on the the element");
			e.printStackTrace();

		}
	}

	/*
	 * method takes parameters as
	 * 
	 * @param locator
	 * 
	 * @param text and get the text of the web element
	 */
	public String getText(final String loc) {
		String text = null;
		try {

			selectloc(loc);
			text = element.getText();

		}

		catch (Exception e) {
			System.out.println("Some problem occured while getting the text");
			e.printStackTrace();

		}
		return text;
	}

	/*
	 * method takes parameter as
	 * 
	 * @param driver
	 * 
	 * @param xpath
	 * 
	 * @param text
	 * 
	 * @return WebElement Its to get the particular element and send text
	 */
	public WebElement getElementAndSendText(String loc, String text) {
		try {

			selectloc(loc);
			element.sendKeys(text);

		} catch (Exception e) {
			System.out.println("Some problem occured while getting the element and sending text");
			e.printStackTrace();

		}
		return element;
	}
	/*
	 * method takes parameters as
	 * 
	 * @param driver
	 * 
	 * @param XPathIsDisplayed
	 * 
	 * @return a boolean value for the displayed element on the web page
	 */

	public boolean isDisplayed(String loc) {
		boolean isDisplayed = false;
		try {
			selectloc(loc);
			isDisplayed = element.isDisplayed();

		} catch (Exception e) {
			System.out.println("Element is not displayed");
			isDisplayed = false;
		}
		return isDisplayed;
	}

	/*
	 * method takes parameters as
	 * 
	 * @param driver
	 * 
	 * @param xpath and uses the explicit wait concept
	 * 
	 * @return a boolean value after checking the visibility of the.
	 */
	public boolean isElementVisible(String loc) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			selectloc(loc);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception exception) {

			return false;
		}
		return true;
	}
	/*
	 * method name-getElementsList
	 * 
	 * @param locator
	 * 
	 * @return
	 */

	public List<WebElement> getElementsList( String loc) {
		String[] loac = loc.split(":");
		List<WebElement> element = null;
		try {

			if (loac[0].toUpperCase().equalsIgnoreCase("CSS")) {
				element = driver.findElements(By.cssSelector(loac[1]));
			} else {
				element = driver.findElements(By.xpath(loac[1]));
			}

		} catch (Exception e) {
			System.out.println("Some problem occured while getting the list of the elements");
			e.printStackTrace();

		}

		return element;
	}

	/*
	 * the method takes parameter as
	 * 
	 * 
	 * @param byResolution and scroll down the window.
	 */
	public void scrollDownPage(int byResolution) {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String scroll = "window.scrollBy(0," + byResolution + ")";
			js.executeScript(scroll);

		} catch (Exception e) {
			System.out.println("Some problem occured while scrolling down the page");
			e.printStackTrace();
		}
	}
	/*
	 * method name-getElement
	 * 
	 * @param locator
	 * 
	 * @return
	 */

	public WebElement getTheElement(String xpath) {
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, TimeOut.TIMEOUT_INSECONDS);
			wait.until(ExpectedConditions.elementToBeSelected(By.xpath(xpath)));
			element = driver.findElement(By.xpath(xpath));

		} catch (Exception e) {
			System.out.println("Some problem occured while getting the element");
			e.printStackTrace();

		}
		return element;
	}

	public WebElement getElement(String loc) {
		try {

			selectloc(loc);

		} catch (Exception e) {
			System.out.println();
		}
		return element;

	}
	/*
	 * method name-clearTextData
	 * 
	 * @param locator
	 * 
	 * @return
	 */

	public void clearTextData(final String loc) {
		try {

			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.ignoring(ElementClickInterceptedException.class)
					.pollingEvery(Duration.ofSeconds(TimeOut.POLLING_TIMEOUT_INSECONDS))
					.withTimeout(Duration.ofSeconds(TimeOut.TIMEOUT_INSECONDS));
			fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					selectloc(loc);
					element.clear();
					return true;
				}
			});
		} catch (Exception e) {
			System.out.println("Some problem occured while clearing the text");
			e.printStackTrace();
		}
	}
	/*
	 * method name-getElementlist
	 * 
	 * @param locator
	 * 
	 * @driver
	 */

	public List<WebElement> getElementsList(WebDriver driver, final String loc) {
		List<WebElement> elements = null;
		try {

			elements = driver.findElements(By.xpath(loc));

		} catch (Exception e) {
			System.out.println("Some problem occured while getting the element list");
			e.printStackTrace();
		}
		return elements;
	}

	public boolean isSelected(String loc) {
		boolean isSelected = false;
		try {
			selectloc(loc);
			element.isSelected();
		} catch (Exception e) {
			isSelected = false;
		}
		return isSelected;
	}
	/*
	 * method name-scrollToElement
	 * 
	 * @param locator
	 */

	public void scrollIntoview(String xpath) {
		try {

			WebElement element = driver.findElement(By.xpath(xpath));
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * method name-SelectDropDownByIndex
	 * 
	 * @param locator
	 * 
	 * @return
	 */

	public void selectDropdownByIndex(final String loc, final int index) {
		try {

			FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
					.ignoring(ElementClickInterceptedException.class)
					.pollingEvery(Duration.ofSeconds(TimeOut.POLLING_TIMEOUT_INSECONDS))
					.withTimeout(Duration.ofSeconds(TimeOut.TIMEOUT_INSECONDS));
			fluentWait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					selectloc(loc);
					Select trip = new Select(element);

					trip.selectByIndex(index);
					return true;
				}

			});
		} catch (Exception e) {
			System.out.println("some problem occured while selecting the drop down");
			e.printStackTrace();

		}

	}

	public void mouseHover(String loc) {
		Actions action = new Actions(driver);
		selectloc(loc);
		action.moveToElement(element).perform();

	}

	public String assertion(String actual, String expected) {
		try {
			Assert.assertEquals(actual, expected, "Assertion failed");
			System.out.println("Validation pass:" + actual + " and " + expected + " match");
			return "Validation pass:" + actual + " data and " + expected + " data match";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Validation fail:" + actual + " and " + expected + " not match");
			return "Validation fail:" + actual + "data  and " + expected + " data not match";
		}
	}

	public String pageRedirection(String expectedPageTile) {
		String actualTitle = driver.getTitle();
		String message = assertion(actualTitle, expectedPageTile);
		return "Page Redirection " + message;
	}
}