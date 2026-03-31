package com.opencart.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class HomePage {
	private ElementUtil ele_util;
	private WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		ele_util=new ElementUtil(driver);
	}
	
	//PO
	private final By searchTextField = By.name("search");
	private final By logoutLink = By.linkText("Logout");
	private final By headers = By.cssSelector("div#content h2");
	private final By searchIcon =  By.cssSelector("#search button");
	
	
	public boolean islogoutLinkExist() {
		return ele_util.isElementDisplayed(logoutLink);
	}
	
	 public String getHomePageTitle() {
		 String actTitle  = ele_util.waitForTitleIs(AppConstants.HOME_PAGE_TITLE, AppConstants.SHORT_TIME_OUT);
		 System.out.println("Home Page title : " + actTitle);
			return actTitle;
	 }
	 
	 public List<String> getHomePageHeaders() {
			List<WebElement> headersList = ele_util.waitForAllElementsPresence(headers, AppConstants.SHORT_TIME_OUT);
			List<String> headersValueList = new ArrayList<String>();
			for (WebElement e : headersList) {
				String text = e.getText();
				headersValueList.add(text);
			}

			return headersValueList;
	 }
	 public ResultsPage doSearch(String searchKey) {
			System.out.println("Search Key: " + searchKey);
			ele_util.doSendKeys(searchTextField, searchKey, AppConstants.SHORT_TIME_OUT);
			ele_util.doClick(searchIcon);
			return new ResultsPage(driver);
	 }
	 
	 public void selectProduct(String productName) {
			System.out.println("You have selected the product: "+productName);
			ele_util.doClick(By.linkText(productName));
			new ProductInfoPage(driver);
		}
}
