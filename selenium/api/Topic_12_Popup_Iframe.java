package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_12_Popup_Iframe {
  
  private WebDriver driver;
  private WebElement element;
  private Select select;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
	  
  }	

  public void TC_01_Popup() {
	  driver.get("https://www.zingpoll.com/");
	  driver.findElement(By.xpath("//a[@id='Loginform']")).click();
	  sleepInSeconds(3);
	  
	  // Nếu như mà hiển thị thì close popup
	  // Nếu như mà không hiển thị thì qua step tiếp theo
	   
	  // Check popup displayed
	  if(isElementDisplayed("//div[@class='modal-dialog modal_dialog_custom']"))
	  {
		  driver.findElement(By.xpath("//button[@class='close']")).click();
		  sleepInSeconds(3);
	  }
	  
	  // Check popup un-displayed
	  Assert.assertFalse(driver.findElement(By.xpath("//div[@class='modal-dialog modal_dialog_custom']")).isDisplayed());
  }
  
  public void TC_02_Popup() {
	  driver.get("https://bni.vn/");
	  sleepInSeconds(3);
	  
	  // Nếu như mà hiển thị thì close popup
	  // Nếu như mà không hiển thị thì qua step tiếp theo
	   
	  // Check popup displayed
	  if(isElementDisplayed("//div[contains(@class,'sgpb-slideInUp')]"))
	  {
		  driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
		  sleepInSeconds(3);
	  }
  }
  
  @Test
  public void TC_03_Popup() {
	  
	  driver.get("https://blog.testproject.io/");
	   
	  // Case 1 - Nếu như mà hiển thị thì close popup
	  // Case 2 - Nếu như mà không hiển thị thì qua step tiếp theo
	   
	  sleepInSeconds(10);
	  
	  // Check popup displayed
	  if(isElementDisplayed("//div[@class='mailch-wrap']"))
	  {
		  System.out.println("Go to if statement");
		  driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
		  sleepInSeconds(3);
	  }
	  
	  // Check popup un-displayed
	  Assert.assertFalse(driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed());
	  
	  //driver.findElement(By.xpath("//form[@class='search-form']//input[@class='search-field']")).sendKeys("Selenium");
	  //sleepInSeconds(3);
	  //driver.findElement(By.xpath("//span[@class='glass']")).click();
	  
	  //String pageTitle = driver.findElement(By.xpath("//h2[@class='page-title']//span")).getText();
	  //System.out.println(pageTitle);
  }
  
  public void TC_04_Iframe() {
	 driver.get("https://kyna.vn/");
	 
	 // Facebook IFrame
	 driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage ']//iframe")));
 
	 // Thao tác lên những element trang Facebook này
	 String kynaTextTitle = driver.findElement(By.xpath("//a[@title='Kyna.vn']")).getText();
	 System.out.println(kynaTextTitle);
	 
	 // Get likes number
	 String likeNumber = driver.findElement(By.xpath("//a[@title='Kyna.vn']//parent::div//following-sibling::div")).getText();
	 System.out.println(likeNumber);
	 
	 // Main Page
	 driver.switchTo().defaultContent();
	 
	 // Thao tác với WebChat Iframe
	 driver.switchTo().frame("cs_chat_iframe");
	 
	 driver.findElement(By.xpath("//div[contains(@class,'border_overlay')]")).click();
	 sleepInSeconds(2);
	 
	 driver.findElement(By.xpath("//input[contains(@class,'input_name')]")).sendKeys("Selenium");
	 driver.findElement(By.xpath("//input[contains(@class,'input_phone')]")).sendKeys("0987654321");
	 
	 select = new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
	 select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
	 
	 driver.findElement(By.xpath("//textarea[contains(@class,'input_textarea')]")).sendKeys("Selenium");
	 
	 driver.findElement(By.xpath("//input[contains(@class,'submit')]")).click();
	 sleepInSeconds(2);
	 
	 // Main Page
	 driver.switchTo().defaultContent();
	 driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Java");
	 sleepInSeconds(2);
	 
	 driver.findElement(By.xpath("//button[@class='search-button']")).click();
	 sleepInSeconds(5);
  }
  
  @Test
  public void TC_03_() {
	 
  }
  
  public boolean isElementDisplayed(String xpathLocator)
  {
	  try
	  {
		  // 1 - Element hiển thị + có trong DOM
		  // 2 - Element không hiển thị + có trong DOM
		  // 3 - Element không hiển thị + không có trong DOM
		  WebElement element = driver.findElement(By.xpath(xpathLocator));
		  return element.isDisplayed();
	  }
	  catch(Exception e)
	  {
		  e.printStackTrace();
		  return false;
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
	  driver.quit();
  }

}
