package com.opencart.qa.tests;


import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.opencart.qa.base.BaseTest;


public class ProductInfoTest extends BaseTest  {
	
	@BeforeClass
	public void productInfoSetUp(){
		homePage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		
	}
	
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object [][] {
			{"MacBook","MacBook Pro"},
			{"MacBook","MacBook"},
			{"MacBook","MacBook Air"},
			{"iMac","iMac"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@DataProvider
	public Object[][] getProductImagesData() {
		return new Object [][] {
			{"MacBook Pro",4},
			{"MacBook Air",4},
			{"MacBook Air",4},
			{"iMac",3},
			{"Samsung Galaxy Tab 10.1",7}
		};
	}
	@Test(dataProvider = "getProductData")
	public void productHeaderTest(String searchKey, String productName) {
		resultsPage = homePage.doSearch(searchKey);
		productInfoPage =  resultsPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(),productName);
	}
	
	@Test(dataProvider = "getProductImagesData")
	public void productImagesCountTest(String productName, int exp_count) {
		resultsPage = homePage.doSearch(productName);
		productInfoPage =  resultsPage.selectProduct(productName);
		int count = productInfoPage.getProductImageCount();
		System.out.println("The number of images for the product is: "+count);
		Assert.assertEquals(count,exp_count);
	}
	
	@Test
	public void productInfoTest() {
		resultsPage = homePage.doSearch("MacBook");
		productInfoPage =  resultsPage.selectProduct("MacBook Pro");
		Map<String,String> actualProductInfoMap= productInfoPage.getProductInfoData();
		System.out.println("Recieved map is: \n "+actualProductInfoMap);
//		System.out.println(actualProductInfoMap.get("productName"));
//		System.out.println(actualProductInfoMap.get("imagesCount"));
//		System.out.println(actualProductInfoMap.get("Brand"));
//		System.out.println(actualProductInfoMap.get("Availability"));
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualProductInfoMap.get("productName"), "MacBook Pro");
		softAssert.assertEquals(actualProductInfoMap.get("imagesCount"), "4");
		softAssert.assertEquals(actualProductInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(actualProductInfoMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(actualProductInfoMap.get("Availability"),"Out Of Stock");
		softAssert.assertEquals(actualProductInfoMap.get("productPrice"),"$2,000.00");
		softAssert.assertEquals(actualProductInfoMap.get("ExTaxPrice"),"$2,000.00");
		softAssert.assertAll();
	}
}
