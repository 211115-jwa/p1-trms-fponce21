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

public class SubmitRequest {
	public static WebDriver driver;
	
	@BeforeAll
	public static void setupDriver() {
		File file = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		driver = new ChromeDriver();
	}
	
	@Given("the user logged in")
	public void the_user_logged_in() {
		driver.get("C:\\Users\\user\\Documents\\Project_1\\p1-trms-fponce21\\trms-front\\HTML\\index.html");
		
		Wait<WebDriver> wait1 = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50));
		wait1.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id("login"), 0));

		WebElement loginLink = driver.findElement(By.id("login"));
		loginLink.click();

		WebElement userIn = driver.findElement(By.id("username"));
		WebElement passIn = driver.findElement(By.id("password"));
		userIn.sendKeys("sell");
		passIn.sendKeys("pass");
		WebElement loginBtn = driver.findElement(By.id("loginBtn"));
		loginBtn.click();
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofMillis(50));
		wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.id("loginForm"), 1));
		
		driver.get("C:\\Users\\user\\Documents\\Project_1\\p1-trms-fponce21\\trms-front\\HTML\\addreqs.html");

		
	}


	@When("data is entered and")
	public void data_is_entered_and() {
		WebElement eDate = driver.findElement(By.id("eventdate"));
		WebElement eTime = driver.findElement(By.id("etime"));
		WebElement location = driver.findElement(By.id("location"));
		WebElement cost = driver.findElement(By.id("cost"));
		WebElement gformat = driver.findElement(By.id("gformat"));
		WebElement eventtype = driver.findElement(By.id("eventtype"));
		WebElement descbox = driver.findElement(By.id("descbox"));
		
		eDate.sendKeys("01011999");
		eTime.sendKeys("0245PM");
		location.sendKeys("SDSU");
		cost.sendKeys(String.valueOf(100));
		gformat.sendKeys("Pass");
		eventtype.sendKeys("Seminar");
		descbox.sendKeys("Description for Selenium Test");
		
		WebElement login = driver.findElement(By.id("submitbutton"));
		login.click();
	}

	@When("the submit button pressed")
	public void the_submit_button_pressed() {
		WebElement loginBtn = driver.findElement(By.id("submitbutton"));
		loginBtn.click();
	}

	@Then("request has been submitted")
	public void request_has_been_submitted() {
		driver.get("C:\\Users\\user\\Documents\\Project_1\\p1-trms-fponce21\\trms-front\\HTML\\myrequests.html");
	}

	@AfterAll
	public static void closeDriver() {
		driver.switchTo().alert().accept();
		driver.close();
		driver.quit();
	}
}
