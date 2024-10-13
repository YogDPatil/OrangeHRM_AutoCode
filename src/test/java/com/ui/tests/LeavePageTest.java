package com.ui.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import com.ui.pages.LeavePage;
import com.ui.pages.LoginPage;
import com.utils.TestUtils;

@Listeners(com.listners.UiListners.class)
public final class LeavePageTest extends TestBase {

	private LeavePage leavePage;
	private LoginPage loginPage;

	@BeforeMethod(alwaysRun = true)
	public void goToLeavePage() {
//		loginPage = new LoginPage(getDriver());
		leavePage = getLoginPage().doLogin(testingEnvironment).goToLeavePage();
//		leavePage = loginPage.doLogin(testingEnvironment).goToLeavePage();
	}

	@Test(description = "Test after clicking leave tab user navigates to leave page", groups = { "smoke", "sanity",
			"e2e" })
	public void testUserNavigateToLeavePage() {
		Assert.assertEquals(leavePage.getLeavePageUrl("viewLeaveList"),
				TestUtils.getValueFromPropertyFile("BASE_URL", testingEnvironment)
						+ "/web/index.php/leave/viewLeaveList");
	}

	@Test(description = "Test user can apply leave from ui", groups = { "smoke", "e2e" })
	public void testUserApplyLeaveFromUi() throws Exception {
		leavePage.verifyUserAplliedLeaveFromUi();
	}

	@Test(groups = { "e2e" })
	public void TestZlickOnTimeTab() {
		leavePage.clickOnTimeTab();
	}
}
