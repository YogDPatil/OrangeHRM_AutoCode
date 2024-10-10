package com.ui.pages;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.utils.BrowserUtils;
import com.utils.TestUtils;

public final class LeavePage extends BrowserUtils {

	WebDriver driver;
	private List<WebElement> leaveRecordsOnPage;
	private List<String> leaveRecords;

	private static final By APPLY_LEAVE_TAB_LINK = By.linkText("Apply");
	private static final By LEAVE_TYPE_DROPDOWN_LOCATOR = By.xpath("//div[contains(text(),'Select')]//..");
	private static final By LIVE_TYPE_LIST_LOCATOR = By.xpath("//div[@role='listbox']/div");
	private static final By FROM_DATE_PICKER_LOCATOR = By.xpath(
			"//label[text()='From Date']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//input");
	private static final By TO_DATE_PICKER_LOCATOR = By.xpath(
			"//label[text()='To Date']/ancestor::div[@class='oxd-input-group oxd-input-field-bottom-space']//input");
	private static final By NEXT_MONTH_BUTTON_ON_DATE_PICKER_LOCATOR = By.xpath("//i[contains(@class,'right')]//..");
	private static final By COMMENT_TEXT_FIELD_LOCATOR = By.xpath("//div[@class='oxd-form-row']//textarea");
	private static final By LEAVE_APPLY_BUTTON = By.xpath("//button[@type='submit']");
	private static final By LEAVE_RECORDS_CARD_LOCATOR = By.xpath("//div[@class='oxd-table-card']");
	private static final By MY_LEAVE_TAB_LINK_LOCATOR = By.linkText("My Leave");
	private static final By PAGINATION_NEXT_BUTTON_LOCATOR = By
			.xpath("//ul[@class='oxd-pagination__ul']//li//i[contains(@class,'right')]");

	public LeavePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public String getLeavePageUrl(String urlEndPoint) {
		return getCurrentPageUrl(urlEndPoint);
	}

	public void applyLeave() throws Exception {
		clickOn(APPLY_LEAVE_TAB_LINK);
		clickOn(LEAVE_TYPE_DROPDOWN_LOCATOR);
		selectValueFromDropdown(LIVE_TYPE_LIST_LOCATOR, "./span", "CAN - FMLA");
		clickOn(FROM_DATE_PICKER_LOCATOR);
		String leaveDate = getNextDayDate(NEXT_MONTH_BUTTON_ON_DATE_PICKER_LOCATOR);
		By from_date_of_leave_locator = By.xpath("//div[@class='oxd-calendar-date' and text()='" + leaveDate + "']");
		clickOn(from_date_of_leave_locator);
		clickOn(TO_DATE_PICKER_LOCATOR);
		By to_date_of_leave_locator = By.xpath("//div[@class='oxd-calendar-date' and text()='" + leaveDate + "']");
		clickOn(to_date_of_leave_locator);
		String comment = TestUtils.getRandomText();
		System.out.println("******* COMMENT ******" + comment);
		enterText(COMMENT_TEXT_FIELD_LOCATOR, comment);
		clickOn(LEAVE_APPLY_BUTTON);
		clickOn(MY_LEAVE_TAB_LINK_LOCATOR);
//		try {
//			leave_records = getListOfWebElement(LEAVE_RECORDS_CARD_LOCATOR);
//			leaveRecords = new ArrayList<String>();
//			for (WebElement leaveRecord : leave_records) {
//				String appliedLeave = leaveRecord.findElement(By.xpath(".//div[8]//div")).getText();
//				leaveRecords.add(appliedLeave);
//			}
//			System.out.println("Page_1" + leaveRecords);
//			while (webElement(PAGINATION_NEXT_BUTTON_LOCATOR).isDisplayed()) {
//				webElement(PAGINATION_NEXT_BUTTON_LOCATOR).click();
//				leave_records = getListOfWebElement(LEAVE_RECORDS_CARD_LOCATOR);
//				for (WebElement leaveRecord : leave_records) {
//					String appliedLeave = leaveRecord.findElement(By.xpath(".//div[8]//div")).getText();
//					leaveRecords.add(appliedLeave);
//				}
//			}
//		} catch (Exception e) {
//			System.out.println("No next page is available");
//		}
//		if(leaveRecords.contains(TestUtils.getRandomText())) {
//			System.out.println("leave applied successfully!!!");
//		}else {
//			throw new Exception("Leave is not applied ");
//		}

		boolean hasNextPage = true;
		while (hasNextPage) {
			leaveRecordsOnPage = getListOfWebElement(LEAVE_RECORDS_CARD_LOCATOR);
			leaveRecords = new ArrayList<String>();
			for (WebElement leaveRecord : leaveRecordsOnPage) {
				leaveRecords.add(leaveRecord.findElement(By.xpath(".//div[8]//div")).getText());
			}
			System.out.println(leaveRecords);
			try {
				JavascriptExecutor je = (JavascriptExecutor) driver;
				je.executeScript("arguments[0].scrollIntoView();", webElement(PAGINATION_NEXT_BUTTON_LOCATOR));
				if (webElement(PAGINATION_NEXT_BUTTON_LOCATOR).isDisplayed()) {
					webElement(PAGINATION_NEXT_BUTTON_LOCATOR).click();
				} else {
					hasNextPage = false;
				}
			} catch (Exception e) {
				System.out.println("No next page is available");
				hasNextPage = false;
			}
			if (leaveRecords.contains(comment)) {
				System.out.println("leave applied successfully!!!");
				break;
			} else {
				throw new Exception("Leave is not applied ");
			}
		}

//		do {
//			leaveRecordsOnPage = getListOfWebElement(LEAVE_RECORDS_CARD_LOCATOR);
//			leaveRecords = new ArrayList<String>();
//			for (WebElement leaveRecord : leaveRecordsOnPage) {
//				leaveRecords.add(leaveRecord.findElement(By.xpath(".//div[8]//div")).getText());
//			}
//
//			try {
//				JavascriptExecutor je = (JavascriptExecutor) driver;
//				je.executeScript("arguments[0].scrollIntoView();", webElement(PAGINATION_NEXT_BUTTON_LOCATOR));
//				if (webElement(PAGINATION_NEXT_BUTTON_LOCATOR).isDisplayed()) {
//					webElement(PAGINATION_NEXT_BUTTON_LOCATOR).click();
//				} else {
//					break;
//				}
//			} catch (Exception e) {
//				System.out.println("No next page is available");
//			}
//			if (leaveRecords.contains(comment)) {
//				System.out.println("leave applied successfully!!!");
//			} else {
//				throw new Exception("Leave is not applied ");
//			}
//
//		} while (true);

	}

}
