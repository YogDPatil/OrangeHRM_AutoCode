package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.utils.BrowserUtils;

public final class LeavePage extends BrowserUtils {

	WebDriver driver;

	private static final By APPLY_LEAVE_TAB_LINK = By.linkText("Apply");
	private static final By LEAVE_TYPE_DROPDOWN_LOCATOR = By.xpath("//div[contains(text(),'Select')]//..");
	private static final By LIVE_TYPE_LIST_LOCATOR = By.xpath("//div[@role='listbox']/div");

	public LeavePage(WebDriver driver) {
		super(driver);
	}

	public String getLeavePageUrl(String urlEndPoint) {
		return getCurrentPageUrl(urlEndPoint);
	}

	public void applyLeave() {
		clickOn(APPLY_LEAVE_TAB_LINK);
		clickOn(LEAVE_TYPE_DROPDOWN_LOCATOR);
		//selectValueFromDropdown(LIVE_TYPE_LIST_LOCATOR, "./span", "CAN - FMLA");
	}
}
