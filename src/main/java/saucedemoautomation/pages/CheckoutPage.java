package saucedemoautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import saucedemoautomation.base.Base;

public class CheckoutPage extends Base {

	public WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "first-name")
	private WebElement firstNameInput;

	@FindBy(id = "last-name")
	private WebElement lastNameInput;

	@FindBy(id = "postal-code")
	private WebElement postalCodeInput;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(id = "cancel")
	private WebElement cancelButton;

	@FindBy(className = "summary_tax_label")
	private WebElement taxLabel;

	@FindBy(id = "finish")
	private WebElement finishButton;

	@FindBy(id = "back-to-products")
	private WebElement backToProductsButton;

	@FindBy(className = "complete-header")
	private WebElement orderConfirmationText;

	@FindBy(xpath = "//h3")
	private WebElement errorContainer;
	
	@FindBy(className="error-button")
	private WebElement errorButton;

	public void enterInformation(String firstName, String lastName, String postalCode) {
		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		postalCodeInput.sendKeys(postalCode);

	}

	public void continueCheckout() {
		continueButton.click();
	}

	public String getCalculatedTax() {
		System.out.println(taxLabel.getText());
		boolean isTrue = false;
		if (taxLabel.isDisplayed()) {
			isTrue = true;
		}
		return (isTrue) ? taxLabel.getText() : "Tax field missing";

	}

	public void finishCheckout() {
		finishButton.click();
	}

	public void goBackHome() {
		backToProductsButton.click();

	}

	public String getOrderConfirmationText() {
		System.out.println(orderConfirmationText.getText());
		return orderConfirmationText.getText();
	}

	public String getErrorMessage() {
		return errorContainer.getText();
	}

	public void clearFirstName() {
		firstNameInput.clear();
	}

	public void clearLastName() {
		lastNameInput.clear();
	}

	public void clearPostalCode() {
		postalCodeInput.clear();
	}
	
	public void dismissError() {
		errorButton.click();
	}

}
