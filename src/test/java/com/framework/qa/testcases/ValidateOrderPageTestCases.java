package com.framework.qa.testcases;

import org.testng.annotations.Test;

import com.framework.qa.basetest.BaseTest;
import com.framework.qa.pages.OrderPage;

public class ValidateOrderPageTestCases extends BaseTest {
	@Test(priority = 2)
	public void validateEndToEndBuyOrderFunctionality() {
		test = extent.createTest("Validate Buy Order and Checkout", "End to End Buy Order Functionality");
		OrderPage orderPage = new OrderPage(driver, test);
		orderPage.clickAccountAndValidateLoginPage();
		orderPage.enterLoginDetails();
		orderPage.goToLaptopAndNoteBookAndChooseShowAll();
		orderPage.getItemNameandAddToCart();
		orderPage.clickShoppingCartAndCheckout();
		orderPage.validateDeliveryDetailsAndMethod();
		orderPage.validateOrderedItem();
	}
}
