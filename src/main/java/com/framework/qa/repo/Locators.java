package com.framework.qa.repo;

public class Locators {
	public static final String MY_ACCOUNT = "//span[contains(text(),'My Account')]";
	public static final String REGISTER = "//a[contains(text(),'Register')]";
	public static final String REGISTRATION_ACCOUNT_TEXT = "//h1[contains(text(),'Register Account')]";
	public static final String FIRST_NAME = "input-firstname";// id
	public static final String LAST_NAME = "input-lastname";// id
	public static final String EMAIL = "input-email";// id
	public static final String TELEPHONE = "input-telephone";// id
	public static final String PASSWORD = "input-password";// id
	public static final String PASSWORD_CONFIRM = "input-confirm";// id
	public static final String NO_RADIOBTN = "//input[@type='radio' and @name='newsletter'][@checked='checked']";
	public static final String AGREE_CHECKBOX = "//input[@name='agree']";
	public static final String CONTINUE_BUTTON = "//input[@type='submit']";
	public static final String CURRENCY = "//span[contains(text(),'Currency')]";
	public static final String CONTACT = "//span[contains(text(),'123456789')]";
	public static final String WISH_LIST = "wishlist-total";// id
	public static final String SHOPPING_CART = "//span[contains(text(),'Shopping Cart')]";
	public static final String CHECKOUT = "//span[contains(text(),'Checkout')]";
	public static final String SUCCESS_MSG = "//h1[contains(text(),'Your Account Has Been Created!')]";
	public static final String LOGOUT = "//a[contains(text(),'Logout')]";
	public static final String LOGOUT_MSG = "//h1[contains(text(),'Account Logout')]";

	// Order Functionality
	public static final String LOGIN = "//a[contains(text(),'Login')]";
	public static final String LOGIN_EMAIL = "//input[@id='input-email']";
	public static final String LOGIN_PASSWORD = "//input[@id='input-password']";
	public static final String LOGIN_BUTTON = "//input[@type='submit']";
	public static final String LAPTOP_NOTEBOOKTAB = "//a[contains(text(),'Laptops & Notebooks')]";
	public static final String SHOW_ALL = "//a[contains(text(),'Show All Laptops & Notebooks')]";
	public static final String ADD_TO_CART = "//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12'][1]//span[contains(text(),'Add to Cart')]";
	public static final String CALENDER_ICON = "//i[@class='fa fa-calendar']";
	public static final String QUANTITY = "//input[@id='input-quantity']";
	public static final String ADD_CART = "//div[@class='form-group']//button[@id='button-cart']";
	public static final String SUCCESS_SHOPPING = "//div[contains(text(),'Success: You have added')]";
	public static final String ITEM_NAME = "//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12'][1]//a/img";
	public static final String MONTH_PICKER = "div.datepicker-days";// css
	public static final String DELIVERY_DATE = "input-option225";// id

	// checkout Details
	public static final String CHECKOUT_FIRSTNAME = "input-payment-firstname";// id
	public static final String CHECKOUT_LASTNAME = "input-payment-lastname";// id
	public static final String CHECKOUT_ADDRESS = "input-payment-address-1";// id
	public static final String CHECKOUT_CITY = "input-payment-city";// id
	public static final String CHECKOUT_COUNTRY = "input-payment-country";// id
	public static final String CHECKOUT_STATE = "input-payment-zone";// id
	public static final String CONTINUE_ADDRESS_BUTTON = "button-payment-address";// id
	public static final String CONTINUE_SHIPPING_BUTTON = "button-shipping-address";// id
	public static final String EXISTING_PAYMENT = "//input[@type='radio' and @name='payment_address'][@checked='checked']";
	public static final String EXISTING_ADDRESS = "//input[@type='radio' and @name='shipping_address'][@checked='checked']";
	public static final String CONTINUE_METHOD_BUTTON = "button-shipping-method";// id
	public static final String CONTINUE_PAYMENT_BUTTON = "button-payment-method";// id
	public static final String ITEM_ORDERED = "//table[@class='table table-bordered table-hover']//a";
	public static final String CONTINUE_ORDER_BUTTON = "button-confirm";// id
	public static final String ORDER_SUCCESS_MSG = "//h1[contains(text(),'Your order has been placed!')]";

	// Search Product
	public static final String SEARCH_INPUT = "search";// name
	public static final String SEARCH_ICON = "//i[@class='fa fa-search']";
	public static final String SEARCHED_ITEM = "//div[@id='content']//h1";

}
