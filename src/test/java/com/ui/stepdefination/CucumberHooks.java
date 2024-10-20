package com.ui.stepdefination;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Env;
import com.utils.TestUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class CucumberHooks {

	private WebDriver driver;
	StepDefTestBase stepDefTB;
	private static String selBrowser;

	

	@Before
	public void driverSetUp() {
		stepDefTB = new StepDefTestBase();
		driver = stepDefTB.init_driver(TestUtils.getValueFromPropertyFile("BROWSER", Env.QA));

	}

	@After
	public void tearDown() {
		if (driver != null)
			driver.quit();
	}

}
