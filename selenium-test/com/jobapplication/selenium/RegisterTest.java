/*
 * Selenium test cases to check user registration
 * Author: Karishma, Shweta, Anusha, Xue
 */
package com.jobapplication.selenium;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

public class RegisterTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void openBrowser() {
		//System.setProperty("webdriver.gecko.driver", "C:\\GeckoDriver\\geckodriver.exe");
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir")+"/WebContent/resources/driver/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void testRegisterFormDisplays() throws InterruptedException {
		//This method checks register form is displayed properly
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[1]/input")).click();
		Thread.sleep(3000);
		String titleName = driver.getTitle();
		assertEquals("Registration page", titleName);
	}
	
	@Test
	public void testValidApplicantRegistration() throws InterruptedException{
		//This method checks an applicant able to register successfully
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[1]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
		element.sendKeys("Shweta");
		element = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		element.sendKeys("Nalawade");
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("Shweta123");
		Select roleUser = new Select(driver.findElement(By.xpath("//*[@id=\"role\"]")));
		roleUser.selectByVisibleText("Applicant");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		element = driver.findElement(By.xpath("//*[@id=\"confirmpassword\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[7]/td[2]/input")).click();
		Thread.sleep(1000);
		String titleName = driver.getTitle();
		assertEquals("Applicant Home page", titleName);
	}
	
	@Test
	public void testValidEmployerRegistration() throws InterruptedException{
		//This method checks an employer able to register successfully
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[1]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
		element.sendKeys("Karishma");
		element = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		element.sendKeys("Thadishetty");
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("Karish123");
		Select roleUser = new Select(driver.findElement(By.xpath("//*[@id=\"role\"]")));
		roleUser.selectByIndex(1);
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		element = driver.findElement(By.xpath("//*[@id=\"confirmpassword\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[7]/td[2]/input")).click();
		Thread.sleep(1000);
		String titleName = driver.getTitle();
		assertEquals("Employer Home page", titleName);
	}

	@Test
	public void testInvalidRegistration() throws InterruptedException{
		//This method checks whether an invalid user (user name already exists) registration fails
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[1]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
		element.sendKeys("James");
		element = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		element.sendKeys("Bond");
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James123");
		Select roleUser = new Select(driver.findElement(By.xpath("//*[@id=\"role\"]")));
		roleUser.selectByVisibleText("Applicant");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		element = driver.findElement(By.xpath("//*[@id=\"confirmpassword\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[7]/td[2]/input")).click();
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[4]/p"));  
		String errmsg = element.getText();
		assertEquals("Username already exists!!", errmsg);
	}

	@Test
	public void testPasswordMatch() throws InterruptedException{
		//This method checks for password and confirm password match
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[1]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
		element.sendKeys("James");
		element = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		element.sendKeys("Bond");
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James008");
		Select roleUser = new Select(driver.findElement(By.xpath("//*[@id=\"role\"]")));
		roleUser.selectByVisibleText("Applicant");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		element = driver.findElement(By.xpath("//*[@id=\"confirmpassword\"]"));
		element.sendKeys("1234567");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[7]/td[2]/input")).click();
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//*[@id=\"user.errors\"]"));  
		String errmsg = element.getText();
		assertEquals("Password does not match", errmsg);
	}

	@Test
	public void testMandatoryFields() throws InterruptedException{
		//This method checks for mandatory fields in registration form
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[1]/input")).click();
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[7]/td[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"firstName.errors\"]"));  
		String errmsg1 = element.getText();
		assertEquals("Mandatory field", errmsg1);
		element = driver.findElement(By.xpath("//*[@id=\"lastName.errors\"]"));  
		String errmsg2 = element.getText();
		assertEquals("Mandatory field", errmsg2);
		element = driver.findElement(By.xpath("//*[@id=\"username.errors\"]"));  
		String errmsg3 = element.getText();
		assertEquals("Mandatory field", errmsg3);
		element = driver.findElement(By.xpath("//*[@id=\"password.errors\"]"));  
		String errmsg4 = element.getText();
		assertThat(errmsg4, containsString("Length must be minimum 6 characters"));
		element = driver.findElement(By.xpath("//*[@id=\"confirmpassword.errors\"]"));  
		String errmsg5 = element.getText();
		assertEquals("Confirm Password is required", errmsg5);
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}

}