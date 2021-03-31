package api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_WebDriver_Wait_Part_1_Element_Status {
  
  private WebDriver driver;
  private WebDriverWait explicitWait;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  
	  explicitWait = new WebDriverWait(driver, 15);
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
	  
  }	

  public void TC_01_Visible_Pass() {
	  driver.get("https://www.facebook.com/");
	  
	  // Email displayed = visible
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
  }
  
  public void TC_02_Visible_Fail() {
	  driver.get("https://www.facebook.com/");
	  
	  // Email displayed = visible
	  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__'")).isDisplayed());
  }
  
  public void TC_03_Invisible_Pass_01() {
	  driver.get("https://www.facebook.com/");
	  
	  // Không xuất hiện ở trên UI - có xuất hiện trong DOM
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
  }
    
  public void TC_04_Invisible_Pass_02() {
	 System.out.println("Start step get = " + getDateTimeNow());
	 driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	 
	 System.out.println("Start step wait = " + getDateTimeNow());
	 // Không xuất hiện ở trên UI - Không xuất hiện trong DOM
	 explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[text()='Invalid email address.']")));
	 System.out.println("End step wait = " + getDateTimeNow());
  }
  
  public void TC_05_Invisible_Fail() {
	 driver.get("https://www.facebook.com/");
	 
	 // Có xuất hiện ở trên UI -> Wait invisible -> Fail
	 explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email']")));
  }
  
  
  public void TC_06_Presence() {
	 
	  driver.get("https://www.facebook.com/");
	  // Có xuất hiện ở trên UI nhưng phải có trong DOM
	  explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
	  
	  //không xuất hiện ở trên UI nhưng phải có trong DOM
	  explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']"))); 
	  
	  // Nếu như element không có trong DOM -> Failed
	  explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='address']")));
  }
  
  @Test
  public void TC_07_Staleness() {
	  driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	  
	  driver.findElement(By.id("SubmitCreate ")).click();
	  
	  WebElement errorMesssage = driver.findElement(By.xpath("//li[text()='Invalid email address.']"));
	  
	  driver.navigate().refresh();
	  
	  //không xuất hiện ở trên UI + không có trong DOM
	  explicitWait.until(ExpectedConditions.stalenessOf(errorMesssage));
  }
  
  public String getDateTimeNow() {
	  SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	  Date date = new Date(System.currentTimeMillis());
	  return formater.format(date);
  }
 
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
