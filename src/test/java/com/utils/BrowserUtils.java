package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BrowserUtils {

	private WebDriver driver;
	private WebDriverWait wait;
	private static WebDriver staticDriver;

	public BrowserUtils(WebDriver driver) {
		this.driver = driver;
		this.staticDriver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void enterText(By locator, String text) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
	}

	public void clickOn(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public String getCurrentPageUrl(String urlName) {
		wait.until(ExpectedConditions.urlContains(urlName));
		return driver.getCurrentUrl();
	}

	public void goToLoginPage(String url) {
		driver.get(url);
	}

	public static String getScreenshot(String testName) {
		TakesScreenshot tss = (TakesScreenshot) staticDriver;
		File srcFile = tss.getScreenshotAs(OutputType.FILE);

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String formatedDate = dateFormat.format(date);
		String ssFolderPath = System.getProperty("user.dir") + "/screenshots/" + formatedDate;
		File file = new File(ssFolderPath);
		if (!file.exists()) {
			FileHandler.createDir(file);
		}
		String ssPath = ssFolderPath + "/" + testName + ".png";
		File destFile = new File(ssPath);
		try {
			FileHandler.copy(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ssPath;

	}

	public void selectValueFromDropdown(By locator, String childEleXpath, String leaveType) {
		List<WebElement> optionList = driver.findElements(locator);

		// here we avoid first element(index=0) in option list
		for (int i = 1; i < optionList.size(); i++) {
			WebElement option = optionList.get(i);
			String leaveTypeInList = option.findElement(By.xpath(childEleXpath)).getText();
			if (leaveTypeInList.equals(leaveType)) {
				option.click();
				break;
			}
		}
	}

	public String getNextDayDate(By nxtMonthButtonLocator) {
		LocalDate today = LocalDate.now();
//		LocalDate today = LocalDate.parse("2024-10-31");
		String todayDate = today.format(DateTimeFormatter.ofPattern("d"));
		Integer.parseInt(today.format(DateTimeFormatter.ofPattern("d")));
		DayOfWeek currentDay = today.getDayOfWeek();
		String leaveDay;
		if (currentDay == DayOfWeek.FRIDAY) {
			leaveDay = today.plusDays(3).format(DateTimeFormatter.ofPattern("d"));
		} else if (currentDay == DayOfWeek.SATURDAY) {
			leaveDay = today.plusDays(2).format(DateTimeFormatter.ofPattern("d"));
		} else {
			leaveDay = today.plusDays(1).format(DateTimeFormatter.ofPattern("d"));
		}
		if (Integer.parseInt(todayDate) > Integer.parseInt(leaveDay)) {
			clickOn(nxtMonthButtonLocator);
		}
		return leaveDay;
	}
	
	public WebElement webElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public List<WebElement> getListOfWebElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
}
