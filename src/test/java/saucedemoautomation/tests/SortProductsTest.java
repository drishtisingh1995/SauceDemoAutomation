package saucedemoautomation.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import saucedemoautomation.base.Base;
import saucedemoautomation.pages.LoginPage;
import saucedemoautomation.resources.Utilities;
import saucedemoautomation.pages.InventoryPage;

public class SortProductsTest extends Base {

	private static Logger logger = LogManager.getLogger(SortProductsTest.class);

	@BeforeClass
	public void setUpSortProductTest() throws IOException {
		logger.info("Setting up SortProductsTest.");
		loginPage.enterCredentials(Utilities.getConfigValues("username"), Utilities.getConfigValues("pwd"));
		loginPage.signIn();
		logger.info("Logged in successfully.");
	}

	@Test(dataProvider = "getNameFilter")
	public void sortByName(String nameFilter) throws InterruptedException {
		logger.info("Starting sortByName test with filter: " + nameFilter);
		inventoryPage.selectFilter(nameFilter);
		List<List<String>> sortedProductName = inventoryPage.sortProductNames(nameFilter);
		List<String> expectedNames = sortedProductName.get(0);
		List<String> actualNames = sortedProductName.get(1);
		Assert.assertEquals(expectedNames, actualNames);
		logger.info("Sort by name test completed with filter: " + nameFilter);
	}

	@Test(dataProvider = "getPriceFilter")
	public void sortByPrice(String priceFilter) {
		logger.info("Starting sortByPrice test with filter: " + priceFilter);
		inventoryPage.selectFilter(priceFilter);
		List<List<Double>> sortedProductPrice = inventoryPage.sortProductPrices(priceFilter);
		List<Double> expectedPrice = sortedProductPrice.get(0);
		List<Double> actualPrice = sortedProductPrice.get(1);
		Assert.assertEquals(expectedPrice, actualPrice);
		logger.info("Sort by price test completed with filter: " + priceFilter);

	}

	@DataProvider(name = "getNameFilter")
	public Object[][] getFilterNameData() {
		return new Object[][] { { "za" }, { "az" } };
	}

	@DataProvider(name = "getPriceFilter")
	public Object[][] getFilterPriceData() {
		return new Object[][] { { "lohi" }, { "hilo" } };
	}

}
