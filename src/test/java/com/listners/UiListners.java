package com.listners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utils.BrowserUtils;

public class UiListners implements ITestListener {

	ExtentReports extentReports;
	ExtentSparkReporter extentSparkReports;
	ExtentTest extentTest;

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReports.createTest(result.getMethod().getMethodName());
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ITestListener.super.onTestFailure(result);
		extentTest.addScreenCaptureFromPath(BrowserUtils.getScreenshot(result.getMethod().getMethodName()));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		ITestListener.super.onStart(context);
		extentReports = new ExtentReports();
		Date date = new Date();
		SimpleDateFormat dateFromat = new SimpleDateFormat("yyyy-dd-MM hh:mm");
		String formatedDate = dateFromat.format(date);
		String reportFolderPath = System.getProperty("user.dir") + "/reports/";
		File reportFolder = new File(reportFolderPath);
		if (reportFolder.exists()) {
			FileHandler.delete(reportFolder);
			FileHandler.createDir(reportFolder);
		} else {
			FileHandler.createDir(reportFolder);
		}
		// File file = new File(reportFolderPath + "report -" + formatedDate);
		extentSparkReports = new ExtentSparkReporter(reportFolderPath + "report -" + formatedDate);
		extentReports.attachReporter(extentSparkReports);
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
	}

}
