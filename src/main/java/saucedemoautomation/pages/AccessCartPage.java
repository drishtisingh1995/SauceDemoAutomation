package saucedemoautomation.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import saucedemoautomation.base.Base;
import saucedemoautomation.resources.Utilities;

public class AccessCartPage extends Base {

	public WebDriver driver;

	public AccessCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	@FindBy(id = "continue-shopping")
	private WebElement continueShoppingButton;

	@FindBy(xpath = "//div[@data-test='inventory-item-name']")
	private List<WebElement> cartItemsName;

	@FindBy(xpath = "//div[@data-test='inventory-item-price']")
	private List<WebElement> cartItemsPrice;

	public void checkout() {
		checkoutButton.click();
	}

	public void continueShopping() {
		continueShoppingButton.click();
	}

	public List<String> getItemsNameInCart() {
		List<String> itemNames = new ArrayList<>();
		for (WebElement name : cartItemsName) {
			itemNames.add(name.getText());

		}
		System.out.println("Item Names In Cart Are: " + itemNames);
		return itemNames;
	}

	public List<Double> getItemsPriceInCart() {
		List<Double> itemPrices = new ArrayList<>();
		for (WebElement price : cartItemsPrice) {
			itemPrices.add(Utilities.getPriceWithoutDollarSign(price.getText()));

		}
		System.out.println("Item Prices In Cart Are: " + itemPrices);
		return itemPrices;
	}

}
