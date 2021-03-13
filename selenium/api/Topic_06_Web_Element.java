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
  
  @BeforeClass
  public void beforeClass() {
	  // 1 - Mở browser lên
	  driver = new FirefoxDriver();
	  driver.get("https://demo.nopcommerce.com/");
  }	
	
  @Test
  public void TC_01_() {
	  // Nếu dùng 1 lần thì tương tác trực tiếp
	  driver.findElement(By.xpath(""));
	  
	  // Khai báo biến - dùng được nhiêu lần
	  // WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email']"));
	  // emailTextbox.clear();
	  // emailTextbox.click();
	  // emailTextbox.sendKeys("");
	  
	  element = driver.findElement(By.xpath("//input[@id='small-searchterms']"));
	  
	  // Nhập dữ liệu vào 1 textbox/ textarea/ dropdown (editable)
	  element.sendKeys("Dell XPS 13");
	  sleepInSeconds(2);
	  
	  // Xóa dữ liệu đang có trong 1 textbox/ textarea/ dropdown (editable)
	  element.clear();
	  sleepInSeconds(2);
	  
	  element.sendKeys("Macbook Pro 2015");
	  sleepInSeconds(2);
	  element.sendKeys("Dell XPS 15");
	  sleepInSeconds(2);
	  
	  
	  // Đi tìm element (số ít - 1) - nếu tìm thấy nhiều hơn 1 thì sẽ lấy cái đầu tiên để tương tác
	  // element.findElement(By.xpath(""));
	  
	  // Đi tìm element (số nhiều - >= 1)
	  // element.findElements(By.xpath(""));
	 
	  // Tìm element và trả về duy nhất 1
	  // element.findElements(By.xpath("")).get(0);
	  // element.findElement(By.xpath(""));
	  
	  String searchStorevalue = element.getAttribute("placeholder");
	  System.out.println("Get  attribute of Search Store = " + searchStorevalue);
	  
	  // Color/ Font/ Size (GUI)
	  element = driver.findElement(By.cssSelector(".search-box-button"));
	  String searchBackgroundColor = element.getCssValue("background-color");
	  System.out.println("Get  background of Search Store = " + searchBackgroundColor);
	  
	  String searchButtonTag = element.getTagName();
	  System.out.println("Get  tag name of Search Store = " + searchButtonTag);
	  
	  // Link/ Label/ Span
	  element = driver.findElement(By.xpath("//div[@class='topic-block-title']/h2"));
	  System.out.println("Welcome text = " + element.getText());
	  
	  // Kiểm tra 1 element có hiển thị để tương tác lên được hay không (all elements)
	  Assert.assertTrue(element.isDisplayed());
	   
	  // Kiểm tra 1 element có enable để tương tác hay không (all elements)
	  Assert.assertTrue(element.isEnabled());
	  
	  // Kiểm tra 1 element có được chọn để tương tác hay không: radio/ checkbox
	  // Assert.assertTrue(element.isSelected());
	  
	  // Nếu như element đang tương tác nằm trong form thì có thể dùng hàm này
	  // element.submit();
  }
  
  public void sleepInSeconds(long timeout){
	  try {
		Thread.sleep(timeout*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  } 
  
  @Test
  public void TC_02_() {
	 
  }
  
  @Test
  public void TC_03_() {
	 
  }
 
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
