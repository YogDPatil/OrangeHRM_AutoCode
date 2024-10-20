package com.ui.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.constants.Env;
import com.ui.pages.LoginPage;
import com.utils.TestUtils;

@Listeners(com.listners.UiListners.class)
public final class LoginPageTest extends TestBase {
	private LoginPage loginPage;

//	@BeforeTest(alwaysRun = true)
//	public void goToLoginPage() {
//		loginPage = new LoginPage(getDriver());
//	}

	@Test(description = "Test user can login using credentials and redirects dashboard page", groups = { "sanity",
			"e2e" })
	public void testLoginByUi() {
		Assert.assertEquals(getLoginPage().doLogin(testingEnvironment).getDashboardPageUrl("dashboard"),
				TestUtils.getValueFromPropertyFile("BASE_URL", testingEnvironment) + "/web/index.php/dashboard/index");
//		Assert.assertEquals(loginPage.doLogin(testingEnvironment).getDashboardPageUrl("dashboard"),
//				TestUtils.getValueFromPropertyFile("BASE_URL", testingEnvironment) + "/web/index.php/dashboard/index");

	}

}
