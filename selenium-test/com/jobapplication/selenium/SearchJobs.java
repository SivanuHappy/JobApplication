/*
 * Selenium test cases for searching for jobs by an applicant
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

public class SearchJobs {
	
	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir")+"/WebContent/resources/driver/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void testSearchJobs() throws InterruptedException{
		//This method checks whether an applicant can search for jobs using job title
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"text1\"]")); 
		element.sendKeys("developer");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"button1\"]")).click();
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[1]/a"));
		String result = element.getText();
		assertEquals("Developer", result);
	}
	
	@Test
	public void testSearchJobsWithoutInput() throws InterruptedException{
		//This method checks whether proper error message is showed for no input search
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"button1\"]")).click();
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("/html/body/p[2]"));   
		Thread.sleep(500);
		String errmsg = element.getText();
		assertEquals("Invalid search string", errmsg);
		Thread.sleep(300);
	}

	@Test
	public void testSearchJobsNotPresent() throws InterruptedException{
		//This method checks whether proper  message is showed for input not in DB
		driver.get("http://localhost:8091/JobApplication/");
		driver.findElement(By.xpath("//*[@id=\"row\"]/div[2]/input")).click();
		element = driver.findElement(By.xpath("//*[@id=\"username\"]"));
		element.sendKeys("xue123");
		element = driver.findElement(By.xpath("//*[@id=\"password\"]"));
		element.sendKeys("123456");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"user\"]/table/tbody/tr[3]/td[2]/input")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("//*[@id=\"text1\"]"));
		element.sendKeys("Data Scientist");
		Thread.sleep(300);
		driver.findElement(By.xpath("//*[@id=\"button1\"]")).click();
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("/html/body/p[2]"));   
		String errmsg = element.getText();
		assertEquals("No jobs found with this search term", errmsg);
		Thread.sleep(300);
	}
	
	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
}
