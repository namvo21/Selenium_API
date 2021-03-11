package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_Web_Browser_API_Exercise {
  // User thao tác với browser
	
  // Khai báo biến (Declare)	
  // Instance đại diện cho 1 thư viện/ Class/ Interface nào đó	
  WebDriver driver; 
  
  @BeforeClass
  public void beforeClass() {
	  // 1 - Mở browser lên
	  driver = new FirefoxDriver();
	  
	  // 2 - User nhập vào Url của application: tiki.vn
	  driver.get("http://live.demoguru99.com");
	  
	  // Dùng để chờ cho element được xuất hiện
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	    
	  // Phóng to trình duyệt
	  driver.manage().window().maximize();
  }	
	
  @Test
  public void TC_01_CheckPageUrl() {
	  
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	  
	  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	  Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	  
  }
  
  @Test
  public void TC_02_CheckPageTile() {
	  
	 driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	 Assert.assertEquals(driver.getTitle(), "Customer Login");
	 
	 driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	 Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	 
  }
  
  @Test
  public void TC_03_Back_Forward() {
	 
	 driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	 driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	 
	 Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");
	 driver.navigate().back();
	 
	 Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	 driver.navigate().forward();
	 
	 Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
  }
  
  @Test
  public void TC_04_CheckPageSource() {
	 
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	  Assert.assertTrue(driver.getPageSource().contains("                Login or Create an Account            "));
	  
	  driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
	  Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
  }
 
  
  @AfterClass
  public void afterClass() {
	  // Đóng trình duyệt trong trường hợp dù có 1 tab hoặc nhiều tab
	  driver.quit();
  }

}
