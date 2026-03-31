package com.opencart.qa.pages;
import java.lang.annotation.Retention;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	//1. Initial driver and element Util
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//Page class Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}
	
	//3. private By locators
	
	private final By emailId = By.id("input-email");
	private final By password = By.id("input-password");
	private final By forgotPwdLink = By.linkText("Forgotten Password");
	private final By loginBtn = By.cssSelector("input[type='submit']");
	private final By registerLink = By.linkText("Register");
	
	//4. public page action methods
	@Step("getting login page title...")
	public String getLoginPageTile() {
		String actTitle = eleUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login Page Title is:"+actTitle);
		return actTitle;
	}

	@Step("getting login page Url...")
	public String getLoginPageUrl() {
		String actUrl = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL, AppConstants.SHORT_TIME_OUT);
		System.out.println("Login Page Title is:"+actUrl);
		return actUrl;
	}
	
	@Step("Checkinf forgot password link is svailable or not")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForElementVisible(forgotPwdLink, AppConstants.MEDIUM_TIME_OUT).isDisplayed();
	}
	
	
	@Step("user is logged-in with username: {0} and password: {1}")
	public HomePage doLogin(String username, String pwd) {
		System.out.println("Credentials are: " + username + " : "+ password);
		eleUtil.doSendKeys(emailId, username , AppConstants.MEDIUM_TIME_OUT);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		System.out.println(driver.getTitle());
		return new HomePage(driver);
	}
	
	@Step("navigating to the register page...")
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.waitForElementReadyAndClick(registerLink, AppConstants.MEDIUM_TIME_OUT);
		return new RegistrationPage(driver);
	}
	
}
