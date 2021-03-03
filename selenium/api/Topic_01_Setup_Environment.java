package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_01_Setup_Environment {
  
  // Khai báo biến	
  private WebDriver driver;
  
  // Chay đầu tiên 1 lần trước các testcases
  // Pre-condition (Manual)
  @BeforeClass
  public void beforeClass() {
	  // Khởi tạo biến driver
	  driver = new FirefoxDriver();
	  
	  // Wait cho element được hiển thị thao tác
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  
	  // Phóng to trình duyệt
	  driver.manage().window().maximize();
	  
	  // Mở app/ aut ra
	  driver.get("https://www.google.com");
  }	
	
  // Xem đây như là 1 testcases	 
  @Test
  public void TC_01_Check_Google_Title() {
	  // Get ra Title của trang Google
	  String googleTitle = driver.getTitle();
	  System.out.print("Title =" + googleTitle);
	  
	  // Verify title có đúng như mong đợi hay không
	  Assert.assertEquals(googleTitle, "Google");
  }
  
  @Test
  public void TC_02_Check_Google_Url() {
	  // Get ra Url của trang Google
	  String googleUrl = driver.getCurrentUrl();
	  System.out.print("Url =" + googleUrl);
	  
	  // Verify Url có đúng như mong đợi hay không
	  Assert.assertTrue(googleUrl.contains("https://www.google.com/"));
  }
  
  @Test
  public void TC_03_Check_Google_Logo() {
	  // Check Google logo displayed
	  /*// Newest version
	  Assert.assertTrue(driver.findElement(By.cssSelector(".//*[@class='lnXdpd']")).isDisplayed());*/
	  
	  // Older version
	  Assert.assertTrue(driver.findElement(By.cssSelector("#hplogo")).isDisplayed());
  }
 
  // Chạy cuối cùng sau tất cả các testcases
  // Post-condition (Manual)
  @AfterClass
  public void afterClass() {
	  //Tắt browser
	  driver.quit();
  }

}
