package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_06_Web_Element {
  // User thao tác với browser
	
  // Khai báo biến (Declare)	
  // Instance đại diện cho 1 thư viện/ Class/ Interface nào đó	
  WebDriver driver; 
  WebElement element;
  By emailTextboxBy = By.id("email");
  By educationTextareaBy = By.id("edu");
  By ageUnder18RadioBy = By.id("under_18");
  By job1DropdownBy = By.id("job1");
  By job2DropdownBy = By.id("job2");
  By developmentCheckboxBy = By.id("development");
  By slider1By = By.id("slider-1");
  By passwordTextboxBy = By.id("password");
  By agedisableRadioBy = By.id("radio-disabled");
  By bioTextareaBy = By.id("bio");
  By job3DropdownBy = By.id("job3");
  By interestsCheckboxBy = By.id("check-disbaled");
  By slider2By = By.id("slider-2");
  By javaCheckboxBy = By.id("java");
  
  
  @BeforeClass
  public void beforeClass() {
	  // 1 - Mở browser lên
	  driver = new FirefoxDriver();
	  driver.get("https://automationfc.github.io/basic-form/index.html");
  }	
	
  @Test
  public void TC_01_Check_Displayed() {
	  
	  // Email textbox
	  if(isElementDisplayed(emailTextboxBy)) {
		 sendKeyToElement(emailTextboxBy, "Automation Testing");
	  }
	  
	  // Education textarea
	  if(isElementDisplayed(educationTextareaBy)) {
		  sendKeyToElement(educationTextareaBy, "Automation Testing");
	  }
	  
	  // Age Under 18 radio button
	  if(isElementDisplayed(ageUnder18RadioBy)) {
		  clickToElement(ageUnder18RadioBy);
	  } 
  }
  
  @Test
  public void TC_02_Check_Enabled() {
	  
	  // Elements are enabled
	  Assert.assertTrue(isElementEnabled(emailTextboxBy));
	  Assert.assertTrue(isElementEnabled(ageUnder18RadioBy));
	  Assert.assertTrue(isElementEnabled(educationTextareaBy));
	  Assert.assertTrue(isElementEnabled(job1DropdownBy));
	  Assert.assertTrue(isElementEnabled(job2DropdownBy));
	  Assert.assertTrue(isElementEnabled(developmentCheckboxBy));
	  Assert.assertTrue(isElementEnabled(slider1By));
	  
	  
	  // Elements are disabled
	  Assert.assertFalse(isElementEnabled(passwordTextboxBy));
	  Assert.assertFalse(isElementEnabled(agedisableRadioBy));
	  Assert.assertFalse(isElementEnabled(bioTextareaBy));
	  Assert.assertFalse(isElementEnabled(job3DropdownBy));
	  Assert.assertFalse(isElementEnabled(interestsCheckboxBy));
	  Assert.assertFalse(isElementEnabled(slider2By));	  
  }
  
  @Test
  public void TC_03_Check_Selected() {
	  // Click on elements to select
	  clickToElement(ageUnder18RadioBy);
	  clickToElement(javaCheckboxBy);
	  
	  // Elements are selected
	  Assert.assertTrue(isElementSelected(ageUnder18RadioBy));
	  Assert.assertTrue(isElementSelected(javaCheckboxBy));
	  
	  // Click on element to deselect
	  clickToElement(javaCheckboxBy);
	  Assert.assertFalse(isElementSelected(javaCheckboxBy)); 
  }
  
  public boolean isElementDisplayed(By by) {
	  WebElement element = driver.findElement(by);
	  
	  if(element.isDisplayed()) {
		  System.out.println("Element ----" + by + "---- is displayed");
		  return true;
	  }
	  else {
		  System.out.println("Element ----" + by + "---- is not displayed");
		  return false;
	  }
	  
  }
  
  public void sendKeyToElement(By by, String value) {
	  WebElement element = driver.findElement(by);
	  element.clear();
	  element.sendKeys(value);
  }
  
  public void clickToElement(By by) {
	  WebElement element = driver.findElement(by);
	  element.click();
  }
  
  public boolean isElementEnabled(By by) {
	  WebElement element = driver.findElement(by);
	  if(element.isEnabled()) {
		  System.out.println("Element ----" + by + "---- is enabled");
		  return true;
	  } else {
		  System.out.println("Element ----" + by + "---- is disabled");
		  return false;
	  }
  }
 
  public boolean isElementSelected(By by) {
	  WebElement element = driver.findElement(by);
	  if(element.isSelected()) {
		  System.out.println("Element ----" + by + "---- is selected");
		  return true;
	  } else {
		  System.out.println("Element ----" + by + "---- is deselected");
		  return false;
	  }
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
