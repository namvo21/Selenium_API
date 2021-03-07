package api;

import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Topic_04_Xpath_Css_Part_2 {
  
  private WebDriver driver;
  By emailTextbox = By.id("email");
  By passwordTextbox = By.id("pass");
  By loginButton = By.id("send2");
  
  String email = "vhnam" + randomNumber()+ "@gmail.com";
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
	  
  }	
  
  @BeforeMethod
  public void runforEachTestMethod() {
	  // Open AUT
	  driver.get("http://live.demoguru99.com/");
	  
	  // Click vào My Account link (Footer)
	  driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
  }	
	
  @Test
  public void TC_01_LoginWithEmptyEmailAndPassword() {
	  
	  // Input vào Email textbox
	  driver.findElement(emailTextbox).sendKeys("");
	  
	  // Input vào Password textbox
	  driver.findElement(passwordTextbox).sendKeys("");
	  
	  // Click vào Login button
	  driver.findElement(loginButton).click();
	  
	  // Verify email/ password error message displayed
	  // 1: Assert True (điều kiện)
	  boolean status = driver.findElement(By.id("advice-required-entry-email")).getText().equals("This is a required field.");
	  System.out.println("Status= " + status);
	  assertTrue(status);
	  
	  // 2: Assert Equal (điều kiện thực tế/ mong đợi)
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
  }
  
  @Test
  public void TC_02_LoginWithInvalidEmail() {
  	  // Input vào Email textbox
	  driver.findElement(emailTextbox).sendKeys("vhnam2110@gmail");
	  
	  // Input vào Password textbox
	  driver.findElement(passwordTextbox).sendKeys("");
	  
	  // Click vào Login button
	  driver.findElement(loginButton).click();
	  
	  // Verify email/ password error message displayed
	  // 2: Assert Equal (điều kiện thực tế/ mong đợi)
	  Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");

  }
  
  @Test
  public void TC_03_LoginWithPasswordLessThan6Chars() {
	  // Input vào Email textbox
	  driver.findElement(emailTextbox).sendKeys("vhnam267751@gmail.com");
	  
	  // Input vào Password textbox
	  driver.findElement(passwordTextbox).sendKeys("123");
	  
	  // Click vào Login button
	  driver.findElement(loginButton).click();
	  
	  // Verify password error message displayed
	  // 2: Assert Equal (điều kiện thực tế/ mong đợi)
	  Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
  }
  @Test
  public void TC_04_LoginWithIncorrectPassword() {
	  // Input vào Email textbox
	  driver.findElement(emailTextbox).sendKeys("vhnam267751@gmail.com");
	  
	  // Input vào Password textbox
	  driver.findElement(passwordTextbox).sendKeys("123456789");
	  
	  // Click vào Login button
	  driver.findElement(loginButton).click();
	  
	  // Verify password error message displayed
	  // 2: Assert Equal (điều kiện thực tế/ mong đợi)
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
  }
  @Test
  public void TC_05_LoginWithVadlidEmailAndPassword() {
	  
  }
  
  @Test
  public void TC_06_CreateNewUser() {
	  	  
	  // Click vào Create an Account button
	  driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	  
	  // Input vào First Name textbox
	  driver.findElement(By.id("firstname")).sendKeys("Nam");
	  
	  // Input vào Last Name textbox
	  driver.findElement(By.id("lastname")).sendKeys("Vo"); 
	  
	  // Input vào Email Address textbox
	  driver.findElement(By.id("email_address")).sendKeys(email);
	  
	  // Input vào Password textbox
	  driver.findElement(By.id("password")).sendKeys("123456"); 
	  
	  // Input vào Confirm Password textbox
	  driver.findElement(By.id("confirmation")).sendKeys("123456"); 
	  
	  // Click vào Confirm Password textbox
	  driver.findElement(By.xpath("//button[@title='Register']")).click(); 
	  
	  // 2: Assert Equal (điều kiện thực tế/ mong đợi)
	  Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
	  
	  // Click vào My Account
	  driver.findElement(By.xpath("//a[@class='skip-link skip-account']//span[text()='Account']")).click(); 
	  
	  // Click on Log out button
	  driver.findElement(By.xpath("//a[@title='Log Out']")).click();
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
