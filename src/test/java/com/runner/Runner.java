package com.runner;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlSuite.ParallelMode;
import org.testng.xml.XmlTest;

import com.constants.Browser;
import com.constants.Componant;
import com.constants.Env;
import com.constants.TestType;

public class Runner {
	static String componant, testType, browser, env;

	public static void main(String[] args) {
		componant = args[0];
		testType = args[1];
		browser = args[2];
		env = args[3];

		Componant comp = Componant.valueOf(componant.toUpperCase());
		Browser brow = Browser.valueOf(browser.toUpperCase());
		String env = String.valueOf(Env.QA);
		TestNG testNg = new TestNG();

		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName(env + " Suite");
		xmlSuite.setVerbose(2);

		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName(comp + " test on " + brow);
		xmlTest.addParameter("browser", browser);
		xmlTest.addParameter("env", env);
		xmlTest.setParallel(ParallelMode.METHODS);

		String componant = String.valueOf(comp).toLowerCase();
		List<XmlPackage> packageList = new ArrayList<>();
		XmlPackage xmlPackage = new XmlPackage("com." + componant + ".tests");
		packageList.add(xmlPackage);
		xmlTest.setPackages(packageList);
		xmlTest.addIncludedGroup(testType);

		List<XmlTest> testList = new ArrayList<>();
		testList.add(xmlTest);
		xmlSuite.setTests(testList);

		List<XmlSuite> suiteList = new ArrayList<>();
		suiteList.add(xmlSuite);

		testNg.setXmlSuites(suiteList);
		testNg.run();
	}
}
