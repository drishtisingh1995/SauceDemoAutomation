package saucedemoautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import saucedemoautomation.base.Base;
import saucedemoautomation.resources.Utilities;

public class LoginPage extends Base {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	private WebElement usernameInput;

	@FindBy(id = "password")
	private WebElement passwordInput;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	@FindBy(xpath = "//h3")
	private WebElement errorContainer;

	@FindBy(id = "react-burger-menu-btn")
	private WebElement hamburgerButton;

	@FindBy(id = "logout_sidebar_link")
	private WebElement logoutButton;

	public void enterCredentials(String username, String password) {
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
	}

	public void signIn() {
		loginButton.click();
	}

	public String getErrorMsg() {
		return errorContainer.getText();
	}

	public void logOut() throws InterruptedException {
		Utilities.waitOfElementToAppear(hamburgerButton, driver);
		hamburgerButton.click();
		Thread.sleep(2000);
		Utilities.waitOfElementToAppear(logoutButton, driver);
		logoutButton.click();
	}

}
