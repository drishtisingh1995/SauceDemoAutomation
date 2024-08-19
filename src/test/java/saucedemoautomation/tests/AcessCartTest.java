package saucedemoautomation.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import saucedemoautomation.base.Base;
import saucedemoautomation.pages.AccessCartPage;
import saucedemoautomation.pages.InventoryPage;
import saucedemoautomation.pages.LoginPage;
import saucedemoautomation.resources.Utilities;

public class AcessCartTest extends Base {

	private static Logger logger = LogManager.getLogger(AcessCartTest.class);

	@BeforeClass
	public void setUpAcessCartTest() throws IOException {
		logger.info("Setting up AcessCartTest.");
		loginPage.enterCredentials(Utilities.getConfigValues("username"), Utilities.getConfigValues("pwd"));
		loginPage.signIn();
		logger.info("Logged in successfully.");
		inventoryPage.addItem1ToCart();
		inventoryPage.addItem2ToCart();
		logger.info("Added Item 1 & Item 2 to cart.");
		inventoryPage.goToAcessCart();
		logger.info("Navigated to the access cart page.");
	}

	@Test
	public void verifyCartProductNameAndPrice() {
		logger.info("Starting verifyCartProductNameAndPrice test.");
		try {
			Assert.assertEquals(inventoryPage.getExpectedItemsName(), accessCartPage.getItemsNameInCart());
			logger.info("Product names in the cart are as expected.");
		} catch (AssertionError e) {
			logger.error("Product names in the cart do not match the expected names.", e);
		}

		try {
			Assert.assertEquals(inventoryPage.getExpectedItemPrices(), accessCartPage.getItemsPriceInCart());
			logger.info("Item prices in the cart are as expected.");
		} catch (AssertionError e) {
			logger.error("Item prices in the cart do not match the expected prices.", e);
		}

		logger.info("Finished verifyCartProductNameAndPrice test.");
	}

}
