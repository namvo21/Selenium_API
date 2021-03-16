package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_07_Textbox_TextArea {
  // Toàn cục: Global in class
  private WebDriver driver;
  private String email, userID, password, loginPageUrl, customerID;
  private String customerName, birthDay, address, city, state, pinNo, mobileNum, gender;
  private String editAddress, editCity, editState, editPinNo, editMobileNum, editEmail;
  private By customerNameTextboxBy = By.name("name");
  private By genderBy = By.name("gender");
  private By birthdayTextboxBy = By.name("dob");
  private By addressTextAreaBy = By.name("addr");
  private By cityTextboxBy = By.name("city");
  private By stateTextboxBy = By.name("state");
  private By pinnoTextboxBy = By.name("pinno");
  private By mobileTextboxBy = By.name("telephoneno");
  private By emailTextboxBy = By.name("emailid");
  private By passwordTextboxBy = By.name("password");
  private By submitButtonBy = By.name("sub");
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
 	  driver.get("http://demo.guru99.com/v4");
 	  
 	  email = "test" + randomNumber() + "@gmail.com";
 	  
 	  customerName = "Selenium Online"; 
 	  gender = "male";
 	  birthDay = "2000-01-10"; 
 	  address = "123 Address" ; 
 	  city = "Ho Chi Minh"; 
 	  state = "Thu Duc"; 
 	  pinNo= "123456";
 	  mobileNum = "0123456987";
 	  
 	  editAddress = "234 Edit Address"; 
 	  editCity = "Edit Ho Chi Minh";
 	  editState = "Edit Thu Duc"; 
 	  editPinNo = "645321"; 
 	  editMobileNum = "0987654321"; 
 	  editEmail = "selenium" + randomNumber() + "@gmail.com";
  }	
	
  @Test
  public void TC_01_RegisterToSystem() {
	  loginPageUrl = driver.getCurrentUrl();
	  
	  driver.findElement(By.xpath("//a[text()='here']")).click();
	  
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	  System.out.println("UserID at Register Page = " + userID);
	  
	  password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();
	  System.out.println("Password at Register Page = " + password);	  
  }
  
  @Test
  public void TC_02_LoginToSystem() {
	  
	  driver.get(loginPageUrl);
	  
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	  
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : "+ userID +"']")).isDisplayed());
	  // Cục bộ
	  String welcomeMsg = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
	  Assert.assertEquals(welcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
  }
  
  @Test
  public void TC_03_NewCustomer() {
	  
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  
	  driver.findElement(customerNameTextboxBy).sendKeys(customerName);
	  driver.findElement(birthdayTextboxBy).sendKeys(birthDay);
	  driver.findElement(addressTextAreaBy).sendKeys(address);
	  driver.findElement(cityTextboxBy).sendKeys(city);
	  driver.findElement(stateTextboxBy).sendKeys(state);
	  driver.findElement(pinnoTextboxBy).sendKeys(pinNo);
	  driver.findElement(mobileTextboxBy).sendKeys(mobileNum);
	  driver.findElement(emailTextboxBy).sendKeys(email);
	  driver.findElement(passwordTextboxBy).sendKeys(password);
	  
	  driver.findElement(submitButtonBy).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3']")).isDisplayed());
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), birthDay);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pinNo);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNum);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
	  
	  customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
  }
  
  @Test
  public void TC_04_EditCustomer() {
	  
	  driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
	  driver.findElement(By.name("cusid")).sendKeys(customerID);
	  driver.findElement(By.name("AccSubmit")).click();
	  
	  Assert.assertEquals(driver.findElement(customerNameTextboxBy).getAttribute("value"), customerName);
	  Assert.assertEquals(driver.findElement(addressTextAreaBy).getText(), address);
	  
	  Assert.assertFalse(driver.findElement(customerNameTextboxBy).isEnabled());
	  Assert.assertFalse(driver.findElement(genderBy).isEnabled());
	  Assert.assertFalse(driver.findElement(birthdayTextboxBy).isEnabled());
	  
	  driver.findElement(addressTextAreaBy).clear();
	  driver.findElement(addressTextAreaBy).sendKeys(editAddress);
	  
	  driver.findElement(cityTextboxBy).clear();;
	  driver.findElement(cityTextboxBy).sendKeys(editCity);
	  
	  driver.findElement(stateTextboxBy).clear();
	  driver.findElement(stateTextboxBy).sendKeys(editState);
	 
	  driver.findElement(pinnoTextboxBy).clear();
	  driver.findElement(pinnoTextboxBy).sendKeys(editPinNo);
	 
	  driver.findElement(mobileTextboxBy).clear();
	  driver.findElement(mobileTextboxBy).sendKeys(editMobileNum);
	 
	  driver.findElement(emailTextboxBy).clear();
	  driver.findElement(emailTextboxBy).sendKeys(editEmail);
	  
	  driver.findElement(submitButtonBy).click();
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3']")).isDisplayed());
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), birthDay);
	  
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPinNo);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editMobileNum);
	  Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
  }
 
  public int randomNumber() {
	  Random rand = new Random();
	  return rand.nextInt(999999);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
