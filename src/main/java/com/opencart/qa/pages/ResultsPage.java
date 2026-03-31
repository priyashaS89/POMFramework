package com.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class ResultsPage {
	private ElementUtil ele_util;
	private WebDriver driver;
	
	public ResultsPage(WebDriver driver) {
		this.driver=driver;
		ele_util=new ElementUtil(driver);
	}
	
	//PO
	private final By searchResults = By.cssSelector(("div.product-thumb"));
	
	public int getSearchResultsCount() {
		int resultCount = ele_util.waitForAllElementsVisible(searchResults, AppConstants.MEDIUM_TIME_OUT).size();
		System.out.println("Total number of available product are: "+resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("selected product name is: "+productName);
		ele_util.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
}
