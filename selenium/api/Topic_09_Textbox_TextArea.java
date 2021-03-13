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


public class Topic_09_Textbox_TextArea {
  // Toàn cục: Global in class
  private WebDriver driver;
  private String email, userID, password, loginPageUrl;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
 	  driver.get("http://demo.guru99.com/v4");
 	  
 	  email = "test" + randomNumber() + "@gmail.com";
  }	
	
  @Test
  public void TC_01_() {
	  loginPageUrl = driver.getCurrentUrl();
	  
	  driver.findElement(By.xpath("//a[text()='here']")).click();
	  
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  // Cục bộ
  }
  
  @Test
  public void TC_02_() {
	 
  }
  
  @Test
  public void TC_03_() {
	 
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
