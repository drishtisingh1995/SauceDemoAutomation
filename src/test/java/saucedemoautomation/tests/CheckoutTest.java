package saucedemoautomation.tests;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import saucedemoautomation.base.Base;
import saucedemoautomation.pages.AccessCartPage;
import saucedemoautomation.pages.CheckoutPage;
import saucedemoautomation.pages.InventoryPage;
import saucedemoautomation.pages.LoginPage;
import saucedemoautomation.resources.TextMessages;
import saucedemoautomation.resources.Utilities;

public class CheckoutTest extends Base {

	private static Logger logger = LogManager.getLogger(CheckoutTest.class);

	@BeforeClass
	public void setUpCheckoutTest() throws IOException {
		logger.info("Setting up CheckoutTest.");
		loginPage.enterCredentials(Utilities.getConfigValues("username"), Utilities.getConfigValues("pwd"));
		loginPage.signIn();
		logger.info("Logged in successfully.");
		inventoryPage.addItem1ToCart();
		inventoryPage.addItem2ToCart();
		logger.info("Added Item 1 & Item 2 to cart.");
		inventoryPage.goToAcessCart();
		logger.info("Navigated to the access cart page.");
		accessCartPage.checkout();
		logger.info("Navigated to checkout information page.");
	}

	@Test(dataProvider = "getCustomerInformation")
	public void verifyValidCheckoutInformation(String firstName, String lastName, String postalCode)
			throws IOException, InterruptedException {
		logger.info("Starting verifyValidCheckoutInformation test.");
		Assert.assertEquals(Utilities.getConfigValues("checkoutInformationUrl"), driver.getCurrentUrl());
		logger.info("Verified URL is correct for checkout information page.");
		checkoutPage.enterInformation(firstName, lastName, postalCode);
		logger.info("Entered customer information: First Name: {}, Last Name: {}, Postal Code: {}", firstName, lastName,
				postalCode);
		checkoutPage.continueCheckout();
		logger.info("Continued to checkout overview.");

		Assert.assertEquals(Utilities.getConfigValues("checkoutOverviewUrl"), driver.getCurrentUrl());
		logger.info("Verified URL is correct for checkout overview page.");
		checkoutPage.getCalculatedTax();
		logger.info("Verified Calculated tax is present.");
		checkoutPage.finishCheckout();
		logger.info("Finished checkout process.");
		Assert.assertEquals(Utilities.getConfigValues("checkoutCompleteUrl"), driver.getCurrentUrl());
		logger.info("Verified URL is correct for order confirmation page.");
		Assert.assertEquals(TextMessages.ORDER_CONFIRMATION_TEXT, checkoutPage.getOrderConfirmationText());
		logger.info("Verified order confirmation text.");
		logger.info("Finished verifyValidCheckoutInformation test.");

	}

	@Test(dataProvider = "getInvalidCustomerInformation")
	public void verifyInvalidCheckoutInformation(String firstName, String lastName, String postalCode)
			throws IOException, InterruptedException {
		logger.info("Starting verifyInvalidCheckoutInformation test.");
		Assert.assertEquals(Utilities.getConfigValues("checkoutInformationUrl"), driver.getCurrentUrl());
		logger.info("Verified URL is correct for checkout information page.");
		checkoutPage.enterInformation(firstName, lastName, postalCode);
		logger.info("Entered invalid customer information: First Name: {}, Last Name: {}, Postal Code: {}", firstName,
				lastName, postalCode);
		// Thread.sleep(2000);
		checkoutPage.continueCheckout();
		logger.info("Attempted to continue checkout with invalid information.");
		// Thread.sleep(2000);
		try {
			Assert.assertEquals(TextMessages.EMPTY_LAST_NAME, checkoutPage.getErrorMessage());
			logger.info("Received expected error message for invalid last name.");
		}

		catch (AssertionError e) {
			logger.error("Incorrect error message for invalid last name.", e);

		}
		// Thread.sleep(2000);
		checkoutPage.dismissError();
		logger.info("Dismissed error message.");
		Thread.sleep(2000);
		driver.get(Utilities.getConfigValues("checkoutInformationUrl"));
		logger.info("Refreshed checkout information page.");
		logger.info("Finished verifyInvalidCheckoutInformation test.");

	}

	@DataProvider(name = "getCustomerInformation")
	public Object[][] getValidInformation() {
		return new Object[][] { { "Drishti", "Singh", "75019" } };
	}

	@DataProvider(name = "getInvalidCustomerInformation")
	public Object[][] getInvalidInformation() {
		return new Object[][] { { "Drishti", "", "75019" } };
	}

}
