package saucedemoautomation.resources;

import java.io.File;
import java.io.FileInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

public class Utilities {

	public static String getConfigValues(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/java/saucedemoautomation/resources/config.properties");
		prop.load(fis);
		String value = prop.getProperty(key);
		return value;

	}

	public static void waitOfElementToAppear(WebElement element, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static double getPriceWithoutDollarSign(String price) {
		return Double.parseDouble(price.replace("$", ""));
	}

	public static String getScreenshotPath(WebDriver driver, String testName) throws IOException {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "/reports" + testName + ".png";
		FileUtils.copyFile(screenshotFile, new File(destinationPath));
		return destinationPath;
	}

}
