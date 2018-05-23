/*
 * Selenium test cases to check profile management as an applicant
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
public class ManageApplicantProfileTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir")+"/WebContent/resources/driver/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void testFirstTimeApplicantUpdate() throws InterruptedException {
		//This method checks whether a first time applicant able to see his/her profile page
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"profilehead\"]/a[1]")).click();
		element = driver.findElement(By.xpath("//*[@id=\"profile\"]/h3"));
		String message = element.getText();
		assertEquals("Provide your details below.", message);
	}

	@Test
	public void testApplicantInfoRetrieved() throws InterruptedException{
		//This method checks whether an applicant information is retrieved properly
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"profilehead\"]/a[1]")).click();
		Thread.sleep(1000);		
		element = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
		String firstname = element.getAttribute("value");
		assertEquals("Xue", firstname);
		element = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		String lastname = element.getAttribute("value");
		assertEquals("Tian", lastname);
	}

	@Test
	public void testInvalidEmailAndPhone() throws InterruptedException{
		//The method checks for invalid email and phone format and checks proper error message is displayed
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"profilehead\"]/a[1]")).click();
		element = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		element.sendKeys("xue123@gmail");
		element = driver.findElement(By.xpath("//*[@id=\"phone\"]"));
		element.sendKeys("123456");
		driver.findElement(By.xpath("//*[@id=\"applicant\"]/table/tbody/tr[1]/td[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"email.errors\"]"));
		String email = element.getText();
		assertEquals("Invalid email address", email);
		element = driver.findElement(By.xpath("//*[@id=\"phone.errors\"]"));
		String phone = element.getText();
		assertEquals("Invalid phone number", phone);
	}


	@Test
	public void testMandatoryEmail() throws InterruptedException{
		//The method checks for mandatory fields and checks proper error message is displayed
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"profilehead\"]/a[1]")).click();
		element = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		element.clear();
		element.sendKeys("");
		element = driver.findElement(By.xpath("//*[@id=\"phone\"]"));
		element.clear();
		element.sendKeys("1234567890");
		driver.findElement(By.xpath("//*[@id=\"applicant\"]/table/tbody/tr[1]/td[2]/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"email.errors\"]"));
		String email = element.getText();
		assertEquals("Invalid email address", email);
	}	

	@Test
	public void testMandatoryPhone() throws InterruptedException{
		//The method checks for mandatory phone field and checks proper error message is displayed
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"profilehead\"]/a[1]")).click();
		element = driver.findElement(By.xpath("//*[@id=\"email\"]"));
		element.sendKeys("xue123@gmail.com");
		element = driver.findElement(By.xpath("//*[@id=\"phone\"]"));
		element.clear();
		element.sendKeys("");
		driver.findElement(By.xpath("//*[@id=\"applicant\"]/table/tbody/tr[1]/td[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"phone.errors\"]"));
		String phone = element.getText();
		assertEquals("Invalid phone number", phone);
	}

	@Test
	public void testValidApplicantUpdate() throws InterruptedException{
		//The method checks whether an applicant information is updated successfully
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"profilehead\"]/a[1]")).click();
		element = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        element.clear();
		element.sendKeys("xue123@gmail.com");
		element = driver.findElement(By.xpath("//*[@id=\"phone\"]"));
		element.clear();
		element.sendKeys("1234567890");
		element = driver.findElement(By.xpath("//*[@id=\"address1\"]"));
		element.clear();
		element.sendKeys("100 Jeffrey Rd");
		element = driver.findElement(By.xpath("//*[@id=\"address2\"]"));
		element.clear();
		element.sendKeys("Apt 2");
		element = driver.findElement(By.xpath("//*[@id=\"city\"]"));
		element.clear();
		element.sendKeys("Normal");
		element = driver.findElement(By.xpath("//*[@id=\"state\"]"));
		element.clear();
		element.sendKeys("IL");
		element = driver.findElement(By.xpath("//*[@id=\"zip\"]"));
		element.clear();
		element.sendKeys("61761");
		element = driver.findElement(By.xpath("//*[@id=\"country\"]"));
		element.clear();
		element.sendKeys("United States");		
		driver.findElement(By.xpath("//*[@id=\"applicant\"]/table/tbody/tr[1]/td[2]/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"applicant\"]/p"));
		String message = element.getText();
		assertEquals("Applicant account updated successfully!!", message);
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
}
