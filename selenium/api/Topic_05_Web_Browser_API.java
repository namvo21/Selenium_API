package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_05_Web_Browser_API {
  // User thao tác với browser
	
  // Khai báo biến (Declare)	
  // Instance đại diện cho 1 thư viện/ Class/ Interface nào đó	
  WebDriver driver; 
  
  @BeforeClass
  public void beforeClass() {
	  // 1 - Mở browser lên
	  driver = new FirefoxDriver();
	  
	  // 2 - User nhập vào Url của application: tiki.vn
	  driver.get("https://tiki.vn/");
	  
	  // 7 - User get title của trang web
	  String homePageTitle = driver.getTitle();
	  Assert.assertEquals(homePageTitle, "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
	  
	  Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
	  
	  // 8 - User get ra Url của trang
	  Assert.assertEquals(driver.getCurrentUrl(), "https://tiki.vn/");
	  
	  // 9 - User get source code của trang
	  // Tương đối
	  Assert.assertTrue(driver.getPageSource().contains("phù hợp với nhu cầu tìm kiếm sản phẩm của bạn")); 
	  
	  // Get ra ID của tab/ window hiện tại (*)
	  driver.getWindowHandles();
	  
	  // Dùng để chờ cho element được xuất hiện
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  // Dùng để chờ cho page được load xong để thao tác
	  driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	  // Dùng để chờ cho script được excute xong 
	  driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	    
	  // Phóng to trình duyệt
	  driver.manage().window().maximize();
	  
	  // Test GUI (Font/ size/ color/ ..)
	  driver.manage().window().getSize();
	  
	  // 5 - User sẽ back lại trang trước đó
	  driver.navigate().back();
	  
	  // 3 - User sẽ tải lại trang
	  driver.navigate().refresh();
	  
	  // 6 - User forward tới trang
	  driver.navigate().forward();
	  
	  // Alert/ Window (Tab)/ IFrame (Frame)
	  driver.switchTo().alert();
	  driver.switchTo().window("");
	  driver.switchTo().frame("");
	  
	  // 10 - User đóng application/ browser
	  // Đóng trình duyệt trong trường hợp có 1 tab
	  // Đóng tab đang active trong trường hợp có nhiều tab
	  driver.close();
	  
	  // Đóng trình duyệt trong trường hợp dù có 1 tab hoặc nhiều tab
	  driver.quit();
  }	
	
  @Test
  public void TC_01_() {
	  
  }
  
  @Test
  public void TC_02_() {
	 
  }
  
  @Test
  public void TC_03_() {
	 
  }
 
  
  @AfterClass
  public void afterClass() {
	  
  }

}
