package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ExceptionTest {
	WebDriver driver;
	
	@BeforeMethod
	private void setUp() {
		System.out.println("Creating driver");
		// Create driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();

		sleep(3000);
		
		
		driver.manage().window().maximize();
	
	}
	
		@Test
		public void notVisibleTest() {
			//open the page 
			String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
			driver.get(url);
			sleep(2000);
			System.out.println("Page is opened.");
			
			WebElement startButton = driver.findElement(By.xpath("//button"));
			startButton.click();
			
			WebElement finishText = driver.findElement(By.id("finish"));
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(finishText));
			
			Assert.assertTrue(finishText.getText().contentEquals("Hello World!"),"'Hello World! text is not present on the page.'");
			
			}
	
		@AfterMethod
		private void tearDown() {
			sleep(3000);
			//Quit Browser
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

	




		
	

	
