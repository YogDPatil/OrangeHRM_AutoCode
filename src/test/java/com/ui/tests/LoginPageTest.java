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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.constants.Env;
import com.utils.TestUtils;

@Listeners(com.listners.UiListners.class)
public final class LoginPageTest extends TestBase {

	@Test(description = "Test user can login using credentials and redirects dashboard page", groups = { "sanity",
			"e2e" })
	public void testLoginByUi() {
		AssertJUnit.assertEquals(loginPage.doLogin(testingEnvironment).getDashboardPageUrl("dashboard"),
				TestUtils.getValueFromPropertyFile("BASE_URL", testingEnvironment) + "/web/index.php/dashboard/index");

	}

}
