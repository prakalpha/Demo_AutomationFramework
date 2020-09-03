package com.framework.qa.basetest;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.framework.qa.pages.RegistrationPage;
import com.framework.qa.utils.Constants;
import com.framework.qa.utils.PropUtils;
import com.framework.qa.utils.ReportUtils;

public class BaseTest {
	public static WebDriver driver;
	public static ExtentTest test;
	public static ExtentReports extent;
	public ExtentHtmlReporter htmlReporter;
	public static String resultFile;
	public static String indexFile;
	public static String reportsFolder;
	public static String instancereportsFolder;
	public static String screenShotFolder;

	public static File configFile = PropUtils.getPropFile(Constants.CONFIG_DIR, Constants.CONFIG_FILE_NAME);
	public static Properties configProp = PropUtils.getProps(configFile);

	// Date and Time Details
	public static String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date date = new Date();
		return sdf.format(date);
	}

	public static String capture(WebDriver driver) throws IOException {
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String dest = BaseTest.screenShotFolder + "/" + getCurrentDateTime() + ".png";
		System.out.println("Dest:........" + dest);
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		return dest;
	}

	@BeforeSuite(alwaysRun = true)
	public void initReport() {

		ReportUtils.createResultsFolder(Constants.REPORTS_DIR);
		Date currentdate = new Date();
		reportsFolder = Constants.REPORTS_DIR + new SimpleDateFormat("MMMMM_dd_yyyy").format(currentdate);
		ReportUtils.createResultsFolder(reportsFolder);

		instancereportsFolder = reportsFolder + Constants.FILE_SEPARATOR
				+ new SimpleDateFormat("MMMMM_dd_yyyy_hh-mm").format(currentdate);
		ReportUtils.createResultsFolder(instancereportsFolder);

		screenShotFolder = instancereportsFolder + Constants.FILE_SEPARATOR
				+ new SimpleDateFormat("MMM-dd-yyyy_hh-mm").format(currentdate) + "_screenshots";
		ReportUtils.createResultsFolder(screenShotFolder);

		indexFile = Constants.REPORTS_DIR + Constants.FILE_SEPARATOR + "index.html";

		resultFile = instancereportsFolder + Constants.FILE_SEPARATOR
				+ PropUtils.getPropValue(configProp, "projectName") + "_"
				+ new SimpleDateFormat("MMM-dd-yyyy_hh-mm").format(currentdate) + "_" + "AutomationResults.html";
		System.out.println("resultFile" + resultFile);
		// extend Report
		if (extent == null) {
			htmlReporter = new ExtentHtmlReporter(resultFile);
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);

			htmlReporter.config().setChartVisibilityOnOpen(true);
			htmlReporter.config().setDocumentTitle("Automation Testing Report");
			try {
				htmlReporter.config().setReportName("Regression Testing");

			} catch (NullPointerException e) {
				htmlReporter.config().setReportName("Automation Testing");
			}
			try {
				htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			} catch (NullPointerException e) {

				htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			}
			try {

				htmlReporter.config().setTheme(Theme.DARK);

			} catch (NullPointerException e) {
				htmlReporter.config().setTheme(Theme.STANDARD);
			}
		}
	}

	@BeforeTest(alwaysRun = true)
	public void initializeDriver() {
		String browserName = System.getProperty("propertyName");
		System.out.println("BrowserName:" + browserName);
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", Constants.DRIVER_DIR + "chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equals("firefox")) {
			// firefox driver
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(PropUtils.getPropValue(configProp, "applicationURL"));
	}

	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {

		try {
			RegistrationPage registrationPage = new RegistrationPage(driver, test);
			if (result.getStatus() == ITestResult.FAILURE) {
				String screenShotPath = capture(driver);
				test.fail(MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
						ExtentColor.RED));
				test.fail(result.getThrowable());
				test.fail("Screenshot below: " + test.addScreenCaptureFromPath(screenShotPath));
				registrationPage.clickAccountAndValidateLogout();
			} else if (result.getStatus() == ITestResult.SUCCESS) {

				test.pass(MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
			} else {
				test.skip(MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
				test.skip(result.getThrowable());
			}
		} catch (Exception ex) {
			test.fail(ex.getMessage());
		}
	}

	@AfterTest(alwaysRun = true)
	public void closeBrowser() {
		driver.close();
		driver.quit();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {

		try {
			extent.flush();
			ReportUtils.createIndexFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
