package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{   

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id="username")
	WebElement username;

	@FindBy(id="password")
	WebElement password;

	@FindBy(id="Login")
	WebElement loginButton;

	@FindBy(xpath="//*[@id='rememberUn']")
	WebElement rememberMe;

	@FindBy(id="forgot_password_link")
	WebElement forgotpwd;

	@FindBy(id="un")
	WebElement forgotuser;

	@FindBy(xpath="//div[@id='error']")
	WebElement loginErrorMsg;

	@FindBy(id="continue")
	WebElement continuebutton;



	@FindBy(xpath="//h1[@id='header'and normalize-space()='Check Your Email']")
	WebElement checkMail;

	public void enterUserName(String data) {
		waitUntiVisible(username, "username element");
		enterText(username,data, "user element" );
	}
	public void enterPassword(String data) {
		enterText(password,data,"password element");
	}
	public WebDriver clickLogin() {
		clickElement(loginButton, "login button element");
		return driver;
	}
	public void selectRemember() {
		clickElement(rememberMe, "remember me");
	}
	public void forgotPwd() {
		clickElement(forgotpwd, "forgotPwd");

	}
	public void forgotUser(String email)   {
		clickElement(continuebutton, "continue");
	}
	public String getErrorMessage() {
		if(loginErrorMsg.isDisplayed()) {
			return loginErrorMsg.getText();
		}
		else {
			return null;
		}

	}

}
