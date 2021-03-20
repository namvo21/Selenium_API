package api;

import java.util.concurrent.TimeUnit;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Button_Radio_Checkbox_Alert {
  
  private WebDriver driver;
  public Actions actions;
  private JavascriptExecutor jsExcecutor;
  Alert alert;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  actions = new Actions(driver);
	  jsExcecutor = (JavascriptExecutor)driver;
	  
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();	  
  }	
	
  public void TC_01_Button() throws Exception {
	  driver.get("https://www.bhphotovideo.com/");
	  
	  // Hover to Computer link -> displayed (MAC)
	  actions.moveToElement(driver.findElement(By.xpath("//a[@data-selenium='computersLink']//span"))).perform();
	  Thread.sleep(2000);
	  
	  // Click (Selenium builtin) -> element displayed (visible)
	  // driver.findElement(By.xpath("//div[@class='category-image']//img[@alt='Mac']")).click();
	  jsExcecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//button[@class='login']")));
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lf-section lf-login-section']")).isDisplayed());
  }
  
  @Test
  public void TC_02_Default_Radio_Button_Checkbox() throws Exception {
	 
	 driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
	 By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input");
	 
	 // Click to checkbox (Checked)
	 driver.findElement(dualZoneCheckbox).click();
	 Thread.sleep(3000);
	 
	 // Verify selected success
	 Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
	 
	 // Click to checkbox (Un-Checked)
	 driver.findElement(dualZoneCheckbox).click();
	 Thread.sleep(3000);
	 
	 // Verify un-selected success
	 Assert.assertFalse (driver.findElement(dualZoneCheckbox).isSelected());
	 
	 driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
	 By carEngineRadio = By.xpath("//input[@id='engine3']//following-sibling::label");
	 driver.findElement(carEngineRadio).click();
	 
	 boolean status = driver.findElement(carEngineRadio).isDisplayed();
	 
	 if(status == false) {
		 driver.findElement(carEngineRadio).click();
	 }
	 
	 
  }
  
  @Test
  public void TC_03_Custom_Radio_Button_Checkbox() throws Exception {
	 driver.get("https://material.angular.io/components/radio/examples");
	 
	 // Không click được nhưng verify được (isSelected)
	 By summerInputRadio = By.xpath("//input[@value='Summer']");
	 
	 // Click được nhưng verify không được
	 // By summerDivRadio = By.xpath("//input[@value='Summer']/preceding-sibling::span[contains(@class,'outer-circle')]");
	 
	 // Click to Summer radio button
	 ClickByJS(summerInputRadio);
	 Thread.sleep(3000);
	 
	 Assert.assertTrue(driver.findElement(summerInputRadio).isSelected());
  }
  
  public void TC_04_Create_Alert() throws Exception {
	 // driver.get("https://automationfc.github.io/basic-form/index.html");
	  driver.get("https://www.google.com/#spf=1616116513290");
	 
	 // Create new Alert accept
	 jsExcecutor.executeScript("alert('Create a new accept alert');");
	 Thread.sleep(3000);
	 
	 // Switch qua Alert
	 alert = driver.switchTo().alert();
	 Assert.assertEquals(alert.getText(), "Create a new accept alert");
	 
	 // Accept (3)
	 alert.accept();
	 
	 // Create new Confirm alert
	 jsExcecutor.executeScript("confirm('Create a new confirm alert');");
	 Thread.sleep(3000);
	 
	 // Switch qua Alert
	 alert = driver.switchTo().alert();
	 Assert.assertEquals(alert.getText(), "Create a new confirm alert");
	 // Cancel (Confirm/ Prompt)
	 Assert.assertEquals(alert.getText(), "Create a new confirm alert");
	 alert.dismiss();
	
	 // Create new Confirm alert
	 jsExcecutor.executeScript("prompt('Create a new prompt alert');");
	 Thread.sleep(3000);
	 
	 // Switch qua Alert
	 alert = driver.switchTo().alert();
	 // Sendkey (Prompt)
	 alert.sendKeys("Selenium");
	 Thread.sleep(3000);
	 
	 Assert.assertEquals(alert.getText(), "Create a new prompt alert");  
	 alert.accept();
  }
  
  public void TC_05_Prompt_Alert() throws Exception {
	 
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  String name = "JavaScript";
	  driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	  
	  alert = driver.switchTo().alert();
	  
	  // Verify title
	  Assert.assertEquals(alert.getText(), "I am a JS prompt");
	  
	  // Sendkey
	  alert.sendKeys(name);
	  Thread.sleep(3000);
	  
	  // Accept
	  alert.accept();
	  
	  // Verify result
	  Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + name);
  }
  
  public void TC_06_Authen_Alert() throws Exception {
		 
	  String username = "admin";
	  String password = "admin";
	  
	  driver.get("http://" + username + ":" + password + "@" +"the-internet.herokuapp.com/basic_auth");
	   
	  // Verify result
	  Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
  }
  
  public void TC_07_Authen_Alert() throws Exception {
		 
	  String username = "admin";
	  String password = "admin";
	  
	  driver.get("http://" + username + ":" + password + "@" +"the-internet.herokuapp.com/basic_auth");
	   
	  // Verify result
	  Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
  }
  
  public boolean isElementEnabled(By by) {
	  WebElement element = driver.findElement(by);
	  if(element.isEnabled())
	  {
		  System.out.println("Element is enabled :" + by);
		  return true;
	  }
	  else
	  {
		  System.out.println("Element is disabled :" + by);
		  return false;
	  }
  }
  
  public void removeDisabledAttributeByJS(By by){
	  WebElement element = driver.findElement(by);
	  jsExcecutor.executeScript("arguments[0].removeAttribute('disable')", element);
  }
  
  public void ClickByJS(By by){
	  WebElement element = driver.findElement(by);
	  jsExcecutor.executeScript("arguments[0].click()", element);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
