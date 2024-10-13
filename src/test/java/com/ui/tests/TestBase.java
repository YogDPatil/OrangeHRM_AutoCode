package com.ui.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.constants.Env;
import com.ui.pages.LoginPage;

public abstract class TestBase {

	private WebDriver driver;
	protected LoginPage loginPage;

	protected Env testingEnvironment;

	@Parameters({ "browser", "env" })
	@BeforeTest(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, @Optional("qa") String env) {
		testingEnvironment = Env.valueOf(env.toUpperCase());
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}
}
