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

public class OrangeHrmRunner {

	public static void main(String[] args) {
		String env = String.valueOf(Env.QA);
		TestNG testNg = new TestNG();

		XmlSuite xmlSuite = new XmlSuite();
		xmlSuite.setName(env + " Suite");
		xmlSuite.setVerbose(2);

		XmlTest xmlTest = new XmlTest(xmlSuite);
		xmlTest.setName(Componant.UI + " test on " + Browser.CHROME);
		xmlTest.addParameter("browser", "chrome");
		xmlTest.addParameter("env", "qa");
		xmlTest.setParallel(ParallelMode.METHODS);

		String componant = String.valueOf(Componant.UI).toLowerCase();
		List<XmlPackage> packageList = new ArrayList<>();
		XmlPackage xmlPackage = new XmlPackage("com." + componant + ".tests");
		packageList.add(xmlPackage);
		xmlTest.setPackages(packageList);
		xmlTest.addIncludedGroup("e2e");

		List<XmlTest> testList = new ArrayList<>();
		testList.add(xmlTest);
		xmlSuite.setTests(testList);

		List<XmlSuite> suiteList = new ArrayList<>();
		suiteList.add(xmlSuite);

		testNg.setXmlSuites(suiteList);
		testNg.run();
	}
}
