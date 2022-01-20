package com.framework.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.framework.reports.ExtentReport;
import com.framework.utilities.ReadDataFromPropFile;

public class WebDriverClass extends ExtentReport{
	
	private static WebDriver driver;
	
	//Launch the Browser Window
	@BeforeMethod(alwaysRun=true)
	public static synchronized void launchBrowser() {
		String browsername= ReadDataFromPropFile.readProperties("Config.properties").getProperty("Browser");
		if(browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		}else if(browsername.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}else if(browsername.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	//Close Browser Window
	@AfterMethod(alwaysRun=true)
	public static synchronized void CloseBrowser() {
		driver.quit();
		ExtentReport.stopReporting();
	}
	
	//Method to share driver details
	public static WebDriver getDriver() {
		return driver;		
	}
	

}
