package com.runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/UIFeatureFiles",
	glue =  "com.ui.stepdefination", 
	monochrome = true,  
	tags = "@Test1",
	dryRun = false
	)
public class UiCucumberRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}

}
