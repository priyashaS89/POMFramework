package com.opencart.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.opencart.qa.exception.BrowserException;
import com.opencart.qa.exception.FrameworkException;


public class DriverFactory {

	WebDriver driver;
	Properties prop;
	FileInputStream fis;
	public OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This method is used to init the driver on the basis of given browserName.
	 * @param browserName
	 * @return it returns the driver value
	 */
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		System.out.println("browser name : " + browserName);
		
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			//driver = new SafariDriver();
			break;

		default:
			System.out.println("=====Invalid browser====="+ browserName);
			throw new BrowserException("====Invalid Browser====");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
		
	}
	
	/**
	 * returns the local copy of driver for a specific thread
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * This method is used to initialise the properties file.
	 * @return property class object which is having all the properties defined.
	 */
	public Properties initProp() {
		prop=new Properties();
		String envName = System.getProperty("env");
		System.out.println("The environment name is: "+envName);
		try {
		if (envName == null) {
			fis = new FileInputStream("C:\\Users\\priya\\Documents\\JavaTraining\\FWD_Selenium\\src\\test\\resources\\config\\config_def.properties");
			System.out.println("FIS: "+fis);
		}
		else {
		
		switch (envName.trim().toLowerCase()) {
			case "qa":
				fis = new FileInputStream("C:\\Users\\priya\\Documents\\JavaTraining\\FWD_Selenium\\src\\test\\resources\\config\\config.qa.properties");
				break;
			case "dev":
				fis = new FileInputStream("C:\\Users\\priya\\Documents\\JavaTraining\\FWD_Selenium\\src\\test\\resources\\config\\config.dev.properties");
				break;
			case "prod":
				fis = new FileInputStream("C:\\Users\\priya\\Documents\\JavaTraining\\FWD_Selenium\\src\\test\\resources\\config\\config.prod.properties");
				break;
			case "uat":
				fis = new FileInputStream("C:\\Users\\priya\\Documents\\JavaTraining\\FWD_Selenium\\src\\test\\resources\\config\\config.uat.properties");
				break;
			default:
				System.out.println("=========================Invalid environment name====================");
				throw new FrameworkException("Ïnvalid Enironment Exception "+ envName);
			}		
		}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		try{
			prop.load(fis);
			System.out.println("loading prop with fis: "+fis);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;	
	}
	
	/**
	 * 
	 * @Screenshot
	 */
	
	public static File getScreenshotFile() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);		//temp dir
	}
	
	
	public static byte[] getScreenshotByte () {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);	//temp dir
	}
	
	public static String getScreenshotBase64() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);	//temp dir
	}	
}
