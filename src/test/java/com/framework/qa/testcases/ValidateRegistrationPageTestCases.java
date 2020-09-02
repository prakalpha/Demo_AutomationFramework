package com.framework.qa.testcases;

import org.testng.annotations.Test;

import com.framework.qa.basetest.BaseTest;
import com.framework.qa.pages.RegistrationPage;

public class ValidateRegistrationPageTestCases extends BaseTest {
	@Test(priority = 1)
	public void validateUserRegistrationProcess() {
		test = extent.createTest("Validate User Registration Process", "User Registration Process");
		RegistrationPage registrationPage = new RegistrationPage(driver, test);
		registrationPage.validateHeaderText("Your Store");
		registrationPage.clickAccountAndValidateRegisterFormPage();
		registrationPage.enterRegistrationDetails();
		registrationPage.validateAccountIsCreated();
		registrationPage.clickAccountAndValidateLogout();
	}
}
