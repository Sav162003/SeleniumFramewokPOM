package com.feb16.salesforce;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.feb16.utility.Constants;
import com.feb16.utility.ExtentReportsUtility;
import com.feb16.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest { 

	protected static WebDriver driver= null;
	protected static Logger logger = null;
	protected static ExtentReportsUtility extentreport =ExtentReportsUtility.getInstance() ;

	@BeforeTest
	public void set_UpBeforeTest() {
		//extentreport.logTestInfo("beforeTest Method has Started");
		System.out.println("Inside @Before Test Method");
		//logger = LogManager	.getLogger(BaseTest.class.getName());
	}

	@BeforeMethod
	@Parameters("browsername")
	public void BeforeTestMethod(@Optional("chrome")String browserName,Method method) throws IOException{

		//logger.info("Testcase executed : "+method.getName());
		//extentreport.logTestInfo("Testcase executed : "+method.getName());

		PropertiesUtility propertiesutil = new PropertiesUtility();
		propertiesutil.loadFile("applicationData.Properties");
		String url = propertiesutil.getPropertyValue("url");
		if(url==null) {
			System.out.println("Url not found in properties file");
			return;
		}
		GetBrowserInstance(browserName);
		GotoUrl(url);
	}
	@AfterMethod

	public void AfterTestMethod() throws IOException {
		//logger.info("Browser closed");
		//extentreport.logTestInfo("Browser closed");
		driver.close();

	}

	public static void  GetBrowserInstance(String browserName) {
		switch(browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		default:
			System.out.println("Not a valid browsername");
			logger.info("Not a valid browsername");
			extentreport.logTestInfo("Not a valid browsername");
		}	
	}

	public WebDriver returnDriverInstance() { 
		return driver; 
	}

	public static void GotoUrl(String url) {
		//logger.info("Url launched");
		//extentreport.logTestInfo("Url launched");
		driver.get(url);
		waitUntilPageLoads();
	}


	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);

	}
	
	public static void getPageScreenshot()  {
		//logger.info("Screenshot generated");
		extentreport.logTestInfo("Screenshot generated");;
		String date = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File image = screenShot.getScreenshotAs(OutputType.FILE);
		File location = new File(Constants.SCREENSHOT_DIR_PATH+date+".png"); 
		try{
			FileHandler.copy(image, location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getPageScreenshot(WebDriver driver)  {
		logger.info("Screenshot generated");
		extentreport.logTestInfo("Screenshot generated");;
		String date = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File image = screenShot.getScreenshotAs(OutputType.FILE);
		File location = new File(Constants.SCREENSHOT_DIR_PATH+date+".png"); 
		try{
			FileHandler.copy(image, location);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return location.getAbsolutePath();
	}
}

