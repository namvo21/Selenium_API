package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_WebDriver_Wait_Part_4_Implicit {
  
  private WebDriver driver;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
 	  driver.manage().window().maximize();  
  }	
	
  @Test
  public void TC_01_Implicit_Pass() {
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  driver.findElement(By.xpath("//button[text()='Start']")).click();
	  
	  // Cần tới 5s để loading icon biến mất
	  
	  // Đến 5.5s : thấy HelloWorld text xuất hiện
	  // Apply cho những hàm findElement/ findElements
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//h4")).getText(), "Hello World!");
  }
  
  @Test
  public void TC_02_Implicit_Fail() {
	  driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
  	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  driver.findElement(By.xpath("//button[text()='Start']")).click();
	  
	  // Cần tới 5s để loading icon biến mất
	  
	  // Đến 3s : không thấy HelloWorld text xuất hiện mà hết timeout 
	  // -> đánh fail testcase
	  // -> throw exception
	  // Apply cho những hàm findElement/ findElements
	  Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']//h4")).getText(), "Hello World!");
  }
  
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
