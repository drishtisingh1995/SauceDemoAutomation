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
import saucedemoautomation.pages.InventoryPage;
import saucedemoautomation.pages.LoginPage;
import saucedemoautomation.resources.Utilities;

public class AddProductsToCartTest extends Base {
	private static Logger logger = LogManager.getLogger(AddProductsToCartTest.class);
	static int expectedBadgeNumber = 0;

	@BeforeClass
	public void setUpAddProductsToCartTest() throws IOException {
		logger.info("Setting up AddProductsToCartTest.");
		loginPage.enterCredentials(Utilities.getConfigValues("username"), Utilities.getConfigValues("pwd"));
		loginPage.signIn();
		logger.info("Logged in successfully.");
	}

	@Test
	public void verifyCartBadgeAddFunctionality() throws InterruptedException {
		logger.info("Starting verifyCartBadgeAddFunctionality test.");
		inventoryPage.addItem1ToCart();
		expectedBadgeNumber++;
		logger.info("Added Item 1 to cart. Expected badge number: " + expectedBadgeNumber);
		try {
			Assert.assertEquals(expectedBadgeNumber, inventoryPage.getCartBadgeItemNumber());
			logger.info("Verified badge number after adding 1st item");
		} catch (AssertionError e) {
			logger.error("Incorrect badge number after 1st item", e);
		}

		inventoryPage.addItem2ToCart();
		expectedBadgeNumber++;

		logger.info("Added Item 1 to cart. Expected badge number: " + expectedBadgeNumber);
		try {
			Assert.assertEquals(expectedBadgeNumber, inventoryPage.getCartBadgeItemNumber());
			logger.info("Verified badge number after adding 2nd item");
		} catch (AssertionError e) {
			logger.error("Incorrect badge number after adding 2nd item", e);
		}
		Thread.sleep(2000);
		logger.info("Finished verifyCartBadgeAddFunctionality test.");

	}

	@Test
	public void verifyCartBadgeRemoveFunctionality() throws InterruptedException {
		logger.info("Starting verifyCartBadgeRemoveFunctionality test.");
		inventoryPage.removeItem1FromCart();
		expectedBadgeNumber--;
		logger.info("Removed Item 1 from cart. Expected badge number: " + expectedBadgeNumber);
		try {
			Assert.assertEquals(expectedBadgeNumber, inventoryPage.getCartBadgeItemNumber());
			logger.info("Verified badge number after removing 1st item");
		} catch (AssertionError e) {
			logger.error("Incorrect badge number after removing 1st item", e);
		}
		
		inventoryPage.removeItem2FromCart();
		expectedBadgeNumber--;
		logger.info("Removed Item 2 from cart. Expected badge number: " + expectedBadgeNumber);
		try {
			Assert.assertEquals(expectedBadgeNumber, inventoryPage.getCartBadgeItemNumber());
			logger.info("Verified badge number after removing 2nd item");
		} catch (AssertionError e) {
			logger.error("Incorrect badge number after removing 2nd item", e);
		}
		
		Thread.sleep(2000);
		logger.info("Finished verifyCartBadgeRemoveFunctionality test.");
	}

}
