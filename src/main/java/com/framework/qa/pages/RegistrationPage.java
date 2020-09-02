package com.framework.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.framework.qa.basepage.BasePage;
import com.framework.qa.repo.Locators;

public class RegistrationPage extends BasePage {
	@FindBy(xpath = Locators.MY_ACCOUNT)
	public WebElement myAccount;
	@FindBy(xpath = Locators.REGISTER)
	public WebElement register;
	@FindBy(xpath = Locators.REGISTRATION_ACCOUNT_TEXT)
	public WebElement registrationAccount;
	@FindBy(id = Locators.FIRST_NAME)
	public WebElement firstName;
	@FindBy(id = Locators.LAST_NAME)
	public WebElement lastName;
	@FindBy(id = Locators.EMAIL)
	public WebElement email;
	@FindBy(id = Locators.TELEPHONE)
	public WebElement telephone;
	@FindBy(id = Locators.PASSWORD)
	public WebElement password;
	@FindBy(id = Locators.PASSWORD_CONFIRM)
	public WebElement passwordConfirm;
	@FindBy(xpath = Locators.NO_RADIOBTN)
	public WebElement noRadioBtn;
	@FindBy(xpath = Locators.AGREE_CHECKBOX)
	public WebElement agree;
	@FindBy(xpath = Locators.CONTINUE_BUTTON)
	public WebElement continueButton;
	@FindBy(xpath = Locators.CURRENCY)
	public WebElement currency;
	@FindBy(xpath = Locators.CONTACT)
	public WebElement contact;
	@FindBy(id = Locators.WISH_LIST)
	public WebElement wishList;
	@FindBy(xpath = Locators.SHOPPING_CART)
	public WebElement shoppingCart;
	@FindBy(xpath = Locators.CHECKOUT)
	public WebElement checkout;
	@FindBy(xpath = Locators.SUCCESS_MSG)
	public WebElement successMsg;
	@FindBy(xpath = Locators.LOGOUT_MSG)
	public WebElement logoutMsg;
	@FindBy(xpath = Locators.LOGOUT)
	public WebElement logout;

	String passwordfield = fakerAPI().name().name();

	public RegistrationPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(driver, this);

	}

	public void validateHeaderText(String title) {
		verifyPageTitle(title);
		isDisplayed(currency, "Currency is displayed");
		isDisplayed(contact, "Contact is displayed");
		isDisplayed(wishList, "WishList is displayed");
		isDisplayed(shoppingCart, "Shopping Cart is displayed");
		isDisplayed(checkout, "Checkout is displayed");

	}

	public void clickAccountAndValidateRegisterFormPage() {
		isDisplayedThenClick(myAccount, "My Account is Clicked");
		isDisplayedThenClick(register, "Register is Clicked");
		verifyPageTitle("Register Account");
		isDisplayed(registrationAccount, "Register Account Text is displayed");
	}

	public void clickAccountAndValidateLogout() {
		isDisplayedThenClick(myAccount, "My Account is Clicked");
		isDisplayedThenClick(logout, "Logout is Clicked");
		verifyPageTitle("Account Logout");
		isDisplayed(logoutMsg, "Logout Message is displayed");

	}

	public void enterRegistrationDetails() {
		String firstname = fakerAPI().name().firstName();
		String lastname = fakerAPI().name().lastName();
		String eMail = fakerAPI().name().firstName() + "@gmail.com";
		String phone = fakerAPI().phoneNumber().phoneNumber();
		isDisplayedThenEnterText(firstName, "First Name feild is displayed", firstname);
		isDisplayedThenEnterText(lastName, "Last Name feild is displayed", lastname);
		isDisplayedThenEnterText(email, "Email feild is displayed", eMail);
		isDisplayedThenEnterText(telephone, "telephone feild is displayed", phone);
		isDisplayedThenEnterText(password, "password feild is displayed", passwordfield);
		isDisplayedThenEnterText(passwordConfirm, "Confirm password feild is displayed", passwordfield);
		scrollDownPage();
		isDisplayedThenClickRadioButtonOrCheckBox(noRadioBtn, "Radio button is Selected");
		isDisplayedThenClickRadioButtonOrCheckBox(agree, "agree checkBox is clicked");
		isDisplayedThenClick(continueButton, "Continue Button is Clicked");
	}

	public void validateAccountIsCreated() {
		verifyPageTitle("Your Account Has Been Created!");
		isDisplayed(successMsg, "Success Message is displayed");
	}

}
