package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_03_Xpath_Css_Part_1 {
  
  private WebDriver driver;
  
 
  @BeforeClass
  public void beforeClass() {
	  // 1 - Mở trình duyệt lên
	  driver = new FirefoxDriver();
	  System.out.println(driver.toString());
	  // Trả về 1 cái GUID: xxxx-xxxx-xxxx-xxxx-xxxx
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
 	  
 	  // 2 - Mở app cần test lên
 	  // 3 - Tới page cần thao tác
 	  driver.get("https://m.facebook.com/");
 	  
 	  // Application (AUT): Application Under Testing
 	  // Pages/ Modules
 	  // 1 Page/ form: element
 	  // Facebook login: Email textbox/ password textbox/ login button/ ...
 	  // HTML Code: <input id="email" class="inputtext login_form_input_box" type="email" data-testid="royal_email" name="email"/>
 	  // HTML Format:
 	  // 1 - tagname: thẻ HTML
 	  // 2 - attribute name: id/ class/ type/ data-testid/ name
 	  // 3 - attribute value: email/ inputtext login_form_input_box/ ...
 	  
 	  // 4 - Cần thao tác với những element nào?
	  
  }	
	
  @Test
  public void TC_01_Id() throws Exception {
	  // Tìm single element: findElement
	  WebElement emailTextbox = driver.findElement(By.id("m_login_email"));
	  emailTextbox.clear();
	  emailTextbox.sendKeys("vhnam2110@gmail.com");
	  emailTextbox.isDisplayed();
	  Thread.sleep(3000);
  }
  
  @Test
  public void TC_02_Class() throws Exception {
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	  
	  // <input id="pass" class="input-text required-entry validate-password" type="password" title="Password" name="login[password]"/>
	  
	  driver.findElement(By.className("validate-password")).sendKeys("123456");
	  Thread.sleep(3000);
  }
  
  @Test
  public void TC_03_Name() {
	  //<input id="email" class="input-text required-entry validate-email" 
	  // type="email" title="Email Address" value="" name="login[username]" 
	  // spellcheck="false" autocorrect="off" autocapitalize="off"/>
	  driver.findElement(By.name("login[username]")).sendKeys("vhnam2110@gmail.com");
	  
  }
  @Test
  public void TC_04_Tagname() {
	  List <WebElement> links = driver.findElements(By.tagName("a"));
	  System.out.println("Link number = " + links.size());
  }
  
  @Test
  public void TC_05_LinkText() {
	  // <a title="My Account" href="http://live.demoguru99.com/index.php/customer/account/">My Account</a>
	  // Tuyệt đối
	  driver.findElement(By.linkText("MY ACCOUNT")).isDisplayed();	  
  }
  
  @Test
  public void TC_06_PartialLinkText() {
	  // <a title="My Account" href="http://live.demoguru99.com/index.php/customer/account/">My Account</a>
	  // Tương đối (chứa text)
	  driver.findElement(By.partialLinkText("ACCOUNT")).isDisplayed();
	  driver.findElement(By.partialLinkText("MY")).isDisplayed();
	  driver.findElement(By.partialLinkText("Y ACCOU")).isDisplayed();	  
  }
  @Test
  public void TC_07_Css() {
	  // <button id="send2" class="button" name="send" title="Login" type="submit">
	  driver.findElement(By.cssSelector("#send2")).isDisplayed();
	  driver.findElement(By.cssSelector("button[name='send']")).isDisplayed();
	  driver.findElement(By.cssSelector("button[title='Login']")).isDisplayed();
  }
  
  @Test
  public void TC_08_Xpath() {
	  // <button id="send2" class="button" name="send" title="Login" type="submit">
	  driver.findElement(By.xpath("//button[@id='send2']")).isDisplayed();
	  driver.findElement(By.xpath("//button[@name='send']")).isDisplayed();
	  driver.findElement(By.xpath("//button[@title='Login']")).isDisplayed();
  }
 
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
