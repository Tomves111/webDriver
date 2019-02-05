package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


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

	




		
	

	
