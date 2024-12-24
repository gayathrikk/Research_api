package com.apollo2.apollo2_Homepage;


import java.io.File;

import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.remote.LocalFileDetector;

import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.remote.RemoteWebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;
public class Uploadfile_search {
	
	private RemoteWebDriver driver;

	private WebDriverWait wait;



	@BeforeTest



	public void setup() throws Exception {

		DesiredCapabilities dc = DesiredCapabilities.chrome();

//		URL url = new URL("http://172.20.23.7:5555/wd/hub");

		URL url = new URL("http://172.20.23.92:4444/");

		driver = new RemoteWebDriver(url, dc);

		wait = new WebDriverWait(driver, 30);



	}



	@Test(priority = 1)

	public void login() throws InterruptedException {

		driver.get("https://apollo2.humanbrain.in/viewer/annotation/portal");

		driver.manage().window().maximize();

		String currentURL = driver.getCurrentUrl();

		System.out.println("Current URL: " + currentURL);

		WebDriverWait wait = new WebDriverWait(driver, 60);

		driver.switchTo().defaultContent(); // Switch back to default content

		WebElement viewerElement = wait

				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Viewer']")));

		if (viewerElement.isEnabled() && viewerElement.isDisplayed()) {

			viewerElement.click();

			System.out.println("Viewer icon is clicked");

		} else {

			System.out.println("Viewer icon is not clickable");

		}



		String parentWindow = driver.getWindowHandle();

		WebElement loginButton = wait

				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Log In ']")));

		if (loginButton != null) {

			loginButton.click();

			System.out.println("Login button clicked successfully.");

		} else {

			System.out.println("Login button is not clicked.");

		}



		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		Set<String> allWindows = driver.getWindowHandles();

		for (String window : allWindows) {

			if (!window.equals(parentWindow)) {

				driver.switchTo().window(window);

				break;

			}

		}

		WebElement emailInput = wait

				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));

		if (emailInput != null && emailInput.isDisplayed()) {

			emailInput.sendKeys("softwareteam45@gmail.com");

			System.out.println("Email was entered successfully.");

		} else {

			System.out.println("Email was not entered.");

		}



		WebElement nextButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));

		if (nextButton1 != null) {

			nextButton1.click();

			System.out.println("Next button 1 is clicked.");

		} else {

			System.out.println("Next button 1 is not clicked.");

		}



		WebElement passwordInput = wait.until(

				ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='Enter your password']")));

		passwordInput.sendKeys("Health#123");

		if (passwordInput.getAttribute("value").equals("Health#123")) {

			System.out.println("Password was entered successfully.");

		} else {

			System.out.println("Password was not entered.");

		}



		WebElement nextButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Next']")));

		if (nextButton2 != null) {

			nextButton2.click();

			System.out.println("Next button 2 is clicked.");

		} else {

			System.out.println("Next button 2 is not clicked.");

		}



		driver.switchTo().window(parentWindow);

		System.out.println("Login successfully");



		System.out.println("************************Login validation done***********************");

		Thread.sleep(2000);

	}



	@Test(priority = 2)

	public void uploadImage() throws InterruptedException {

		Thread.sleep(5000);



		File uploadFile = new File("src/test/resources/automation.png");

		Thread.sleep(3000);



		((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());

		WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));

		fileInput.sendKeys(uploadFile.getAbsolutePath());



//	    get the uploaded file value, so that we can compare

		String uploadedFileName = fileInput.getAttribute("value");



		Thread.sleep(3000);



		// now check the file name matches or not

		Assert.assertNotEquals("", uploadedFileName, "File was not uploaded successfully.");

		System.out.println("File uploaded successfully \n\n");



		Thread.sleep(10000);



	}



	@Test(priority = 3)

	public void testExtract() throws InterruptedException {

		Thread.sleep(5000);



		java.util.List<WebElement> locationElements = driver

				.findElements(By.xpath("//div[@class='research-data']/p[1]"));

		String TextArea = locationElements.get(0).getText();

		Thread.sleep(2000);



		if (TextArea.isEmpty()) {

			Assert.fail("TextArea is empty.");

		} else {

			System.out.println("Content generated successfully \n\n");

		}



	}



	@AfterTest

	public void afterTest() {

		driver.quit();

	}

}
