/*
 * Selenium test cases to check login into the application
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

public class LoginTest {

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
	public void testValidApplicantLogin() throws InterruptedException{
		//This method checks whether a valid applicant can login successfully
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		String titleName = driver.getTitle();
		assertEquals("Applicant Home page", titleName);
	}

	@Test
	public void testInvalidApplicantLogin() throws InterruptedException{
		//This method checks whether login fails for an invalid applicant
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("1234567");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"user\"]/p"));  
		String errmsg = element.getText();
		assertEquals("Username or Password is wrong!!", errmsg);
	}

	@Test
	public void testValidEmployerLogin() throws InterruptedException{
		//This method checks whether a valid employer can login successfully
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("dora123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		String titleName = driver.getTitle();
		assertEquals("Employer Home page", titleName);
	}

	@Test
	public void testInvalidEmployerLogin() throws InterruptedException{
		//This method checks whether login fails for an invalid employer
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("dora123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("1234567");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"user\"]/p"));  
		String errmsg = element.getText();
		assertEquals("Username or Password is wrong!!", errmsg);
	}

	@Test
	public void testMandatoryFields() throws InterruptedException{
		//This method checks for mandatory fields in login form
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"user\"]/p"));  
		String errmsg = element.getText();
		assertEquals("Username or Password is wrong!!", errmsg);
	}
	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
}
