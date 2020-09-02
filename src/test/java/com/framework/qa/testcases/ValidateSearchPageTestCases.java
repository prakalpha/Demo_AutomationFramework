package com.framework.qa.testcases;

import org.testng.annotations.Test;

import com.framework.qa.basetest.BaseTest;
import com.framework.qa.pages.OrderPage;
import com.framework.qa.pages.SearchPage;

public class ValidateSearchPageTestCases extends BaseTest {
	@Test(priority = 3)
	public void validateSearchProduct() {
		test = extent.createTest("Validate Searched Product", "Search Product Functionality");
		OrderPage orderPage = new OrderPage(driver, test);
		SearchPage searchPage = new SearchPage(driver, test);
		orderPage.goToLaptopAndNoteBookAndChooseShowAll();
		searchPage.searchandValidateTheProductDetails();
	}

}
