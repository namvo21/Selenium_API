package api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_15_Upload_File {
  
  private WebDriver driver;
  private String rootFolderPath = System.getProperty("user.dir");
  private WebDriverWait explicitWait;
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
	  explicitWait = new WebDriverWait(driver, 15);
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
  public void TC_03_Robot() throws AWTException {
	  
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");  
	  
	  // Specify the file location with extension
	  StringSelection select = new StringSelection(bullSkullPath);
	  
	  // Copy to clipboard
	  Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
	  
	  if(driver.toString().contains("chrome"))
	  {
		  WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		  uploadFile.click();
		  sleepInSeconds(2);
	  }
	  else
	  {
		  System.out.println("Go to Firefox");
		  WebElement uploadFile = driver.findElement(By.cssSelector(".fileinput-button"));
		  uploadFile.click();
		  sleepInSeconds(2);
	  }
	  
	  Robot robot = new Robot();
	  sleepInSeconds(2);
	  
	  // Nhấn Phím Enter -> focus vào textbox
	  robot.keyPress(KeyEvent.VK_ENTER);
	  robot.keyRelease(KeyEvent.VK_ENTER);
	  
	  // Nhấn xuống Ctrl-V
	  robot.keyPress(KeyEvent.VK_CONTROL);
	  robot.keyPress(KeyEvent.VK_V);
	  
	  // Nhả Ctrl-V
	  robot.keyRelease(KeyEvent.VK_CONTROL);
	  robot.keyRelease(KeyEvent.VK_V);
	  sleepInSeconds(2);
	  
	  // Nhấn Enter
	  robot.keyPress(KeyEvent.VK_ENTER);
	  robot.keyRelease(KeyEvent.VK_ENTER);
	  
	  sleepInSeconds(3);
  }

  @Test
  public void TC_04_Upload_Files() {
	  driver.get("https://gofile.io/?t=uploadFiles");  
	  
	  WebElement uploadFile = driver.findElement(By.xpath("//button[@id='dropZoneBtnSelect']"));
	 
	  // Load file lên
	  uploadFile.sendKeys(bullSkullPath);
	  sleepInSeconds(5);
	  
  }
  
  public void sleepInSeconds(long timeout){
	  try {
		Thread.sleep(timeout*1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
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
 
  
  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }

}
