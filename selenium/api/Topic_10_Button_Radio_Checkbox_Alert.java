package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Button_Radio_Checkbox_Alert {
  
  private WebDriver driver;
  public Actions actions;
  private JavascriptExecutor jsExcecutor;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  actions = new Actions(driver);
	  jsExcecutor = (JavascriptExecutor)driver;
	  
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();	  
  }	
	
  @Test
  public void TC_01_Button() throws Exception {
	  driver.get("https://www.bhphotovideo.com/");
	  
	  // Hover to Computer link -> displayed (MAC)
	  actions.moveToElement(driver.findElement(By.xpath("//a[@data-selenium='computersLink']//span"))).perform();
	  Thread.sleep(2000);
	  
	  // Click (Selenium builtin) -> element displayed (visible)
	  // driver.findElement(By.xpath("//div[@class='category-image']//img[@alt='Mac']")).click();
	  jsExcecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[@class='login']")));
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lf-section lf-login-section']")).isDisplayed());
  }
  
  @Test
  public void TC_02_Default_Radio_Button_Checkbox() throws Exception {
	 driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	 By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input");
	 
	 // Click to checkbox (Checked)
	 driver.findElement(dualZoneCheckbox).click();
	 Thread.sleep(3000);
	 
	 // Verify selected success
	 Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
	 
	 // Click to checkbox (Un-Checked)
	 driver.findElement(dualZoneCheckbox).click();
	 Thread.sleep(3000);
	 
	 // Verify un-selected success
	 Assert.assertFalse (driver.findElement(dualZoneCheckbox).isSelected());
  }
  
  @Test
  public void TC_03_Custom_Radio_Button_Checkbox() throws Exception {
	 driver.get("https://material.angular.io/components/radio/examples");
	 
	 By summerRadio = By.xpath("//input[@value='Summer']");
	 // Click to Summer radio button
	 driver.findElement(summerRadio).click();
	 Thread.sleep(3000);
	 
	 Assert.assertTrue(driver.findElement(summerRadio).isSelected());
  }
 
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
