package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_03_Xpath_Css_Part_1 {
  
  private WebDriver driver;
  
 
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
	  
  }	
	
  @Test
  public void TC_01_() {
	  driver.get("");
  }
  
  @Test
  public void TC_02_() {
	 
  }
  
  @Test
  public void TC_03_() {
	 
  }
 
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
