
package com.atmecs.practice1.dynamic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.atmecs.practice1.constant.FilePath;
import com.atmecs.practice1.reports.ExtentReporterNG;
import com.atmecs.practice1.util.ReadProp;

public class Testng {

	Properties baseClass;
	String browser;

	@SuppressWarnings("deprecation")
	@Test(priority = 1, dataProvider = "classname", dataProviderClass = DataProvider.class)
	public void base(String classname, String url) throws IOException {
		baseClass = ReadProp.loadProperty(FilePath.CONFIG_FILE);
		browser = baseClass.getProperty("browser");
		String[] browser2 = browser.split(",");

		XmlSuite xmlsuite = new XmlSuite();
		xmlsuite.setName("suitename");
		xmlsuite.setVerbose(2);
		xmlsuite.setThreadCount(3);
		String mode = baseClass.getProperty("mode");
		if (mode.equalsIgnoreCase("parallel")) {
			xmlsuite.setParallel("tests");
		}

		for (String element : browser2) {

			XmlTest xmltest = new XmlTest(xmlsuite);
			Map<String, String> parameter = new HashMap<String, String>();
			parameter.put("browser", element);
			parameter.put("url", url);

			xmltest.setParameters(parameter);

			xmltest.setName("Test validate " + element);

			// XmlClass homePage = new XmlClass(ProductSearchTestScripts.class);
			XmlClass productDetail = new XmlClass(classname);
			List<XmlClass> list = new ArrayList<XmlClass>();
			list.add(productDetail);
			// list.add(productDetail);
			xmltest.setXmlClasses(list);

		}

		TestNG testng = new TestNG();

		List<Class<? extends ITestNGListener>> listenerClasses = new ArrayList<Class<? extends ITestNGListener>>();
		listenerClasses.add(ExtentReporterNG.class);
		testng.setListenerClasses(listenerClasses);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(xmlsuite);
		testng.setXmlSuites(suites);

		testng.run();

	}

}
