package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.AfterMethod;

public class NegativeTests {
	WebDriver driver;

	@Parameters({ "browser" })
	@BeforeMethod
	protected void setUp(@Optional("chrome") String browser) {
		// Create driver
		System.out.println("Create driver:" + browser);

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver = new FirefoxDriver();

		default:
			System.out.println("Do not know how to start:" + browser + "starting chrome.");
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}

		sleep(3000);

		driver.manage().window().maximize();

	}

	@Parameters({ "username", "password", "expectedMessage" })
	@Test(priority = 1, groups = { "smokeTests", "negativeTests" })
	public void incorrectUsernameTest() {
		System.out.println("Starting incorrectUsernameTest");

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

	}

	@AfterMethod
	protected void tearDown() {
		sleep(3000);
		System.out.println("Close Driver");
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
