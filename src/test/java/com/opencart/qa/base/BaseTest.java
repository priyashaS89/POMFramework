package com.opencart.qa.base;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.opencart.qa.factory.DriverFactory;
import com.opencart.qa.listeners.TestAllureListener;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.pages.LoginPage;
import com.opencart.qa.pages.ResultsPage;
import com.opencart.qa.pages.ProductInfoPage;
import com.opencart.qa.pages.RegistrationPage;

//@Listeners({ChainTestListener.class, TestAllureListener.class})
public class BaseTest {
	
	protected WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected HomePage homePage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registrationPage;

	@BeforeTest
	@Parameters({"browser"})
	public void setUp(@Optional("chrome") String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		if(browserName!=null)//browser name is coming from xml file
		{
			prop.setProperty("browser", browserName);
		}
		driver =  df.initDriver(prop);
		prop=df.initProp();
		loginPage= new LoginPage(driver);
			}
	@AfterMethod
	public void takeScreenshot(ITestResult result) {
		if(!result.isSuccess()) {
			ChainTestListener.embed(DriverFactory.getScreenshotFile(),"iamge/png");
		}
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
