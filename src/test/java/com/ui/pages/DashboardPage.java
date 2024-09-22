package com.ui.pages;

import org.openqa.selenium.WebDriver;

import com.utils.BrowserUtils;

public final class DashboardPage extends BrowserUtils {

	private WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getDashboardPageUrl(String urlEndPoint) {
		return getCurrentPageUrl(urlEndPoint);
	}
	
}
