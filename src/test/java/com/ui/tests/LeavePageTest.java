package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.LeavePage;
import com.utils.TestUtils;

@Listeners(com.listners.UiListners.class)
public final class LeavePageTest extends TestBase {

	private LeavePage leavePage;

	@BeforeMethod
	public void goToLeavePage() {
		leavePage = loginPage.doLogin(testingEnvironment).goToLeavePage();
	}

	@Test(description = "Test after clicking leave tab user navigates to leave page")
	public void testUserNavigateToLeavePage() {

		Assert.assertEquals(leavePage.getLeavePageUrl("viewLeaveList"),
				TestUtils.getValueFromPropertyFile("BASE_URL", testingEnvironment) + "/leave/viewLeaveList");
	}

	@Test(description = "Test user can apply leave from ui")
	public void testUserApplyLeaveFromUi() {
		leavePage.applyLeave();
	}
}
