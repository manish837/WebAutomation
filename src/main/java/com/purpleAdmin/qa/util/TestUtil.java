package com.purpleAdmin.qa.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.purpleAdmin.qa.base.TestBase;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
//import com.relevantcodes.extentreports.ExtentTest;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 50;
	public static long IMPLICIT_WAIT = 20;
	public static long start = System.currentTimeMillis();
	public static long finish = System.currentTimeMillis();
	public static Boolean blnFlag = false;

	public static String TESTDATA_SHEET_PATH = "/Users/user/Downloads/PurpleAuto/PurpleAdminPortal/src/main/java/com/purpleAdmin/qa/testdata/PupleAdminModuleList.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	public static void switchToFrame() {
		// driver.switchTo().frame("wayfinding-iframe");
		driver.switchTo().frame("viewport");
	}
	/*
	 * public static void switchToFrameWithID(WebElement element){
	 * driver.switchTo().frame(element); }
	 */

	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}

	public static void takeScreenshotElement() throws IOException {
		WebElement logoImageElement = driver.findElement(By.xpath("//div[@id='FloorBasement-map']"));
		Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImageElement);
		ImageIO.write(logoImageScreenshot.getImage(), "png", new File("C://path/OrangeHRMlogo.png"));
		File f = new File("C://path/OrangeHRMlogo.png");
		if (f.exists()) {
			System.out.println("Image File Captured");
		} else {
			System.out.println("Image File NOT exist");
		}
	}

	public static void CompareScreenshotElement() throws IOException {
		BufferedImage expectedImage = ImageIO.read(new File("C://path/OrangeHRMlogo.png"));

		WebElement logoImageElement = driver.findElement(By.xpath("//div[@id='FloorBasement-map']"));
		Screenshot logoImageScreenshot = new AShot().takeScreenshot(driver, logoImageElement);
		BufferedImage actualImage = logoImageScreenshot.getImage();

		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
		if (diff.hasDiff() == true) {
			System.out.println("Images are Not Same");
		} else {
			System.out.println("Images are Same");
		}
	}

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static String validatePageTitle() {
		String pageTitle = driver.findElement(By.xpath("//h4[@class='purpleHeading']")).getText();
		return pageTitle;
	}

	public static Boolean waitForElementPresence(WebElement ele, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 90);
		blnFlag = wait.until(ExpectedConditions.visibilityOf(ele)).isDisplayed();
		return blnFlag;
	}

	public static WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot",
				element);
		return ele;
	}

	private void logResult(ExtentTest test, String key, String actual, String expected, String result) {
		if (result.equalsIgnoreCase("PASS")) {
			test.log(Status.PASS, "<font size = 3 color=\"#3ADF00\">" + key + "</font>" + " Actual: " + actual
					+ " || Expected: " + expected);
		} else {
			test.log(Status.FAIL, "<font size = 3 color=\"#FE2E2E\">" + key + "</font>" + " Actual: " + actual
					+ " || Expected: " + expected);
		}
	}

	public void verifyText(String actual, String expected, String key, ExtentTest test) {
		try {
			Assert.assertEquals(actual, expected);
			logResult(test, key, actual, expected, "PASS");
		} catch (Error e) {
			logResult(test, key, actual, expected, "FAIL");

		}
	}

	public static void scrollByVisibleElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public static Boolean compareText(WebElement element, String expected) {
		String actual = element.getText();
		System.out.println(actual);

		if (expected.equals(actual)) {
			blnFlag = true;
		}
		return blnFlag;
	}

	public static void switchTab() {
		// Switching between tabs using CTRL + tab keys.
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "\t");
		// Switch to current selected tab's content.
		driver.switchTo().defaultContent();
	}

	public static void clearData(WebElement element) {
		element.clear();
	}

	public static String getFileDateAsSufix() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String fileDateSuffix = formatter.format(date);
		return fileDateSuffix;
	}

}