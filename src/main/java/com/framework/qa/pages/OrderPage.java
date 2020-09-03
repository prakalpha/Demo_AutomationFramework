package com.framework.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.framework.qa.basepage.BasePage;
import com.framework.qa.repo.Locators;
import com.framework.qa.utils.PropUtils;

public class OrderPage extends BasePage {
	@FindBy(xpath = Locators.LOGIN)
	public WebElement login;
	@FindBy(xpath = Locators.MY_ACCOUNT)
	public WebElement myAccount;
	@FindBy(xpath = Locators.LOGIN_EMAIL)
	public WebElement loginEmail;
	@FindBy(xpath = Locators.LOGIN_PASSWORD)
	public WebElement loginPassword;
	@FindBy(xpath = Locators.LOGIN_BUTTON)
	public WebElement loginBtn;
	@FindBy(xpath = Locators.LAPTOP_NOTEBOOKTAB)
	public WebElement laptopAndNotebookTab;
	@FindBy(xpath = Locators.SHOW_ALL)
	public WebElement showAll;
	@FindBy(xpath = Locators.ADD_TO_CART)
	public WebElement addToCart;
	@FindBy(xpath = Locators.ITEM_NAME)
	public WebElement itemName;
	@FindBy(xpath = Locators.CALENDER_ICON)
	public WebElement calenderIcon;
	@FindBy(css = Locators.MONTH_PICKER)
	public WebElement monthPicker;
	@FindBy(id = Locators.DELIVERY_DATE)
	public WebElement deliveryDate;
	@FindBy(xpath = Locators.QUANTITY)
	public WebElement quantity;
	@FindBy(xpath = Locators.SUCCESS_SHOPPING)
	public WebElement successShopping;
	@FindBy(xpath = Locators.ADD_CART)
	public WebElement addCart;
	@FindBy(xpath = Locators.SHOPPING_CART)
	public WebElement shoppingCart;
	@FindBy(xpath = Locators.CHECKOUT)
	public WebElement checkout;
	@FindBy(id = Locators.CHECKOUT_FIRSTNAME)
	public WebElement checkoutFirstname;
	@FindBy(id = Locators.CHECKOUT_LASTNAME)
	public WebElement checkoutLastname;
	@FindBy(id = Locators.CHECKOUT_ADDRESS)
	public WebElement checkoutAddress;
	@FindBy(id = Locators.CHECKOUT_CITY)
	public WebElement checkoutCity;
	@FindBy(id = Locators.CHECKOUT_COUNTRY)
	public WebElement checkoutCountry;
	@FindBy(id = Locators.CHECKOUT_STATE)
	public WebElement checkoutState;
	@FindBy(id = Locators.CONTINUE_ADDRESS_BUTTON)
	public WebElement continueAddressbtn;
	@FindBy(id = Locators.CONTINUE_SHIPPING_BUTTON)
	public WebElement continueShippingbtn;
	@FindBy(xpath = Locators.EXISTING_PAYMENT)
	public WebElement existingPayment;
	@FindBy(xpath = Locators.EXISTING_ADDRESS)
	public WebElement existingAddress;
	@FindBy(id = Locators.CONTINUE_METHOD_BUTTON)
	public WebElement continueMethodbtn;
	@FindBy(xpath = Locators.AGREE_CHECKBOX)
	public WebElement agreeCheckbox;
	@FindBy(id = Locators.CONTINUE_PAYMENT_BUTTON)
	public WebElement continuePaymentbtn;
	@FindBy(xpath = Locators.ITEM_ORDERED)
	public WebElement itemOrdered;
	@FindBy(id = Locators.CONTINUE_ORDER_BUTTON)
	public WebElement continueOrderbtn;
	@FindBy(xpath = Locators.ORDER_SUCCESS_MSG)
	public WebElement orderSuccessMsg;

	public OrderPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
	}

	public void clickAccountAndValidateLoginPage() {
		isDisplayedThenClick(myAccount, "My Account is Clicked");
		isDisplayedThenClick(login, "Register is Clicked");
		verifyPageTitle("Account Login");

	}

	public void enterLoginDetails() {
		isDisplayedThenEnterText(loginEmail, "Email field is displayed", PropUtils.getPropValue(configProp, "email"));
		isDisplayedThenEnterText(loginPassword, "Password feild is displayed",
				PropUtils.getPropValue(configProp, "password"));
		isDisplayedThenClick(loginBtn, "login button");
		verifyPageTitle("My Account");

	}

	public void goToLaptopAndNoteBookAndChooseShowAll() {
		MouseHover(laptopAndNotebookTab);
		isDisplayedThenClick(showAll, "ShowAll Laptop and NoteBook Tab");
		verifyPageTitle("Laptops & Notebooks");
	}

	public String getItemNameandAddToCart() {
		String productName = getAttribute(itemName, "title");
		isDisplayedThenClick(addToCart, "Add To Cart");
		sleep(2);
		verifyPageTitle(productName);
		scrollDownPage();
		sleep(2);
		isDisplayedThenClearText(deliveryDate, "Delivery ate is cleared");
		isDisplayedThenEnterText(deliveryDate, "Delivery Date is entered",
				PropUtils.getPropValue(configProp, "deliveryDate"));
		isDisplayedThenClearText(quantity, "Quantity is cleared");
		isDisplayedThenEnterText(quantity, "Quantity is entered", Integer.toString(3));
		isDisplayedThenClick(addCart, "add Cart");
		isDisplayed(successShopping, "Sucess Message");
		return productName;
	}

	public void clickShoppingCartAndCheckout() {
		isDisplayedThenClick(shoppingCart, "Shopping Cart");
		isDisplayedThenClick(checkout, "Check Out");
		sleep(2);
		verifyPageTitle("Checkout");

	}

	public void enterBillingDetails() {
		String firstname = fakerAPI().name().firstName();
		String lastname = fakerAPI().name().lastName();
		String address = fakerAPI().address().fullAddress();
		isDisplayedThenEnterText(checkoutFirstname, "Firstname is entered", firstname);
		isDisplayedThenEnterText(checkoutLastname, "Lastname is entered", lastname);
		isDisplayedThenEnterText(checkoutAddress, "Address is entered", address);
		isDisplayedThenEnterText(checkoutCity, "City is entered", "Koramangala");
		selectOptionByVisibleText(checkoutCountry, "India");
		selectOptionByVisibleText(checkoutState, "Karnataka");
		isDisplayedThenClick(continueAddressbtn, "Payment Address Continue Button");

	}

	public void validateDeliveryDetailsAndMethod() {
		isDisplayedThenClickRadioButtonOrCheckBox(existingPayment, "Existing Payment Radio button is checked");
		isDisplayedThenClick(continueAddressbtn, "Payment Address Continue Button");
		isDisplayedThenClickRadioButtonOrCheckBox(existingAddress, "Existing Address Radio button is checked");
		isDisplayedThenClick(continueShippingbtn, "Shipping Continue");
		isDisplayedThenClick(continueMethodbtn, "Shipping Method Continue");
		scrollDownPage();
		isDisplayedThenClick(agreeCheckbox, "Agree is Clicked");
		isDisplayedThenClick(continuePaymentbtn, "Payment Method Continue");
	}

	public void validateOrderedItem() {
		isDisplayedThenClick(continueOrderbtn, "Continue Order");
		sleep(2);
		verifyPageTitle("Your order has been placed!");
		isDisplayed(orderSuccessMsg, "Order Message");

	}

}
