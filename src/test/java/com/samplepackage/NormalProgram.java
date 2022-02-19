package com.samplepackage;

public class NormalProgram {
	

	public static void StartReporting() {
		System.out.println("Reporting Process Started");
	}	
	
	public static void launchBrowser() {
		System.out.println("Browser Launched");
	}
	
	public static void CloseBrowser() {
		System.out.println("Browser Closed");
	}
	
	//public static void TestCase1() {
		//System.out.println("@Regression: Test Case 1 Executed");
	//}

	public static void TestCase2() {
		System.out.println("@Regression: Test Case 2 Executed");
	}
	
	public static void TestCase3() {
		System.out.println("@Sanity: Test Case 3 Executed");
	}	
	
	public static void TestCase4(String Username, String Password) {
		System.out.println("@Sanity: Test Case 4 Executed With "+Username+" , "+Password);
	}	
	
	
	public static void main(String[] args) {
		
		StartReporting();
		
		launchBrowser();
		TestCase1();
		CloseBrowser();
		
		launchBrowser();
		TestCase2();
		CloseBrowser();
		
		launchBrowser();
		TestCase3();
		CloseBrowser();
		
		launchBrowser();
		TestCase4("Anusha","1234");
		CloseBrowser();
		
		launchBrowser();
		TestCase4("Hemanth","2345");
		CloseBrowser();

	}
}
