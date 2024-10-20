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
	private static ThreadLocal<WebDriver> thlcDriver = new ThreadLocal<WebDriver>();
	private ThreadLocal<LoginPage> thlcLoginPage = new ThreadLocal<LoginPage>();

	protected Env testingEnvironment;

	@Parameters({ "browser", "env" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, @Optional("qa") String env) {
		testingEnvironment = Env.valueOf(env.toUpperCase());

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
			thlcDriver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
			thlcDriver.set(new FirefoxDriver());
		}
		thlcDriver.get().manage().window().maximize();
		thlcLoginPage.set(new LoginPage(thlcDriver.get()));
	}

//	@Parameters({ "browser", "env" })
//	public WebDriver setUpDriver(@Optional("chrome") String browser, @Optional("qa") String env) {
//		testingEnvironment = Env.valueOf(env.toUpperCase());
//		if (browser == null || browser.isEmpty())
//			browser = "chrome";
//		if (env == null || env.isEmpty())
//			env = "qa";
//
//		if (browser.equalsIgnoreCase("chrome")) {
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
//			thlcDriver.set(new ChromeDriver());
//		} else if (browser.equalsIgnoreCase("firefox")) {
//			System.setProperty("webdriver.gecko.driver",
//					System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
//			thlcDriver.set(new FirefoxDriver());
//		}
//		thlcDriver.get().manage().window().maximize();
//		return thlcDriver.get();
//	}

	public WebDriver getDriver() {
		return thlcDriver.get();
	}

	public LoginPage getLoginPage() {
		return thlcLoginPage.get();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		if (thlcDriver.get() != null) {
			Thread.sleep(3000);
			thlcDriver.get().quit();
			thlcDriver.remove();

		}
	}
}
