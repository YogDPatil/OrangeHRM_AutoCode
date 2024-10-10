package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Env;
import com.utils.BrowserUtils;
import com.utils.TestUtils;

public final class LoginPage extends BrowserUtils {

	private WebDriver driver;
	private String endPoint = "/web/index.php/leave/viewMyLeaveList";

	private static final By USERNAME_LOCATOR = By.name("username");
	private static final By PASSOWORD_LOCATOR = By.name("password");
	private static final By LOGIN_LOCATOR = By.xpath("//button[@type='submit']");

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public DashboardPage doLogin(Env env) {
		if (env.toString().equals(Env.QA.toString())) {
			goToLoginPage(TestUtils.getValueFromPropertyFile("BASE_URL", env) + endPoint);
			enterText(USERNAME_LOCATOR, TestUtils.getValueFromPropertyFile("Username", env));
			enterText(PASSOWORD_LOCATOR, TestUtils.getValueFromPropertyFile("Password", env));
		} else if (env.toString().equals(Env.DEV.toString())) {
			goToLoginPage(TestUtils.getValueFromPropertyFile("BASE_URL", env) + endPoint);
			enterText(USERNAME_LOCATOR, TestUtils.getValueFromPropertyFile("Username", env));
			enterText(PASSOWORD_LOCATOR, TestUtils.getValueFromPropertyFile("Password", env));
		} else if (env.toString().equals(Env.UAT.toString())) {
			goToLoginPage(TestUtils.getValueFromPropertyFile("BASE_URL", env) + endPoint);
			enterText(USERNAME_LOCATOR, TestUtils.getValueFromPropertyFile("Username", env));
			enterText(PASSOWORD_LOCATOR, TestUtils.getValueFromPropertyFile("Password", env));
		}

		clickOn(LOGIN_LOCATOR);
		return new DashboardPage(driver);
	}

}
