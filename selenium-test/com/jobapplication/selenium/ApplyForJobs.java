/*
 * Selenium test cases for applying for a job by an applicant
 * Author: Karishma, Shweta
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

public class ApplyForJobs {
	
	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void openBrowser() {
		System.setProperty("webdriver.gecko.driver",
				System.getProperty("user.dir")+"/WebContent/resources/driver/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	@Test
	public void testViewDetailsOfJob() throws InterruptedException{
		//This method checks if an applicant is able to see job details
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
		driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[1]/a")).click();
		Thread.sleep(1000);
		element = driver.findElement(By.xpath("//*[@id=\"jobdetails\"]"));
		String message = element.getText();
		assertEquals("Job details", message);
		Thread.sleep(300);
	}
	
	@Test
	public void testApplyForJob() throws InterruptedException{
		//This method checks whether an applicant can successfully apply to a job
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
		driver.findElement(By.xpath("/html/body/table/tbody/tr[4]/td[1]/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"button1\"]")).click();
		Thread.sleep(300);
		element = driver.findElement(By.xpath("/html/body/p"));
		String message = element.getText();
		assertEquals("Successfully applied for this job", message);
		Thread.sleep(300);
	}

	@AfterClass
	public static void closeBrowser() {
		driver.quit();
	}
}
