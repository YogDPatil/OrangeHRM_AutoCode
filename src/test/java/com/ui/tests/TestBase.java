package com.ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverInfo;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.constants.Env;
import com.ui.pages.LoginPage;
import com.utils.TestUtils;

public abstract class TestBase {

	private WebDriver driver;
	protected LoginPage loginPage;

	protected String env;

	@Parameters({ "browser", "env" })
	@BeforeTest()
	public void setUp(@Optional("chrome") String browser, @Optional("qa") String environment) {
		env = environment;
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
		// driver.get(TestUtils.getValueFromPropertyFile("BASE_URL", Env.QA) +
		// "/web/index.php/auth/login");
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() throws InterruptedException {
		if (driver != null) {
			Thread.sleep(3000);
			driver.quit();
		}
	}
}
