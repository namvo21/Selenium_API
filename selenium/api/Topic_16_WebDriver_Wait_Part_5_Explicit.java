package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_WebDriver_Wait_Part_5_Explicit {
  
  private WebDriver driver;
  WebDriverWait explicitWait;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  explicitWait = new WebDriverWait(driver, 10);
 	  driver.manage().window().maximize();
	  
  }	
	
  public void TC_01_Visible() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  // 1- Click vào Start button
	  driver.findElement(By.xpath("//button[text()='Start']")).click();
	  
	  // 2 - Loading icon hiển thị (biến mất sau 5s)
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']//h4")));
	  
	  // 3 - HelloWorld text được hiển thị ~ loading icon biến mất (02)
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//h4")).getText(), "Hello World!");
  }
  
  public void TC_02_Invisible() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  // 1- Click vào Start button
	  driver.findElement(By.xpath("//button[text()='Start']")).click();
	  
	  // 2 - Chờ cho loading icon biến mất
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
	  
	  // 3 - HelloWorld text được hiển thị ~ loading icon biến mất (02)
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//h4")).getText(), "Hello World!");
  }
  
  public void TC_03_Implicit_Ajax_Loading() {
	 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	 driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	 
	 driver.findElement(By.xpath("//td[@title='Tuesday, March 23, 2021']")).click();
	 
	 Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Tuesday, March 23, 2021']")).isDisplayed());
	 
	 Assert.assertEquals(driver.findElement(By.xpath("ctl00_ContentPlaceholder1_Label1")).getText().trim(), "Tuesday, March 23, 2021");
  }
  
  @Test
  public void TC_04_Explicit_Ajax_Loading() {
	 driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	 
	 WebElement selecteddateText = driver.findElement(By.xpath("ctl00_ContentPlaceholder1_Label1"));
	 Assert.assertEquals(selecteddateText.getText().trim(), "No Selected Dates to display.");
	 
	 driver.findElement(By.xpath("//td[@title='Tuesday, March 23, 2021']")).click();
	 
	 // Wait for loading icon invisibile
	 explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]//div[@class='raDiv']")));
	 
	 // Wait for date selected visible
	 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/td[@class='rcSelected' and @title='Tuesday, March 23, 2021']")));
	 
	 Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected' and @title='Tuesday, March 23, 2021']")).isDisplayed());
	 
	 selecteddateText = driver.findElement(By.xpath("ctl00_ContentPlaceholder1_Label1"));
	 Assert.assertEquals(selecteddateText.getText().trim(), "Tuesday, March 23, 2021");
  }
 
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
