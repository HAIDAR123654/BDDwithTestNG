package org.haidar.cucumberbdd;

import io.cucumber.java.*;
import io.cucumber.java.en.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class StepDefinitions extends TestBase {

	@Before
	public void initializeTest(Scenario scenario) {
		setup();
		ExtentTestFactory.getInstance().setExtentTest(extent.createTest(scenario.getName()));
		test = ExtentTestFactory.getInstance().getExtentTest();
	}

	@After
	public void closeTest(Scenario scenario) {
		
		if(scenario.isFailed()) {
			test.log(Status.FAIL, "Test Case : " + scenario.getName() + " is failed");
	        
	        //add screenshot for failure tests
	        
	        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			Date date = new Date();
			String ActualDate = format.format(date);
			
	        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        String screenshotPath = System.getProperty("user.dir")+
					"/Reports/Screenshots/" +ActualDate+".jpeg";
	        File dest = new File(screenshotPath);
	        try {
				FileUtils.copyFile(src, dest);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        test.addScreenCaptureFromPath(screenshotPath,"Test case failure screenshot");
		}
		
		tearDown();
	}

	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		driver.get("https://www.saucedemo.com/");
		// ExtentCucumberAdapter.addTestStepLog("Navigated to login page:
		// https://www.saucedemo.com/test");
		logStep("Navigated to login page: https://www.saucedemo.com/");
	}

	@When("I enter username {string} and password {string}")
	public void i_enter_username_and_password(String username, String password) {

		CustomWebElement usernameField = new CustomWebElement(driver.findElement(By.id("user-name")), test);
		CustomWebElement passwordField = new CustomWebElement(driver.findElement(By.id("password")), test);

		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
	}

	@Then("I click the login button")
	public void i_click_the_login_button() {
		CustomWebElement loginButton = new CustomWebElement(driver.findElement(By.id("login-button")), test);
		loginButton.click();
		
		try {
			String productPage = driver.findElement(By.xpath("//span[text()='Products']")).getText();
			Assert.assertEquals("Products",productPage);
		}catch(Exception e) {
			Assert.assertFalse(true);	
		}
	}

	@Given("I am on the google page")
	public void i_am_on_the_google_page() {
		driver.get("https://www.google.com/");
		logStep("Navigated to google page: https://www.google.com/");
    }

	@When("I enter username {string}")
	public void i_enter_username(String string) {
		CustomWebElement searchField = new CustomWebElement(driver.findElement(By.name("q")), test);
		searchField.sendKeys(string);
	}

	@Then("I click the enter button from keypad")
	public void i_click_the_login_enter_from_keypad() {
		logStep("clicked on the enter buitton by keyboard");
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER);
		logStep("selenium is shown now");
	}

	@Given("I add few products in cart")
	public void i_add_few_products_in_cart() {

		Actions actions = new Actions(driver);
		logStep("Actions class for add few products method is created");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		logStep("Explicit wait for add few products method is created");

		String x = "//div[@id='inventory_container']/div[@class='inventory_list']/div//a[contains(@id, 'title')]";
		String s = "/parent::div/parent::div//div[@class='pricebar']//button";
		logStep("x and s xpath is created");

		List<WebElement> products = driver.findElements(By.xpath(x));
		for (int i = 1; i <= products.size(); i++) {
			String s1 = "//div[@id='inventory_container']/div[@class='inventory_list']/div[" + i
					+ "]//a[contains(@id, 'title')]";
			WebElement addToCartButton = driver.findElement(By.xpath(s1 + s));
			actions.moveToElement(addToCartButton).build().perform();
			// Wait until the element is clickable after scrolling
			wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
			addToCartButton.click();
			logStep(i + "-->" + "product is added in to the cart");
		}
	}

	@When("I click on cart icon")
	public void i_click_on_cart_icon() {
		Actions actions = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement cart = driver.findElement(By.id("shopping_cart_container"));
		actions.moveToElement(cart).build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(cart));
		cart.click();
		logStep("clicked on the cart icon");
	}

	@Then("I should land to cart details page")
	public void i_should_land_to_cart_details_page() {
		String yourCart = driver.findElement(By.xpath("//span[text()='Your Cart']")).getText();
		Assert.assertEquals("Your Cart1", yourCart);
		logStep("cart page validation is successfull..");
	}
}
