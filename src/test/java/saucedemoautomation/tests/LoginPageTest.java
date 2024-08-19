package saucedemoautomation.tests;

import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import saucedemoautomation.base.Base;
import saucedemoautomation.pages.LoginPage;
import saucedemoautomation.resources.TextMessages;
import saucedemoautomation.resources.Utilities;

public class LoginPageTest extends Base {

	private static Logger logger = LogManager.getLogger(LoginPageTest.class);

	@Test(dataProvider = "getCredentials")
	public void VerifySignInFunctionality(String condition, String username, String password)
			throws IOException, InterruptedException {
		logger.info("Starting VerifySignInFunctionality test with condition: " + condition);
		loginPage.enterCredentials(username, password);
		loginPage.signIn();
		if (condition.equals("valid")) {
			String actualUrl = driver.getCurrentUrl();
			Assert.assertEquals(Utilities.getConfigValues("inventoryUrl"), actualUrl);
			logger.info("Successfully logged in with valid credentials.");
			loginPage.logOut();

		} else if (condition.equals("lockedout")) {
			String actualMessage = loginPage.getErrorMsg();
			Assert.assertEquals(TextMessages.LOCKED_OUT_USER, actualMessage);
			logger.info("Attempted to log in with locked user credentials.");

		} else if (condition.equals("invalidPW")) {
			String actualMessage = loginPage.getErrorMsg();
			Assert.assertEquals(TextMessages.INVALID_PASSWORD, actualMessage);
			logger.info("Attempted to log in with an invalid password.");

		} else if (condition.equals("emptyUM")) {
			String actualMessage = loginPage.getErrorMsg();
			Assert.assertEquals(TextMessages.EMPTY_USERNAME, actualMessage);
			logger.info("Attempted to log in with an empty username.");
		}
	}

	@AfterMethod
	public void resetState() throws IOException {
		logger.info("Resetting state by navigating back to the base URL.");
		driver.get(Utilities.getConfigValues("baseUrl"));
	}

	@DataProvider(name = "getCredentials")
	public Object[][] getData() {
		return new Object[][] { { "valid", "standard_user", "secret_sauce" },
				{ "lockedout", "locked_out_user", "secret_sauce" }, { "invalidPW", "standard_user", "secret" },
				{ "emptyUM", "", "secret_sauce" } };
	}

}
