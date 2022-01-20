package com.application.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.application.pages.ForgotPasswordPage;
import com.application.pages.HomePage;
import com.application.pages.LoginPage;
import com.framework.utilities.ReadDataFromExcel;
import com.framework.webdriver.WebDriverClass;

public class ApplicationTest extends WebDriverClass{
	
	@Test(priority=1,groups= {"Smoke"})
	public void verifyApplicationTitle() throws IOException {
		startReporting("Verify Application Title");
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.launchApplication();
		loginpage.verifyApplicationTitle();		
	}
	
	@Test(dataProvider="LoginData",priority=2,groups= {"Regression","Sanity"})
	public void verifyApplicationLogin(String Username, String Password) throws IOException {
		startReporting("Verify Application Login");
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.launchApplication();
		loginpage.verifyApplicationTitle();
		loginpage.loginIntoApplication(Username, Password);
		loginpage.verifySuccessfulLogin();
	}
	
	@Test(priority=3,groups= {"Functional"})
	public void verifyForgotPasswordPage() throws IOException {
		startReporting("Verify Forgot Password Page");
		LoginPage loginpage = LoginPage.getLoginPage();
		ForgotPasswordPage forgotpasswordpage = ForgotPasswordPage.getForgotPasswordPage();
		loginpage.launchApplication();
		loginpage.verifyApplicationTitle();		
		forgotpasswordpage.verifyForgotPasswordPageElements();
	}
	
	@Test(dataProvider="LogoutData",priority=4,groups= {"Sanity"})
	public void verifyApplicationLogout(String Username, String Password) throws IOException {
		startReporting("Verify Application Logout");
		LoginPage loginpage = LoginPage.getLoginPage();
		HomePage homepage = HomePage.getHomePage();
		loginpage.launchApplication();
		loginpage.verifyApplicationTitle();
		loginpage.loginIntoApplication(Username, Password);
		loginpage.verifySuccessfulLogin();
		homepage.verifyApplicationLogout();
	}
	
	
	@DataProvider(name="LoginData")
	public String[][] loginData() {
		String [][] data = ReadDataFromExcel.readExcelData("TestData.xlsx", "LoginData");
		return data;		
	}
	
	@DataProvider(name="LogoutData")
	public String[][] logoutData() {
		String [][] data = ReadDataFromExcel.readExcelData("TestData.xlsx", "LogoutData");
		return data;		
	}
	
}
