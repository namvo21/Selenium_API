package api;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_16_WebDriver_Wait_Part_7_Fluent {
  
  private WebDriver driver;
  FluentWait<WebDriver> fluentDriver;
  FluentWait<WebElement> fluentElement;
  WebElement element;
 
  @BeforeClass
  public void beforeClass() {
	  
	  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/browserDriver/geckodriver");
	  driver = new FirefoxDriver();
 	  driver.manage().window().maximize();
  }	
	
  public void TC_01_Fluent_WebDriver() {
	  driver.get("https://www.facebook.com/");
	  
	  fluentDriver = new FluentWait<WebDriver>(driver); 
	  
	  // Tổng thời gian check là 15s
	  fluentDriver.withTimeout(15, TimeUnit.SECONDS).
	  	// Tần số mỗi 1s check 1 lần
	  	pollingEvery(300, TimeUnit.MILLISECONDS).
	  		// Nếu gặp exception là find không thấy element sẽ bỏ qua
	  		ignoring(NoSuchElementException.class);
	  // Function<T, R>
	  // T sẽ là tham số của hàm apply
	  // R sẽ là kiểu trả về của hàm apply
	  WebElement feelingLuckyButton = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>() {
		  public WebElement apply(WebDriver driver) {
			  return driver.findElement(By.xpath("//input[@id='lastname']"));
		  }
	  });
	  feelingLuckyButton.click();
  }
  
  @Test
  public void TC_02_Fluent_WebElement() {
	  driver.get("https://automationfc.github.io/fluent-wait/");
	  
	  element = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	  fluentElement = new FluentWait<WebElement>(element);
	  
	  // Tổng thời gian chờ là 15s
	  fluentElement.withTimeout(15, TimeUnit.SECONDS).
	  	// Tần số mỗi 1s check 1 lần
	  	pollingEvery(2, TimeUnit.SECONDS).
	  		// Nếu gặp exception là find không thấy element sẽ bỏ qua
	  		ignoring(NoSuchElementException.class);
	  	//Kiểm tra điều kiện	
  		boolean status = fluentElement.until(new Function<WebElement, Boolean>() {
	  			public Boolean apply(WebElement element) {
	  				// Kiểm tra điều kiện countdown = 00
	  				boolean flag = element.getText().endsWith("00");
	  				System.out.println("Time = " + element.getText());
	  				// Return giá trị cho function apply
	  				return flag;
	  			}
			});  
  		System.out.println("Status = " + status);
  }
   
  @Test
  public void TC_03_Fluent_WebElement() {
	  driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
	  
	  driver.findElement(By.xpath("//button[text()='Start']")).click();
	  
	  fluentDriver = new FluentWait<WebDriver>(driver);
	  fluentDriver.withTimeout(6, TimeUnit.SECONDS)
			  .pollingEvery(1000, TimeUnit.MILLISECONDS)
			  .ignoring(NoSuchElementException.class);
	  
	  WebElement helloWorldText = (WebElement) fluentDriver.until(new Function<WebDriver, WebElement>() {
		  public WebElement apply(WebDriver driver){
			  return driver.findElement(By.xpath("//div[@id='finish']//h4"));
		  }
	  });
	  
	  Assert.assertEquals(helloWorldText.getText(), "Hello World!");
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
