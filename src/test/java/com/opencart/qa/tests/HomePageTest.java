package com.opencart.qa.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.qa.base.BaseTest;
import com.opencart.qa.pages.HomePage;
import com.opencart.qa.pages.ProductInfoPage;
import com.opencart.qa.pages.ResultsPage;
import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.ElementUtil;

public class HomePageTest extends BaseTest  {
	//HomePage homePage;
	
	@BeforeClass
	public void homePageSetUp() {
		homePage = loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());

	}
	
	
	@Test
	public void isLogoutFieldExistTest() {
		Assert.assertTrue(homePage.islogoutLinkExist());
	}

	@Test
	public void homePageTiteTest() {
		Assert.assertEquals(homePage.getHomePageTitle(), AppConstants.HOME_PAGE_TITLE);
	}

	
	@Test
	public void homePageHeadersTest() {
		List<String> actHeadersList = homePage.getHomePageHeaders();
		Assert.assertEquals(actHeadersList, AppConstants.EXPECTED_HEADERS_LIST);
	}
	
	
	@DataProvider
	public Object getSearchTestData() {
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"airtel",0},
			{"samsung",2},
			{"canon",1}
			
		};
	}
	
	@Test(dataProvider = "getSearchTestData")
	public void searchTest(String searchKey, Integer expReusltsCount) {
		resultsPage = homePage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultsCount(),expReusltsCount);
		//Assert.assertEquals(resultsPage., null);
	}
	
	

}
