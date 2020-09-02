package com.framework.qa.basepage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.qa.basetest.BaseTest;
import com.github.javafaker.Faker;

public class BasePage extends BaseTest {
	public BasePage(WebDriver driver, ExtentTest test) {
		BaseTest.driver = driver;
		BaseTest.test = test;
	}

	public void logInfo(String msg) {
		test.info(MarkupHelper.createLabel(msg, ExtentColor.BLUE));
	}

	public void logFail(String msg) {
		test.fail(MarkupHelper.createLabel(msg, ExtentColor.RED));
		Assert.assertFalse(true, msg);
	}

	public void logPass(String msg) {
		test.pass(MarkupHelper.createLabel(msg, ExtentColor.GREEN));
	}

	public void waitUntilElementDisplayed(final WebElement webElement) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		ExpectedCondition<Boolean> elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					webElement.isDisplayed();
					return true;
				} catch (NoSuchElementException e) {
					return false;
				} catch (StaleElementReferenceException f) {
					return false;
				}
			}
		};
		wait.until(elementIsDisplayed);
	}

	public boolean waitForElementTobeClickable(WebElement element, long timeOut) {

		WebElement isElementClickable = null;

		try {

			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			isElementClickable = wait.until(ExpectedConditions.elementToBeClickable(element));
			logInfo("element present");
		} catch (Exception e) {
			logInfo(" Exception in the wait for 10 seconds for the  Element To Appear" + e);
		}

		return isElementClickable.isDisplayed();
	}

	public void isDisplayed(WebElement locator, String message) {
		try {
			if (locator != null) {
				waitUntilElementDisplayed(locator);
				if (locator.isDisplayed()) {
					logPass(message + " is Displayed");
				} else {
					logFail(message + " is Not Displayed");
				}
			}

			else {
				logInfo("Locator is Null");
			}
		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public void sleep(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void isDisplayedThenClick(WebElement locator, String message) {
		try {
			waitForElementTobeClickable(locator, 5);
			if (locator.isDisplayed()) {
				locator.click();
				logPass(message + " id Displayed and Clicked on it.");
			} else {
				logFail(message + " is Not Displayed");
			}
		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public void Click(WebElement locator, String message) {
		try {
			waitForElementTobeClickable(locator, 5);
			System.out.println(locator);
			locator.click();
			logPass(message + " id Clicked on it.");
		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public void isDisplayedThenEnterText(WebElement locator, String message, String sendText) {
		try {
			waitUntilElementDisplayed(locator);
			System.out.println("element present ----- ");
			isDisplayed(locator, message);
			locator.click();
			locator.sendKeys(sendText);
		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public void isDisplayedThenClearText(WebElement locator, String message) {
		try {
			waitUntilElementDisplayed(locator);
			System.out.println("element present ----- ");
			isDisplayed(locator, message);
			locator.clear();

		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public void isDisplayedThenActionClick(WebElement locator, String message) {
		try {
			waitUntilElementDisplayed(locator);
			if (locator.isDisplayed()) {
				actionClick(locator);
				logPass(message + " id Displayed and Clicked on it.");
			} else {
				logFail(message + " is Not Displayed");
			}
		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public void actionClick(WebElement locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(locator));
			Actions act = new Actions(driver);
			act.moveToElement(locator).click(locator).build().perform();
		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public void checkElementPresenceAndClick(WebElement locator, String elementText) {
		if (locator.isDisplayed()) {
			actionClick(locator);
			logPass(elementText + " clicked successfully");
		} else {
			logFail(elementText + " not clickable");
		}
	}

	public void scrollDownPage() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,390)", "");
	}

	public String getText(WebElement locator) {
		try {
			String text = "";

			if (locator.isDisplayed()) {
				waitUntilElementDisplayed(locator);
				text = locator.getText().trim();
			}
			return text;
		} catch (Exception ex) {
			logFail(ex.getMessage());
			return null;
		}
	}

	public void MouseHover(WebElement locator) {
		try {

			System.out.print("Mouse Hover Action");
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(locator));
			Actions act = new Actions(driver);
			act.moveToElement(locator).build().perform();
		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public String getAttribute(WebElement locator, String attribute) {
		try {
			String attr = locator.getAttribute(attribute);
			return attr;
		} catch (Exception ex) {
			logFail(ex.getMessage());
			return null;
		}
	}

	public void validateIsPageDisplayed(WebElement leftPanel, WebElement mainFrame) {
		if (getText(leftPanel).equals(getText(mainFrame))) {
			logPass("Expected Page is Displayed");
		} else {
			logFail("Expected Page is not displayed");
		}
	}

	public void verifyText(WebElement locator, String text) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.textToBePresentInElement(locator, text));
			Assert.assertEquals(locator.getText().trim(), text);
			logInfo("Text Present");
		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public boolean waitToCheckElementIsDisplayed(final By locator, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		try {
			if (wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed() == true) {
				return true;
			} else {
				return false;
			}
		} catch (NoSuchElementException e) {
			logInfo(" Exception in the wait for element To Appear" + e);
			return false;
		} catch (TimeoutException e) {
			return false;
		} catch (StaleElementReferenceException e) {
			return false;
		}
	}

	public boolean waitForTextToAppear(String textToAppear, long timeOut) {

		boolean isTextPresent = false;
		WebElement bodyElement = null;

		try {
			bodyElement = driver.findElement(By.tagName("body"));
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(bodyElement, textToAppear));
			logInfo(textToAppear + "is present in the page = " + isTextPresent);
		} catch (Exception e) {
			logInfo(" Exception in the wait for Text To Appear" + e);
		}

		return isTextPresent;
	}

	public Faker fakerAPI() {
		Faker faker = new Faker();
		return faker;
	}

	public void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	public void verifyPageTitle(String title) {
		waitForPageLoad(driver);
		String pageTitle = driver.getTitle();
		if (pageTitle.equals(title)) {
			logPass("Expected Title is displayed");
		} else {
			logFail("Expected title Page is not displayed");
		}
	}

	public void isDisplayedThenClickRadioButtonOrCheckBox(WebElement locator, String message) {
		try {
			waitUntilElementDisplayed(locator);
			isDisplayed(locator, message);
			boolean isEnabledORNot = locator.isSelected();
			if (!isEnabledORNot) {
				locator.click();
				logPass(message + " is selected");
			}

			else {
				logInfo(message + " already selected");
			}

		} catch (Exception ex) {
			logFail(ex.getMessage());
		}
	}

	public void selectOptionByVisibleText(WebElement locator, String text) {
		try {
			Select selectElement = new Select(locator);
			selectElement.selectByVisibleText(text);
			logInfo(text + " is Selected");
		} catch (Exception e) {
			logFail(e.getMessage());
		}

	}

}
