package com.framework.reports;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport {
	
	public static ExtentReports extent = null;
	public static ExtentTest logger=null;
	
	//Method to generate empty htmlreport
	@BeforeSuite(alwaysRun=true)
	public static void SetupReport() {
		ExtentHtmlReporter htmlreport = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationTestReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlreport);
	}	
	
	//Method to start printing test results for each test case
	public static void startReporting(String testname) {
		logger=extent.createTest(testname);
	}
	
	//Method to Stop printing 
	public static void stopReporting() {
		extent.flush();
	}
	
	//Method to share print method 
	public static ExtentTest getLogger() {
		return logger;
	}
	
	

}
