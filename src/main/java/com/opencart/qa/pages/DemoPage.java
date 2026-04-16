package com.opencart.qa.pages;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
public class DemoPage {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = null;
        
        FirefoxOptions fo = new FirefoxOptions();
        fo.setCapability("browserName", "firefox");

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), fo);
            //driver = new FirefoxDriver();
        	driver.get("https://www.google.com");
            Thread.sleep(20000);
            System.out.println(driver.getTitle());
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}