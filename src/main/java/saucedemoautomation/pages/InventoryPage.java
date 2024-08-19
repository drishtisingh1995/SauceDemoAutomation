package saucedemoautomation.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import saucedemoautomation.base.Base;
import saucedemoautomation.resources.Utilities;

public class InventoryPage extends Base {

	public WebDriver driver;

	public InventoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "product_sort_container")
	private WebElement filterContainer;

	@FindBy(className = "active_option")
	private WebElement activeFilter;

	@FindBy(xpath = "//div[contains(@data-test,'inventory-item-name')]")
	private List<WebElement> inventoryItemName;

	@FindBy(xpath = "//div[contains(@data-test,'inventory-item-price')]")
	private List<WebElement> inventoryItemPrice;

	@FindBy(id = "shopping_cart_container")
	private WebElement shoppingCartContainer;

	@FindBy(id = "item_4_title_link")
	private WebElement productNameItemBackpack;

	@FindBy(id = "item_3_title_link")
	private WebElement productNameItemRedShirt;

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addItemBackpack;

	@FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
	private WebElement addItemRedShirt;

	@FindBy(id = "remove-sauce-labs-backpack")
	private WebElement removeItemBackpack;

	@FindBy(id = "remove-test.allthethings()-t-shirt-(red)")
	private WebElement removeItemRedShirt;

	@FindBy(xpath = "//button[contains(@id, 'remove')]/preceding-sibling::div[@class='inventory_item_price']")
	private List<WebElement> expectedAccessCartItemPrice;

	public void selectFilter(String filter) {
		Select select = new Select(filterContainer);
		select.selectByValue(filter);
	}

	public List<List<String>> sortProductNames(String sortNameFilter) {
		List<String> actualProductNames = new ArrayList<>();
		for (WebElement inventoryItem : inventoryItemName) {
			actualProductNames.add(inventoryItem.getText());

		}
		System.out.println("Product Names: " + actualProductNames);

		List<String> expectedProductNames = new ArrayList<>(actualProductNames);
		Collections.sort(expectedProductNames);
		if (sortNameFilter.equals("za")) {
			Collections.reverse(expectedProductNames);
		}
		System.out.println("Expected Product Names: " + expectedProductNames);

		/*
		 * Return a result list which contains both expected product names and actual
		 * product names
		 */
		List<List<String>> result = new ArrayList<>();
		result.add(expectedProductNames);
		result.add(actualProductNames);
		return result;
	}

	public List<List<Double>> sortProductPrices(String sortPriceFilter) {
		List<Double> actualProductPrices = new ArrayList<>();
		for (WebElement itemPrice : inventoryItemPrice) {

			// String currentPrice = itemPrice.getText();
			// String priceWithoutDollarSign = currentPrice.replace("$", "");
			// actualProductPrices.add(Double.parseDouble(priceWithoutDollarSign));
			actualProductPrices.add(Utilities.getPriceWithoutDollarSign(itemPrice.getText()));

		}
		System.out.println("Product Prices: " + actualProductPrices);

		List<Double> expectedProductPrices = new ArrayList<>(actualProductPrices);
		Collections.sort(expectedProductPrices);
		if (sortPriceFilter.equals("hilo")) {
			Collections.reverse(expectedProductPrices);
		}
		System.out.println("Expected Product Prices: " + expectedProductPrices);

		/*
		 * Return a result list which contains both expected product prices and actual
		 * product prices
		 */
		List<List<Double>> result = new ArrayList<>();
		result.add(expectedProductPrices);
		result.add(actualProductPrices);
		return result;
	}

	public void addItem1ToCart() {
		addItemBackpack.click();
	}

	public void addItem2ToCart() {
		addItemRedShirt.click();
	}

	public void removeItem1FromCart() {
		removeItemBackpack.click();
	}

	public void removeItem2FromCart() {
		removeItemRedShirt.click();
	}

	public int getCartBadgeItemNumber() {
		String numberText = shoppingCartContainer.getText();
		return numberText.isEmpty() ? 0 : Integer.parseInt(numberText);
	}

	public List<String> getExpectedItemsName() {
		List<String> expectedItemNames = new ArrayList<>();
		expectedItemNames.add(productNameItemBackpack.getText());
		expectedItemNames.add(productNameItemRedShirt.getText());
		System.out.println("Expected Item Names Are: " + expectedItemNames);
		return expectedItemNames;

	}

	public List<Double> getExpectedItemPrices() {
		List<Double> expectedProductPrices = new ArrayList<>();
		for (WebElement itemPrices : expectedAccessCartItemPrice) {
			expectedProductPrices.add(Utilities.getPriceWithoutDollarSign(itemPrices.getText()));
		}
		System.out.println("Expected Item Prices Are: " + expectedProductPrices);
		return expectedProductPrices;

	}

	public void goToAcessCart() {
		shoppingCartContainer.click();
	}

}
