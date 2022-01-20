package com.framework.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.webdriver.WebDriverClass;

public class WebCommons {
	
	public WebDriver driver = WebDriverClass.getDriver();
	
	//Method to take screenshot
	public String takeScreenshot(String screenshotname) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot)driver; // to take screenshot
		File screenshotfile = screenshot.getScreenshotAs(OutputType.FILE);  // to convert screenshot into file format
		String screenshotpath =System.getProperty("user.dir")+"\\Screenshots\\"+screenshotname+"_"+uniqueid()+".png"; //to collect the path to store screenshots
		FileUtils.copyFile(screenshotfile, new File(screenshotpath)); // to copy screenshots file into folder
		return screenshotpath;
	}
	
	//method to generate random unique id
	public static String uniqueid() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		String uniqueId = sdf.format(Calendar.getInstance().getTime());
		return uniqueId;
	}	
	
	// Method to perform click action on element	
	public void Click(WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		element.click();
	}
	
	//Method to enter text into textbox
	public void EnterInfo(WebElement element, String value) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		element.clear();
		element.sendKeys(value);
	}
	
	//Method to get title
	public String getTitle() {
		return driver.getTitle();
	}

	//Method to select dropdown option
	public void selectDropdownOption(WebElement element, String value , String By) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		Select s = new Select(element);
		if(By.equalsIgnoreCase("Index")) {
			s.selectByIndex(Integer.parseInt(value));
		}else if(By.equalsIgnoreCase("Value")) {
			s.selectByValue(value);
		}else if(By.equalsIgnoreCase("VisibleText")) {
			s.selectByVisibleText(value);
		}
	}
	
	//Method to select checkbox
	public void SelectCheckbox(WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		if(!element.isSelected())
			element.click();
	}
	
	//Method to perform double click
	public void DoubleClick(WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
	}
	
	//Method to perform right click
	public void RightClick(WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
		Actions action = new Actions(driver);
		action.contextClick(element).build().perform();
	}
	
	//Method to wait -implicit
	public void ImplicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	//Method to wait for Element locator
	public void WaitForElement(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}
}
