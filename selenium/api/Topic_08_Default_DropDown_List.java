package api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_08_Default_DropDown_List {
  
  private WebDriver driver;
  private Select select;
 
  @BeforeClass
  public void beforeClass() {
	  
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize(); 
  }	
	
  
  public void TC_01_Default_Dropdown() throws InterruptedException {
	  
	  driver.get("https://automationfc.github.io/basic-form/index.html");
	  
	  // Thao tác với dropdown (Job 01)
	  select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));
	  
	  boolean status = select.isMultiple();
	  System.out.print("Status =" + status);
	  Assert.assertFalse(status);
	  
	  /*
	   *<select id="job1" name="user_job1">
	   0	<option value="automation">Automation Testing</option>
	   1	<option value="manual">Manual Testing</option>
	   2	<option value="website">Adhoc Testing</option>
	   3	<option value="mobile">Mobile Testing</option>
	   4	<option value="desktop">Desktop Testing</option>
	   5	<option value="security">Security Testing</option>
	   6	<option value="perfomance">Perfomance Testing</option>
	   7	<option value="intergration">Intergration Testing</option>
	   8	<option value="unit">Unit Testing</option>
	   9	<option value="function">Functional UI Testing</option>
			</select>  */
	  
	  select.selectByIndex(3);
	  Thread.sleep(3000);
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
	  
	  select.selectByValue("manual");
	  Thread.sleep(3000);
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
	  
	  select.selectByVisibleText("Functional UI Testing");
	  Thread.sleep(3000);
	  Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
	  
	  int jobDropdownItem = select.getOptions().size();
	  Assert.assertEquals(jobDropdownItem, 10);
	  
	  // Thao tác với dropdown (Job 02)
	  select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
	  
	  Assert.assertTrue(select.isMultiple());
	  select.selectByVisibleText("Automation");
	  Thread.sleep(1000);
	  select.selectByVisibleText("Mobile");
	  Thread.sleep(1000);
	  select.selectByVisibleText("Desktop");
	  Thread.sleep(1000);
	  
	  List<WebElement> jobRole02Selected = select.getAllSelectedOptions();
	  for(WebElement select: jobRole02Selected) {
		  System.out.println(select.getText());
	  }
	  
	  Assert.assertEquals(select.getAllSelectedOptions().size(), 3);
	  
	  select.deselectAll();
	  
	  Assert.assertEquals(select.getAllSelectedOptions().size(), 0);
  }
  
  @Test
  public void TC_02_Default_DropDown() {
	  driver.get("https://demo.nopcommerce.com/register");
	  driver.findElement(By.cssSelector(".ico-register")).click();
	  
	  driver.findElement(By.cssSelector("#gender-male")).click();
	  driver.findElement(By.cssSelector("#FirstName")).sendKeys("John");
	  driver.findElement(By.cssSelector("#LastName")).sendKeys("Terry");
	  
	  select = new Select(driver.findElement(By.name("DateOfBirthDay")));
	  select.selectByVisibleText("1");
	  Assert.assertEquals(select.getOptions().size(), 32);
	  
	  select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
	  select.selectByVisibleText("May");
	  Assert.assertEquals(select.getOptions().size(), 13);
	  
	  select = new Select(driver.findElement(By.name("DateOfBirthYear")));
	  select.selectByVisibleText("1988");
	  Assert.assertEquals(select.getOptions().size(), 112);
	  
	  driver.findElement(By.cssSelector("#Email")).sendKeys("johnterry" + randomNumber()+ "@gmail.com");
	  driver.findElement(By.cssSelector("#Company")).sendKeys("Fossil Company");
	  driver.findElement(By.cssSelector("#Password")).sendKeys("hoangnam123");
	  driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("hoangnam123");
	  
	  driver.findElement(By.cssSelector("#register-button")).click();
	  driver.findElement(By.cssSelector("#register-button")).click();
	  
	  Assert.assertTrue(driver.findElement(By.cssSelector(".result")).getText().equals("Your registration completed"));
	  Assert.assertTrue(driver.findElement(By.cssSelector(".ico-account")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.cssSelector(".ico-logout")).isDisplayed());

  }
  
  
  public int randomNumber() {
	  Random rand = new Random();
	  return rand.nextInt(999999);
  }
 
  
  @AfterClass
  public void afterClass() {
	  //driver.quit();
  }

}
