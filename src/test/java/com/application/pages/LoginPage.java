package com.application.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.framework.commons.WebCommons;
import com.framework.utilities.ReadDataFromPropFile;
import com.framework.webdriver.WebDriverClass;

public class LoginPage extends WebCommons{	
	
	ExtentTest logger = WebDriverClass.getLogger();
	
	//WebElements related to Login page
	
	@FindBy(xpath="//img[contains(@src,'logo.png')]")
	private WebElement logo;
	
	@FindBy(id="logInPanelHeading")
	private WebElement logInPanelHeading;
	
	@FindBy(name="txtUsername")
	private WebElement usernameTextbox;
	
	@FindBy(name="txtPassword")
	private WebElement passwordTextbox;
	
	@FindBy(name="Submit")
	private WebElement loginButton;
	
	@FindBy(linkText="Forgot your password?")
	private WebElement forgotYourPassword;
	
	private By welcomePageLocator = By.xpath("//a[@id='welcome']");
	
	//Actions for Login Page

	//Method to Launch the Application
	public void launchApplication() throws IOException {
		try {
			driver.get(ReadDataFromPropFile.readProperties("Config.properties").getProperty("Url"));
			logger.pass("Application Launched Successfully");
		}catch(Exception e) {
			logger.fail("Error while Launching the Application");
			logger.addScreenCaptureFromPath(takeScreenshot("AppLaunch"));
			Assert.fail("Error while Launching the Application");
		}
	}
	
	//Method to Verify Application Title
	public void verifyApplicationTitle() throws IOException {
		if(getTitle().equals(ReadDataFromPropFile.readProperties("Config.properties").getProperty("Title"))) {
			logger.pass("Application Title is Displayed as Expected");
		}else {
			logger.fail("Application Title is Not Matching");
			logger.addScreenCaptureFromPath(takeScreenshot("AppTitle"));
			Assert.fail("Application Title is Not Matching");
		}
	}
	
	//Method to login into the application
	public void loginIntoApplication(String username,String password) throws IOException {
		try {
			logger.info("Test Data used : "+username+" , "+password);
			EnterInfo(usernameTextbox, username);
			EnterInfo(passwordTextbox, password);
			Click(loginButton);
			logger.pass("Application Login is executed successfully");			
		}catch(Exception e) {
			logger.fail("Error while Login into the Application");
			logger.addScreenCaptureFromPath(takeScreenshot("AppLogin"));
			Assert.fail("Error while Login into the Application");
		}
	}
		
		//Method to verify Login Successful
		public void verifySuccessfulLogin() throws IOException {
			try {
				WaitForElement(welcomePageLocator);	
				logger.pass("Application Login is Successful");		

			}catch(Exception e) {
				logger.fail("Application Login is not Successful");
				logger.addScreenCaptureFromPath(takeScreenshot("Welcome"));
				Assert.fail("Application Login is not Successful");
			}
	}
	
	public static LoginPage getLoginPage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), LoginPage.class);
	}
}
