package com.utils;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
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

	public static void getScreenshot() {
		TakesScreenshot tss = (TakesScreenshot) staticDriver;
	}
}
