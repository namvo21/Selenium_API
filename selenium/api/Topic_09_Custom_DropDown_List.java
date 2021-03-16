package api;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_09_Custom_DropDown_List {
  
  private WebDriver driver;
  private WebDriverWait explicitWait;
  
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver(); 
	  explicitWait = new WebDriverWait(driver, 15);
	  
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
	  
  }	
	
  @Test
  public void TC_01_JQuery() {
	  driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
	  
	  selectItemInDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']"));
	  
	  selectItemInDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']"));
	  
	  selectItemInDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "3");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='3']"));
	  
	  selectItemInDropdownList("//span[@id='number-button']", "//ul[@id='number-menu']//div", "16");
	  Assert.assertTrue(isElementDisplayed("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='16']"));
  }
  
  @Test
  public void TC_02_() {
	 
  }
  
  @Test
  public void TC_03_() {
	//ul[@id='number-menu']//div
  }
 
  public void selectItemInDropdownList(String parentXpath, String childXpath, String expectedItem) {
	  // Click vào 1 thẻ cha để cho nó xổ ra tất cả các item con bên trong
	  driver.findElement(By.xpath(parentXpath)).click();
	  sleepInSeconds(2);
	  
	  // Lấy hết tất cả item gán vào 1 cái List<WebElement> (findElements)
	  List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
	  System.out.println("Item number = " + allItems.size());;
	  
	  // Wait cho tất cả các item được load lên thành công
	  explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
	  
	  for(WebElement webElement : allItems)
	  {
		  // Get text của item 
		  String itemText = webElement.getText();
		  // Kiểm tra có bằng item mình chọn hay không
		  if (itemText.equals(expectedItem))
		  {
			  // Nếu như thỏa mãn thì mình click vào item đó
			  webElement.click();
			  sleepInSeconds(2);
			  break;
		  }
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
  
  public boolean isElementDisplayed(String xpathLocator)
  {
	  if(driver.findElement(By.xpath(xpathLocator)).isDisplayed())
	  {
		  return true;
	  }else
	  {
		  return false;
	  }
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
