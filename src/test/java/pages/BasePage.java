package pages;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.feb16.utility.ExtentReportsUtility;
import com.feb16.utility.PropertiesUtility;

public class BasePage {
	protected static WebDriver driver = null;//should be used in all the pages
	protected static WebDriverWait wait = null;
	protected static  Logger logger = null;
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();

	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public static void enterText(WebElement element,String text,String name) {
		if(element.isDisplayed()) {
			element.clear();
			element.sendKeys(text);
			//logger.info("Text entered in :" +name+  " field");
			//extentreport.logTestInfo("Text entered in :" +name+  " field");
		}
		else {
			logger.info("Text not entered in :" +name+  " field");
			extentreport.logTestInfo("Text not entered in :" +name+  " field");
		}
		driver.getTitle();
	}


	public static void clearElement(WebElement element, String objName) {
		if(element.isDisplayed()) {
			element.clear();
			logger.info("Pass: "+objName + "cleared");
			extentreport.logTestInfo("Pass: "+objName + "cleared");
		}
		else {
			logger.info("Fail: "+objName + " not cleared");
			extentreport.logTestInfo("Pass: "+objName + "cleared");
		}
	}

	public static void clickElement(WebElement element,String objName) {
		if(element.isDisplayed()) {
			element.click();
			//logger.info("Pass: "+objName + "clicked");
			//extentreport.logTestInfo("Pass: "+objName + "clicked");
		}
		else {
			logger.info("Fail: "+objName + " not clicked");
			extentreport.logTestInfo("Fail: "+objName + " not clicked");
		}
	}

	public static void sendKeysElement(WebElement element,String objName) throws IOException {
		PropertiesUtility propertiesutil = new PropertiesUtility();
		propertiesutil.loadFile("applicationData.Properties");
		String username = propertiesutil.getPropertyValue("login.valid.userid");

		if(element.isDisplayed()) {
			element.sendKeys(username);	
			//logger.info("Forgotuser element displayed: " +objName);
			//extentreport.logTestInfo("Forgotuser element displayed: " +objName);
		}
		else {
			//logger.info("Forgotuser element not displayed: " +objName);
			//extentreport.logTestInfo("Forgotuser element not displayed: " +objName);
		}
	}
	
	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);

	}

	public static String getTextWebElement(WebElement element,String name) {
		if(element.isDisplayed()) {
			return element.getText();
		}
		else {
			logger.info("Webelement"  +name+ " is not displayed");
			//extentreport.logTestInfo("Webelement"  +name+ " is not displayed");
			return null;
		}
	}
	public static void waitUntiVisible(WebElement element, String objName) {
		//logger.info("WebElement" +objName+ "is visible");
		wait=new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void closeBrowser() {
		driver.close();
	}
	

}


