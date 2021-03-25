package api;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_13_Window_Tab {
  
  private WebDriver driver;
  private Alert alert;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
 	  
 	  System.out.println("ID of driver = " + driver.toString());
 	  // GUID : Global Unique Identifier
  }	

  public void TC_01_Window() {
	  driver.get("https://kyna.vn/");
	  
	  // Lấy ra ID của tab mà driver đang active ở đó
	  String parentWindowID = driver.getWindowHandle();
	  System.out.println("Parent ID (Kyna) = " + parentWindowID);
	  
	  driver.findElement(By.xpath("//div[@class='title' and text()='Khóa học combo']")).click();
	  sleepInSeconds(2);
	  
	   // Switch qua trang Khóa học combo
	  switchToWWindowByTitle("Khuyến mãi Nhóm Khóa Học Combo, Khóa Học Online");
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/khuyen-mai/nhom-khoa-hoc");
	  
	  String childID = driver.getWindowHandle();
	  System.out.println("Child ID (Kyna - Khóa học combo) = " + childID);
	  
	  // Switch qua home page
	  switchToWWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
	  sleepInSeconds(2);
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
	  
	  // Sales - CSKH
	  driver.findElement(By.xpath("//div[@class='title' and text()='Sales - CSKH']")).click();
	  sleepInSeconds(2);
	  
	  // Switch qua Sales - CSKH
	  switchToWWindowByTitle("Sales - CSKH");
	  sleepInSeconds(2);
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/danh-sach-khoa-hoc/sales-cskh?utm_source=kyna.site&utm_medium=homepage_newmenu");
	  
	  // Switch qua home page
	  switchToWWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
	  sleepInSeconds(2);
	  
	  // Kỹ năng công việc
	  driver.findElement(By.xpath("//div[@class='title' and text()='Kỹ năng công việc']")).click();
	  sleepInSeconds(2);
		  
	  // Switch qua Kỹ năng công việc 
	  switchToWWindowByTitle("Kỹ năng công việc");
	  sleepInSeconds(2);
	  
	  Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/danh-sach-khoa-hoc/ky-nang-cong-viec?utm_source=kyna.site&utm_medium=homepage_newmenu");
	  
	  closeWindowithoutParent(parentWindowID);
	  Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
	  sleepInSeconds(2);
  }
  
  @Test
  public void TC_02_Window() {
	 driver.get("http://live.demoguru99.com/index.php/");
	 
	 driver.findElement(By.xpath("//a[text()='Mobile']")).click();
	 
	 String parentWindowID = driver.getWindowHandle();
	 
	 driver.findElement(By.xpath("//a[text()='Sony Xperia']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText().equals("The product Sony Xperia has been added to comparison list."));
	 
	 driver.findElement(By.xpath("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText().equals("The product Samsung Galaxy has been added to comparison list."));
	 
	 driver.findElement(By.xpath("//button[@title='Compare']")).click();
	 sleepInSeconds(2);
	 
	 switchToWWindowByTitle("Products Comparison List - Magento Commerce");
	 Assert.assertTrue(driver.findElement(By.xpath("//button[@title='Close Window']")).isDisplayed());
	 
	 driver.findElement(By.xpath("//button[@title='Close Window']")).click();
	 
	 switchToWWindowByTitle("Mobile");
	 
	 closeWindowithoutParent(parentWindowID);
	 
	 driver.findElement(By.xpath("//a[text()='Clear All']")).click();
	 
	 alert = driver.switchTo().alert();
	 alert.accept();
	 
	 Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText().equals("The comparison list was cleared."));
  }
 
  // Only 2 Windows/ Tabs
  public void switchToWWindowByID(String parentID)
  {
	  // Get ra tất cả các ID của Window/ Tab
	  Set<String> allWindows = driver.getWindowHandles();
	  System.out.println(allWindows);
	  
	  // Dùng vòng lặp để duyệt các ID này
	  for (String Id : allWindows) {
		  
		  // Kiểm tra ID mà khác với ID của tham số truyền vào (parent)
		  if (!Id.equals(parentID))
		  {
			  // Sẽ switch qua cái ID đó
			  driver.switchTo().window(Id);
			  break;
		  }
	  }
  }
  
  public void switchToWWindowByTitle(String windowTitle)
  {
	  // Get ra tất cả các ID của Window/ Tab
	  Set<String> allWindows = driver.getWindowHandles();
	  System.out.println(allWindows);
	  
	  // Dùng vòng lặp để duyệt các ID này
	  for (String Id : allWindows) {
		  driver.switchTo().window(Id);
		  String title = driver.getTitle();
		  
		  if (title.equals(windowTitle))
		  {
			  break;
		  }
	  }
 }
 
  public void closeWindowithoutParent(String parentID)
  {
	  // Get ra tất cả các ID của Window/ Tab
	  Set<String> allWindows = driver.getWindowHandles();
	  System.out.println(allWindows);
	  
	  // Dùng vòng lặp để duyệt các ID này
	  for (String Id : allWindows) {
		  if (!Id.equals(parentID))
		  {
			  driver.switchTo().window(Id);
			  driver.close();
		  }
		  driver.switchTo().window(parentID);
	  }
 }
  
  public void sleepInSeconds(long timeout){
	  try {
		Thread.sleep(timeout*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }

}
