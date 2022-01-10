package com.revature.features;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GetRequestStepImpl {
public static WebDriver driver;
	
	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
	}
	
	@Given("user is on the page")
	public void the_user_is_on_the_page() {
		driver.get("C:\\Users\\user\\Documents\\Project_1\\p1-trms-fponce21\\trms-front\\HTML\\myrequests.html");

		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50));
		wait1.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("allRequests"), 0));

	}
	

	@When("employee id is entered")
	public void the_employee_id_is_entered() {
		WebElement empInput = driver.findElement(By.id("dataInput"));
		empInput.sendKeys( String.valueOf(10));
		
		WebElement reqBtn = driver.findElement(By.id("reqbutton"));
		reqBtn.click();
		}

	@Then("table shows the requests for the employee")
	public void the_table_shows_the_requests_for_the_employee() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.tagName("tr"), 1));
	}
	
	@AfterAll
	public static void closeDriver() {
		driver.switchTo().alert().accept();
		driver.close();
		driver.quit();
	}
	
}
