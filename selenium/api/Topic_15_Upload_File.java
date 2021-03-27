package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Upload_File {
  
  private WebDriver driver;
  private String rootFolderPath = System.getProperty("user.dir");
  String bullSkullName = "BullSkull.png";
  String cactusName = "Cactus.png";
  String decorName = "Decor.png";
  
  String bullSkullPath = rootFolderPath + "/uploadFiles/" + bullSkullName;
  String cactusPath = rootFolderPath + "/uploadFiles/" + cactusName;
  String decorPath = rootFolderPath + "/uploadFiles/" + decorName;
  
 
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDriver/chromedriver");
	  driver = new ChromeDriver();
	  //System.setProperty("webdriver.gecko.driver", rootFolderPath + "/browserDriver/geckodriver");
	  //driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
	  
  }	
	
  public void TC_01_SendKeys_Single() {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  
	  // Tìm 1 element và lưu nó vào biến uploadFile (A)
	  WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
	  uploadFile.sendKeys(bullSkullPath);
	  // Được load lại
	  sleepInSeconds(5);
	  
	  // Dùng cái biến uploadFile này lại lần nữa
	  uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
	  uploadFile.sendKeys(cactusPath);
	  sleepInSeconds(5);
	  
	  uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
	  uploadFile.sendKeys(decorPath);
	  sleepInSeconds(5);	  
  }
  
  @Test
  public void TC_02_SendKeys_Multiples() {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  
	  WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
	  
	  // Load file lên
	  uploadFile.sendKeys(bullSkullPath + "\n" + cactusPath + "\n" + decorPath);
	  sleepInSeconds(5);
	  
	  // Verify 3 files được load lên thành công
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ bullSkullName +"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ cactusName +"']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ decorName +"']")).isDisplayed());
	  
	  // Click vào Start button tại từng file
	  List<WebElement> startButton = driver.findElements(By.cssSelector("table .start"));
	  for (WebElement start : startButton) {
		  	start.click();
		  	sleepInSeconds(1);
	  }
	  
	  // Verify 3 files được upload thành công
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ bullSkullName +"' and @href]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ cactusName +"' and @href]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ decorName +"' and @href]")).isDisplayed());
	  
  }
  
  @Test
  public void TC_03_() {
	 
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
