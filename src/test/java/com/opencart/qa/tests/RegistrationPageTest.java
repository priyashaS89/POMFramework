package com.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.opencart.qa.base.BaseTest;
import com.opencart.qa.utils.AppConstants;
import com.opencart.qa.utils.CSVUtil;
import com.opencart.qa.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest  {
	
	@BeforeClass
	public void registrationSetUp() {
		registrationPage = loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object userRegistrationTestData() {
		return new Object[][]{
			{"ichel", "shol", "789456123","gameOn","yes"},
			{"cheeko", "gana", "456879123","gameOn","yes"},
			{"kani", "manta", "789456123","gameOn","no"}
			
		};
	}
	
	
	@DataProvider
	public Object [][] getUserRegExcelTestData(){
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@DataProvider
	public Object [][] getUserRegCSVTestData(){
		return CSVUtil.csvData("OpenCartCSVData");
	}
			
	@Test(dataProvider = "getUserRegCSVTestData")
	public void userRegistrationTest(String firstname,String lastName, String number, String password, String subscribe) {
		Assert.assertTrue(registrationPage.userRegistration(firstname,lastName,number,password,subscribe));
	}
}
