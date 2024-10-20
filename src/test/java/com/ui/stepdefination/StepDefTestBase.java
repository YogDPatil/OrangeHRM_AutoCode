package com.ui.stepdefination;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Env;
import com.ui.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Synchronized;

public class StepDefTestBase {

	protected WebDriver driver;
	protected Env environment;

	private static ThreadLocal<WebDriver> thlcDriver = new ThreadLocal<>();

	// Initialize the WebDriver for the specified browser
	public WebDriver init_driver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			thlcDriver.set(new ChromeDriver()); // Set driver in ThreadLocal
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			thlcDriver.set(new FirefoxDriver()); // Set driver in ThreadLocal
		}
		return getDriver();
	}

	public static WebDriver getDriver() {
		return thlcDriver.get();
	}

	public LoginPage getLoginPageObject() {
		return new LoginPage(getDriver());
	}

}
