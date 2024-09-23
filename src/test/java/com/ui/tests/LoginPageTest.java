package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.constants.Env;
import com.utils.TestUtils;

@Listeners(com.listners.UiListners.class)
public final class LoginPageTest extends TestBase {

	@Test(description = "Test user can login using credentials and redirects dashboard page")
	public void testLoginByUi() {
		Assert.assertEquals(loginPage.doLogin(Env.valueOf(env.toUpperCase())).getDashboardPageUrl("dashboard"),
				TestUtils.getValueFromPropertyFile("BASE_URL", Env.valueOf(env.toUpperCase())) + "/dashboard/index");

	}

}
