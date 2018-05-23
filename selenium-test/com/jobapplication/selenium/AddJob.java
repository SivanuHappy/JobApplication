/*
 * Selenium test cases for adding a new job by an employer
 * Author: Karishma, Shweta
 */
package com.jobapplication.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddJob {
	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir")+"/WebContent/resources/driver/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void testAddJobPageDisplayed() throws InterruptedException {
		/*
		 * This method checks whether add job page is displayed
		 */
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James76");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"content\"]/a")).click();
		element = driver.findElement(By.xpath("/html/body/input"));
		Thread.sleep(300);
		String addJob = element.getAttribute("value");
		assertEquals("Add Jobs", addJob);	
	}
	
	@Test
	public void testCheckMandatoryFields() throws InterruptedException {
		/*
		 * This method checks for mandatory field
		 */
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James76");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"content\"]/a")).click();
		driver.findElement(By.xpath("/html/body/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"job\"]/table/tbody/tr[8]/td[2]/input")).click(); 
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//*[@id=\"title.errors\"]"));
		String errmsg1 = element.getText();
		assertEquals("Mandatory field", errmsg1);
		element = driver.findElement(By.xpath("//*[@id=\"company.errors\"]"));
		String errmsg2 = element.getText();
		assertEquals("Mandatory field", errmsg2);
		element = driver.findElement(By.xpath("//*[@id=\"skills.errors\"]"));
		String errmsg3 = element.getText();
		assertEquals("Mandatory field", errmsg3);
		element = driver.findElement(By.xpath("//*[@id=\"salary.errors\"]"));
		String errmsg4 = element.getText();
		assertEquals("Salary cannot be 0 or greater than $9,999,999.99", errmsg4);
		element = driver.findElement(By.xpath("//*[@id=\"city.errors\"]"));
		String errmsg5 = element.getText();
		assertEquals("Mandatory field", errmsg5);
		element = driver.findElement(By.xpath("//*[@id=\"state.errors\"]"));
		String errmsg6 = element.getText();
		assertEquals("Mandatory field", errmsg6);
	}
	
	@Test
	public void testMinValueForSalary() throws InterruptedException {
		/*
		 * This method checks proper error message is showed on entering 0.0 (out of range) as salary
		 */
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James76");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"content\"]/a")).click();
		driver.findElement(By.xpath("/html/body/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"title\"]"));
		element.sendKeys("Developer");
		element = driver.findElement(By.xpath("//*[@id=\"company\"]"));
		element.sendKeys("Apple");
		element = driver.findElement(By.xpath("//*[@id=\"skills\"]"));
		element.sendKeys("C,C++");
		element = driver.findElement(By.xpath("//*[@id=\"salary\"]"));
		element.sendKeys("0.0");
		element = driver.findElement(By.xpath("//*[@id=\"city\"]"));
		element.sendKeys("San Jose");
		element = driver.findElement(By.xpath("//*[@id=\"state\"]"));
		element.sendKeys("California");
		driver.findElement(By.xpath("//*[@id=\"job\"]/table/tbody/tr[8]/td[2]/input")).click(); 
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//*[@id=\"salary.errors\"]"));
		String errmsg = element.getText();
		assertEquals("Salary cannot be 0 or greater than $9,999,999.99", errmsg);
	}

	@Test
	public void testMaxValueForSalary() throws InterruptedException {
		/*
		 * This method checks whether proper error message is showed on entering 10000000.00 (out of range) as salary
		 */
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James76");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"content\"]/a")).click();
		driver.findElement(By.xpath("/html/body/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"title\"]"));
		element.sendKeys("Developer");
		element = driver.findElement(By.xpath("//*[@id=\"company\"]"));
		element.sendKeys("Apple");
		element = driver.findElement(By.xpath("//*[@id=\"skills\"]"));
		element.sendKeys("C,C++");
		element = driver.findElement(By.xpath("//*[@id=\"salary\"]"));
		element.sendKeys("10000000.00");
		element = driver.findElement(By.xpath("//*[@id=\"city\"]"));
		element.sendKeys("San Jose");
		element = driver.findElement(By.xpath("//*[@id=\"state\"]"));
		element.sendKeys("California");
		driver.findElement(By.xpath("//*[@id=\"job\"]/table/tbody/tr[8]/td[2]/input")).click(); 
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//*[@id=\"salary.errors\"]"));
		String errmsg = element.getText();
		assertEquals("Salary cannot be 0 or greater than $9,999,999.99", errmsg);
	}

	@Test
	public void testStringValueForSalary() throws InterruptedException {
		/*
		 * This method checks whether proper error message is showed on entering string input as salary
		 */
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James76");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"content\"]/a")).click();
		driver.findElement(By.xpath("/html/body/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"title\"]"));
		element.sendKeys("Developer");
		element = driver.findElement(By.xpath("//*[@id=\"company\"]"));
		element.sendKeys("Apple");
		element = driver.findElement(By.xpath("//*[@id=\"skills\"]"));
		element.sendKeys("C,C++");
		element = driver.findElement(By.xpath("//*[@id=\"salary\"]"));
		element.sendKeys("Good Job");
		element = driver.findElement(By.xpath("//*[@id=\"city\"]"));
		element.sendKeys("San Jose");
		element = driver.findElement(By.xpath("//*[@id=\"state\"]"));
		element.sendKeys("California");
		driver.findElement(By.xpath("//*[@id=\"job\"]/table/tbody/tr[8]/td[2]/input")).click(); 
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//*[@id=\"salary.errors\"]"));
		String errmsg = element.getText();
		assertEquals("Salary cannot be 0 or greater than $9,999,999.99", errmsg);
	}

	@Test
	public void testAddJob() throws InterruptedException {
		 //This method checks whether adding a new job is successful
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James76");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"content\"]/a")).click();
		driver.findElement(By.xpath("/html/body/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"title\"]"));
		element.sendKeys("Senior Developer");
		element = driver.findElement(By.xpath("//*[@id=\"company\"]"));
		element.sendKeys("Google");
		element = driver.findElement(By.xpath("//*[@id=\"skills\"]"));
		element.sendKeys("C,C++");
		element = driver.findElement(By.xpath("//*[@id=\"salary\"]"));
		element.sendKeys("9999999.99");
		element = driver.findElement(By.xpath("//*[@id=\"city\"]"));
		element.sendKeys("San Jose");
		element = driver.findElement(By.xpath("//*[@id=\"state\"]"));
		element.sendKeys("California");
		driver.findElement(By.xpath("//*[@id=\"job\"]/table/tbody/tr[8]/td[2]/input")).click(); 
		Thread.sleep(1000);
		String titleName = driver.getTitle();
		assertEquals("Manage jobs", titleName);
	}
	
	@Test
	public void testSalaryWithNoDecimal() throws InterruptedException {
		//This method checks whether salary value is entered without 2 decimal points
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James76");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"content\"]/a")).click();
		driver.findElement(By.xpath("/html/body/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"title\"]"));
		element.sendKeys("Senior Manager");
		element = driver.findElement(By.xpath("//*[@id=\"company\"]"));
		element.sendKeys("Amazon");
		element = driver.findElement(By.xpath("//*[@id=\"skills\"]"));
		element.sendKeys("C,C++");
		element = driver.findElement(By.xpath("//*[@id=\"salary\"]"));
		element.sendKeys("450000");
		element = driver.findElement(By.xpath("//*[@id=\"city\"]"));
		element.sendKeys("San Jose");
		element = driver.findElement(By.xpath("//*[@id=\"state\"]"));
		element.sendKeys("California");
		driver.findElement(By.xpath("//*[@id=\"job\"]/table/tbody/tr[8]/td[2]/input")).click(); 
		Thread.sleep(1000);
		String titleName = driver.getTitle();
		assertEquals("Manage jobs", titleName);
	}
	
	@Test
	public void testInvalidSalary() throws InterruptedException {
		//This method checks for invalid salary - salary with 3 decimal places
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("James76");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"content\"]/a")).click();
		driver.findElement(By.xpath("/html/body/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"title\"]"));
		element.sendKeys("Senior Developer");
		element = driver.findElement(By.xpath("//*[@id=\"company\"]"));
		element.sendKeys("Google");
		element = driver.findElement(By.xpath("//*[@id=\"skills\"]"));
		element.sendKeys("C,C++");
		element = driver.findElement(By.xpath("//*[@id=\"salary\"]"));
		element.sendKeys("13500.750");
		element = driver.findElement(By.xpath("//*[@id=\"city\"]"));
		element.sendKeys("San Jose");
		element = driver.findElement(By.xpath("//*[@id=\"state\"]"));
		element.sendKeys("California");
		driver.findElement(By.xpath("//*[@id=\"job\"]/table/tbody/tr[8]/td[2]/input")).click(); 
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//*[@id=\"salary.errors\"]"));
		String errmsg = element.getText();
		assertEquals("Salary cannot be 0 or greater than $9,999,999.99", errmsg);
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
}

