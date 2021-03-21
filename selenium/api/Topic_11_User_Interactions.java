package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_User_Interactions {
  
  private WebDriver driver;
  Actions actions;
  WebElement element;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  actions = new  Actions(driver);
	  
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
	  
  }	
	
  public void TC_01_Hover() {
	  
	  driver.get("http://www.myntra.com/");
	  
	  element = driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"));
	  
	  actions.moveToElement(element).perform();
	  sleepInSeconds(3);
	  
	  driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//li[@class='breadcrumbs-item']//span[text()='Kids Home Bath']")).isDisplayed());
  }
  
  public void TC_02_Hover() {
	  
	  driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
	  
	  element = driver.findElement(By.xpath("//input[@id='age']"));
	  
	  actions.moveToElement(element).perform();
	  sleepInSeconds(3);
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content' and text()='We ask for your age only for statistical purposes.']")).isDisplayed());
  }
  
  
  public void TC_03_Hover() {
	  
	  driver.get("https://hn.telio.vn/");
	  
	  element = driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//span[text()='Đồ ăn vặt']"));
	  
	  actions.moveToElement(element).perform();
	  sleepInSeconds(3);
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//nav[@class='navigation cdz-fix-left']//a[text()='Bắp rang bơ']")).isDisplayed());
  }
  
  public void TC_04_Click_And_Hold() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  System.out.println("Item number " + allItems.size());
	  
	  actions.clickAndHold(allItems.get(0)).moveToElement(allItems.get(3)).release().perform();
	  sleepInSeconds(3);
	  
	  List<WebElement> selectedItems = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
	  System.out.println("Item number " + selectedItems.size());
	  
	  Assert.assertEquals(selectedItems.size(), 4);
	  
	  for (WebElement item : selectedItems) {
		  	System.out.println(item.getText());
	  }
	  
  }
  
  public void TC_05_Click_And_Hold_Random() {
	  driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
	  
	  List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
	  System.out.println("Item number " + allItems.size());
	  
	  // Nhấn phím xuống
	  actions.keyDown(Keys.CONTROL).perform();
	  
	  actions.click(allItems.get(0));
	  actions.click(allItems.get(2));
	  actions.click(allItems.get(5));
	  actions.click(allItems.get(10));
	  
	  actions.keyUp(Keys.CONTROL).perform();
	  sleepInSeconds(3);
	  
	  List<WebElement> selectedItems = driver.findElements(By.xpath("//ol[@id='selectable']//li[contains(@class,'ui-selected')]"));
	  System.out.println("Item number " + selectedItems.size());
	  
	  Assert.assertEquals(selectedItems.size(), 4);
	  
	  for (WebElement item : selectedItems) {
		  	System.out.println(item.getText());
	  }
  }
  
  public void TC_06_Double_Click() {
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  
	  actions.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	  sleepInSeconds(3);
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
	  
  }
  
  
  public void TC_07_Right_Click() {
	  driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	  
	  actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	  sleepInSeconds(3);
	  
	  actions.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
	  sleepInSeconds(3);
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover')]")).isDisplayed());
	  sleepInSeconds(3);
	  
	  actions.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
	  sleepInSeconds(3);
	  
	  driver.switchTo().alert().accept();
	  sleepInSeconds(3);
  }
  
  @Test
  public void TC_08_Drag_And_Drop() {
	  driver.get("https://demos.telerik.com/kendo-ui/dragdrop/index");
	  
	  WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
	  WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
	  
	  actions.dragAndDrop(sourceCircle, targetCircle).perform();
	  sleepInSeconds(3);
	  Assert.assertEquals(targetCircle.getText(), "You did great!");
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
