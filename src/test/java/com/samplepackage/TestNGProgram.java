package com.samplepackage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.reports.ExtentReport;
import com.framework.utilities.ReadDataFromExcel;
import com.framework.utilities.ReadDataFromPropFile;

public class TestNGProgram {
	
	@BeforeSuite(groups={"regression","sanity","smoke"})
	public static void StartReporting() {
		System.out.println("Reporting Process Started");
	}	
	
	@BeforeMethod
	public static void launchBrowser() {
		System.out.println("Browser Launched");
	}
	
	@AfterMethod
	public static void CloseBrowser() {
		System.out.println("Browser Closed");
	}
	
	@Test(groups= {"Regression"})
	public static void TestCase1() {	
		ExtentReport.startReporting("Verify Test Case 1");
	}
	
	@Test(dependsOnMethods= {"TestCase1"},enabled =true,groups= {"Regression"})
	public static void TestCase2() {
		Assert.fail();
	}
	
	@Test(priority=3,enabled =true,groups= {"Sanity"})
	public static void TestCase3() {
		System.out.println("@Sanity: Test Case 3 Executed");
	}	
	
	@Test(dataProvider="testdata",priority=4,groups= {"Sanity"})
	public static void TestCase4(String Username, String Password,String Role) {
		System.out.println("@Sanity: Test Case 4 Executed With "+Username+" , "+Password+" , "+Role);
	}	
	
	@DataProvider(name="testdata")
	public String[][] data (){
//		String [][] data = {{"Anusha","1234","QA"},{"ABCDE","1234","Dev"}};
		String [][] data = ReadDataFromExcel.readExcelData("TestData.xlsx", "Sheet1");
		return data;
	}

}
