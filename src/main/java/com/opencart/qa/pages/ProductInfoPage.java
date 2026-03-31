package com.opencart.qa.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private final By header = By.cssSelector("#content h1");
	private final By images = By.cssSelector("ul.thumbnails img");
	private final By quantity = By.name("quantity");
	private final By addToCart = By.id("button-cart");
	private final By metadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private final By priceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	public String getProductHeader() {
		return eleUtil.doElementGetText(header);
	}
	
	public int getProductImageCount() {
		int imageCount= eleUtil.waitForAllElementsVisible(images, AppConstants.MEDIUM_TIME_OUT).size();
		System.out.println("Total number of images for "+getProductHeader()+" is "+ imageCount );
		return imageCount;
	}
	
	
	public Map<String, String> getProductInfoData() {
		//productMap = new LinkedHashMap<String, String>();
		//productMap = new HashMap<String, String>();
		productMap = new TreeMap<String, String>();
		productMap.put("productName", getProductHeader());
		productMap.put("imagesCount", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPricingData();
		System.out.println("Product information is: \n"+productMap);
		return productMap;	
	}
	
//	Brand: Apple
//	Product Code: Product 14
//	Availability: Out Of Stock
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(metadata);
		for (WebElement e: metaDataList) {
			String metaText = e.getText();
			String meta[] = metaText.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
	
	
//	$122.00
//	Ex Tax: $100.00
	private void getProductPricingData() {
		List <WebElement> priceList = eleUtil.getElements(priceData);
		String productPrice = priceList.get(0).getText().trim();
		String productExTaxPrice = priceList.get(1).getText().trim().split(":")[1].trim();
		productMap.put("productPrice", productPrice);
		productMap.put("ExTaxPrice", productExTaxPrice);
	}
}
