package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utils.BrowserUtils;

public final class DashboardPage extends BrowserUtils {

	private WebDriver driver;

	private static final By LEAVE_TAB_BUTTON_LOCATOR = By.xpath("//span[text()='Leave']");

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getDashboardPageUrl(String urlEndPoint) {
		return getCurrentPageUrl(urlEndPoint);
	}
	
	public LeavePage goToLeavePage() {
		clickOn(LEAVE_TAB_BUTTON_LOCATOR);
		return new LeavePage(driver);
	}

}
