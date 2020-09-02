package com.framework.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.framework.qa.basepage.BasePage;
import com.framework.qa.repo.Locators;

public class SearchPage extends BasePage {
	OrderPage orderPage;
	@FindBy(name = Locators.SEARCH_INPUT)
	public WebElement searchInput;
	@FindBy(xpath = Locators.SEARCH_ICON)
	public WebElement searchIcon;
	@FindBy(xpath = Locators.SEARCHED_ITEM)
	public WebElement searchItem;
	@FindBy(xpath = Locators.ITEM_NAME)
	public WebElement itemName;

	public SearchPage(WebDriver driver, ExtentTest test) {
		super(driver, test);
		PageFactory.initElements(driver, this);
	}

	public void searchandValidateTheProductDetails() {
		scrollDownPage();
		String productName = getAttribute(itemName, "title");
		isDisplayedThenEnterText(searchInput, "Item is entered", productName);
		Click(searchIcon, "Search Product");
		verifyPageTitle("Search - " + productName);
		String searchedText = getText(searchItem);
		if (searchedText.equals(productName)) {
			logPass("expected Text" + searchedText + "is Displayed");
		} else {
			logPass("expected Text is not Displayed " + searchedText);
		}
	}

}
