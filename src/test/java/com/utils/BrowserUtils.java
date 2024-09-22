package com.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
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
}
