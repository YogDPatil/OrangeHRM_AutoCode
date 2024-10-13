package com.ui.tests;

import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.constants.Env;
import com.ui.pages.LoginPage;

public abstract class TestBase {

	protected LoginPage loginPage;
	protected WebDriver driver;
	private static ThreadLocal<WebDriver> thlDriver = new ThreadLocal<WebDriver>();
	protected static ThreadLocal<LoginPage> thlcLoginPage = new ThreadLocal<LoginPage>();

	protected Env testingEnvironment;

	@Parameters({ "browser", "env" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, @Optional("qa") String env) {
		testingEnvironment = Env.valueOf(env.toUpperCase());

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
			thlDriver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
			thlDriver.set(new FirefoxDriver());
		}
		thlDriver.get().manage().window().maximize();
		thlcLoginPage.set(new LoginPage(thlDriver.get()));
	}

	public WebDriver getDriver() {
		return thlDriver.get();
	}

	public LoginPage getLoginPage() {
		return thlcLoginPage.get();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		if (thlDriver.get() != null) {
			Thread.sleep(3000);
			thlDriver.get().quit();
			thlDriver.remove();

		}
	}
}
