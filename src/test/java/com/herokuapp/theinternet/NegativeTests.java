package com.herokuapp.theinternet;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NegativeTests {

	@Test(priority = 1, groups = { "smokeTests", "negativeTests"})
	public void incorrectUsernameTest() {
		System.out.println("Starting incorrectUsernameTest");
		
		// Create driver
		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();

		sleep(3000);

		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// open the page
		String url = "http://the-internet.herokuapp.com/login";
		driver.get(url);
		sleep(2000);
		System.out.println("Page is opened.");

		// enter incorrect username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("incorrectUsername");

		// enter password
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SuperSecretPassword!");


		// push log in button
		WebElement logInButton = driver.findElement(By.className("radius"));
		logInButton.click();

		// verifications
		WebElement ErrorMessage = driver.findElement(By.id("flash"));
		String expectedErrorMessage = "Your username is invalid!";
		String actualErrorMessage = ErrorMessage.getText();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
				"actualErrorMessage does not contain expectedErrorMessage\nexpectedSuccessMessage: "
						+ expectedErrorMessage + "\nactualErrorMessage: " + actualErrorMessage);
		

	
		sleep(3000);

		// Close browser
		driver.quit();

	}

	/** Static sleep */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
