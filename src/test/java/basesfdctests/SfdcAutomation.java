package basesfdctests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.feb16.salesforce.BaseTest;
import com.feb16.utility.PropertiesUtility;

import pages.HomePage;
import pages.LoginPage;


public class SfdcAutomation extends BaseTest{

	@Test
	public void ErrorLogin_Script() throws InterruptedException {
		//logger.info("inside salesforce method");
		//extentreport.logTestInfo("inside salesforce method");
		PropertiesUtility propertiesutil = new PropertiesUtility();
		propertiesutil.loadFile("applicationData.Properties");

		String username = propertiesutil.getPropertyValue("login.invalid.userid");
		String password = propertiesutil.getPropertyValue("login.invalid.password");
		waitUntilPageLoads();

		String errorexpected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";

		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(username);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();

		String actualErrorMessage = loginpage.getErrorMessage(); 

		Assert.assertEquals(actualErrorMessage, errorexpected);

	}
	@Test

	public void loginToSfdc() throws InterruptedException   {
		PropertiesUtility propertiesutil = new PropertiesUtility();
		propertiesutil.loadFile("applicationData.Properties");

		String username = propertiesutil.getPropertyValue("login.valid.userid");
		String password = propertiesutil.getPropertyValue("login.valid.password");

		String expected = "Home Page ~ Salesforce - Developer Edition";
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(username);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();
		Thread.sleep(3000);
		HomePage homepage = new HomePage(driver);
		String actual = homepage.verifyPageTitle(expected);
		System.out.println("Page Title : "+actual);
		Assert.assertEquals(actual, expected);


	}
	@Test

	public  void RememberUn() throws IOException, InterruptedException {
		//logger.info("Inside RememberUn Method");
		//extentreport.logTestInfo("Inside RememberUn Method");
		PropertiesUtility propertiesutil = new PropertiesUtility();
		propertiesutil.loadFile("applicationData.Properties");
		String username = propertiesutil.getPropertyValue("login.valid.userid");
		String password = propertiesutil.getPropertyValue("login.valid.password");

		String expected = "Home Page ~ Salesforce - Developer Edition";
		Thread.sleep(3000);
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(username);
		loginpage.enterPassword(password);
		loginpage.selectRemember();
		driver = loginpage.clickLogin();
		Thread.sleep(3000);

		HomePage homepage = new HomePage(driver);
		String actual = homepage.verifyPageTitle(expected);
		System.out.println("Page Title : "+actual);
		Assert.assertEquals(actual, expected);
	}
	@Test

	public void Forgotpwd() throws IOException, InterruptedException  {
		//logger.info("Inside Forgotpwd Method");
		//extentreport.logTestInfo("Inside Forgotpwd Method");
		PropertiesUtility propertiesutil = new PropertiesUtility();
		propertiesutil.loadFile("applicationData.Properties");
		String username = propertiesutil.getPropertyValue("login.valid.userid");
		String email = propertiesutil.getPropertyValue("gmail.id");

		String expected = "Forgot Your Password | Salesforce";
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(username);
		loginpage.forgotPwd();
		waitUntilPageLoads();

		HomePage homepage = new HomePage(driver);
		String pageTitle = homepage.verifyPageTitle(expected);
		System.out.println("Page Title : "+pageTitle);
		Assert.assertEquals(pageTitle, expected);
		waitUntilPageLoads();

		loginpage.forgotUser(email);
	
		
		/*
		 * String check = "Check Your Email | Salesforce"; waitUntilPageLoads();
		 * HomePage homepage2 = new HomePage(driver); String checkemail =
		 * homepage2.verifyPageTitle(check);
		 * System.out.println("Page Title: "+checkemail);
		 * Assert.assertEquals(checkemail,check);
		 */

	}
	@Test

	public void wrongUser() throws IOException, InterruptedException {
		//logger.info("Inside Wrong user Method");
		//extentreport.logTestInfo("Inside Wrong user Method");

		PropertiesUtility propertiesutil = new PropertiesUtility();
		propertiesutil.loadFile("applicationData.Properties");
		String username = propertiesutil.getPropertyValue("login.invalid.userid");
		String password = propertiesutil.getPropertyValue("login.invalid.password");

		String expected = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		
		LoginPage loginpage = new LoginPage(driver);
		loginpage.enterUserName(username);
		loginpage.enterPassword(password);
		driver = loginpage.clickLogin();
		waitUntilPageLoads();
		
		
		String actual = loginpage.getErrorMessage();
		System.out.println("Page Title :"+actual);
		Assert.assertEquals(actual, expected);

	}
}