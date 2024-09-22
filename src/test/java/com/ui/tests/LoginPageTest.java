package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.constants.Env;
import com.utils.TestUtils;

public final class LoginPageTest extends TestBase {

	@Test(description = "Test user can login using credentials and redirects dashboard page")
	public void testLoginByUi() {
		Assert.assertEquals(loginPage.doLogin(Env.QA).getDashboardPageUrl("dashboard"),
				TestUtils.getValueFromPropertyFile("BASE_URL", Env.QA) + "/dashboard/index");
	}

}
