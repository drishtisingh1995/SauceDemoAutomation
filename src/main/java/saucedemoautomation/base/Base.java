package saucedemoautomation.base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import saucedemoautomation.pages.AccessCartPage;
import saucedemoautomation.pages.CheckoutPage;
import saucedemoautomation.pages.InventoryPage;
import saucedemoautomation.pages.LoginPage;
import saucedemoautomation.resources.Utilities;

public class Base {

	public static WebDriver driver;
	protected LoginPage loginPage;
	protected InventoryPage inventoryPage;
	protected AccessCartPage accessCartPage;
	protected CheckoutPage checkoutPage;

	@BeforeClass
	@Parameters({"browserName"})
	public void setUp(@Optional("Chrome") String browserName) throws IOException {
		// String browser = "Chrome";
		switch (browserName) {
		case "Chrome":
			driver = new ChromeDriver();
			break;
		case "Firefox":
			driver = new FirefoxDriver();
			break;
		case "Edge":
			driver = new EdgeDriver();
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(Utilities.getConfigValues("baseUrl"));

		loginPage = new LoginPage(driver);
		inventoryPage = new InventoryPage(driver);
		accessCartPage = new AccessCartPage(driver);
		checkoutPage = new CheckoutPage(driver);
	}
	
//	@BeforeClass
//	public void setUp() throws IOException {
//		String browser = "Chrome";
//		switch (browser) {
//		case "Chrome":
//			driver = new ChromeDriver();
//			break;
//		case "Firefox":
//			driver = new FirefoxDriver();
//			break;
//		case "Edge":
//			driver = new EdgeDriver();
//			break;
//		}
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.get(Utilities.getConfigValues("baseUrl"));
//
//		loginPage = new LoginPage(driver);
//		inventoryPage = new InventoryPage(driver);
//		accessCartPage = new AccessCartPage(driver);
//		checkoutPage = new CheckoutPage(driver);
//	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
