package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_WebDriver_Wait_Part_2_FindElement {
  
  private WebDriver driver;
  
 
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDriver/chromedriver");
	  driver = new ChromeDriver();
	  
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
	  
  }	
	
  // implicitWait: timeout mặc định dùng cho việc tìm element (findElement/ findElements)
  
 
  public void TC_01_FindElement() {
	  driver.get("https://automationfc.github.io/multiple-fields/");
	  
	  // 1 - Nếu như nó tìm thấy 1 element thì nó sẽ làm gì? -> 1 matching node
	  // Nó sẽ thao tác với element này
	  driver.findElement(By.xpath("//input[@name='q45_patientName[first]']")).sendKeys("Selenium");
	  sleepInSeconds(2);
	  
	  // 2 - Nếu như nó tìm thấy nhiều hơn 1 element thì nó sẽ làm gì? -> >= 2 matching nodes
	  // Thì nó sẽ thao  tác với element đầu tiền
	  driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	  sleepInSeconds(2);
	    
	  // 3 - Nếu như nó không tìm thấy element thì nó sẽ làm gì? -> 0 matching node
	  // Nó sẽ chờ cho đến khi hết timeout của implicit wait
	  // Trong thời gian chờ - cứ mỗi 0.5s nó tìm lại 1 lần
	  // 3.1 - Nếu như nó tìm thấy element trong thời gian chờ thì nó sẽ pass step này và không cần chờ hết timeout
	  // 3.2 - Nếu như nó không tìm thấy mà hết timeout thì:
	  // - Đánh failed testcase
	  // - throw ra 1 exception: No Such Element
	  driver.findElement(By.xpath("//input[@name='q45_patientName[address]']")).click();;
	  sleepInSeconds(2);
  }
  
  @Test
  public void TC_02_FindElements() {
	  
	  // Dùng trực tiếp (Chỉ dùng 1 lần)
	  driver.get("https://automationfc.github.io/multiple-fields/");
	  
	  // Khai báo biến (Dùng lại nhiều lần)
	  
	  // 1 - Nếu như nó tìm thấy 1 element thì nó sẽ làm gì? -> 1 matching node
	  // Nó sẽ gán element này vào trong cái list (có 1 element)
	  List<WebElement> firstnameTextbox = driver.findElements(By.xpath("//input[@name='q45_patientName[first]']"));
	  System.out.println("Number of element = " + firstnameTextbox.size());
	  firstnameTextbox.get(0).sendKeys("Selenium");
	  sleepInSeconds(2);
	  
	  // 2 - Nếu như nó tìm thấy nhiều hơn 1 element thì nó sẽ làm gì? -> >= n matching nodes
	  // Nó sẽ gán element này vào trong cái list (có n element)
	  List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
	  System.out.println("Number of element = " + checkboxes.size());
	  
	  for (WebElement checkbox : checkboxes) {
		  	checkbox.click();
		  	sleepInSeconds(1);
		  	Assert.assertTrue(checkbox.isSelected());
	  }
	  
	  // 3 - Nếu như nó không tìm thấy element thì nó sẽ làm gì? -> 0 matching node
	  // Nó sẽ chờ cho đến khi hết timeout của implicit wait
	  // Trong thời gian chờ - cứ mỗi 0.5s nó tìm lại 1 lần
	  // 3.1 - Nếu như nó tìm thấy element trong thời gian chờ thì nó sẽ pass step này và không cần chờ hết timeout
	  // 3.2 - Nếu như nó không tìm thấy mà hết timeout thì:
	  // - Không có element nào để gán vào trong list (nên list đó = empty)
	  // - Không đánh fail testcase
	  List<WebElement> addressTextbox = driver.findElements(By.xpath("//input[@name='q45_patientName[address]']"));
	  System.out.println("Number of element = " + addressTextbox.size());
	  
	  addressTextbox.get(0).sendKeys("Selenium");
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
	  driver.quit();
  }

}
