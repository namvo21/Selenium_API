package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_14_JavaScript_Executor {
  
  private WebDriver driver;
  private JavascriptExecutor jsExecutor;
  private WebElement element;
  private String email, password;
  private String customerName, birthDay, address, city, state, pinNo, mobileNum, gender;
  private By customerNameTextboxBy = By.name("name");
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
	  //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDriver/chromedriver");
	  //driver = new ChromeDriver();
	  
	  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/browserDriver/geckodriver");
	  driver = new FirefoxDriver();
	  
	  jsExecutor = (JavascriptExecutor)driver;
	  
	  customerName = "Selenium Online"; 
	  password = "123456";
	  gender = "male";
	  birthDay = "2000-01-10"; 
	  address = "123 Address" ; 
	  city = "Ho Chi Minh"; 
	  state = "Thu Duc"; 
	  pinNo= "123456";
	  mobileNum = "0123456987";
	  
		
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
 	  driver.manage().window().maximize();
 	  
 	  email = "test" + randomNumber() + "@gmail.com";
	  
  }	
	
  public void TC_01_JS() {
	  
	  navigateToUrlByJS( "http://live.demoguru99.com/");
	  
	  // Cast data type
	  String liveGuruDomain = (String) executeForBrowser("return document.domain;");
	  System.out.println("Live Guru Domain = " + liveGuruDomain);
	  Assert.assertEquals(liveGuruDomain, "live.demoguru99.com");
	  
	  String liveGuruUrl = (String) executeForBrowser("return document.URL;");
	  System.out.println("Live Guru URL = " + liveGuruUrl);
	  Assert.assertEquals(liveGuruUrl, "http://live.demoguru99.com/");
	  
	  highlightElement("//a[text()='Mobile']");
	  clickToElementByJS("//a[text()='Mobile']");
	  
	  highlightElement("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button[@title='Add to Cart']");
	  clickToElementByJS("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button[@title='Add to Cart']");
	  
	  Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
	  
	  highlightElement("//a[text()='Customer Service']");
	  clickToElementByJS("//a[text()='Customer Service']");
	  
	  String customerServiceTitle = (String) executeForBrowser("return document.title;");
	  System.out.println("Customer Service Page Title = " + customerServiceTitle);
	  Assert.assertEquals(customerServiceTitle, "Customer Service");
	  
	  highlightElement("//input[@id='newsletter']"); 
	  scrollToElement("//input[@id='newsletter']"); 
	  
	  sendkeyToElementByJS("//input[@id='newsletter']", "redobbe1@gmail.com");
	  
	  highlightElement(" //button[@title='Subscribe']");
	  clickToElementByJS(" //button[@title='Subscribe']");
	  
	  Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription.")); 
	  
	  navigateToUrlByJS("http://demo.guru99.com/v4/");
	  
	  // Cast data type
	  String demoGuruDomain = (String) executeForBrowser("return document.domain;");
	  System.out.println("Demo Guru Domain = " + demoGuruDomain);
	  Assert.assertEquals(demoGuruDomain, "demo.guru99.com");
	  
  }
  
  public void TC_02_Remove_Attribute() {
	  driver.get("http://demo.guru99.com/v4");
	  
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr316521");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("gegubap");
	  
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  String welcomeMsg = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
	  Assert.assertEquals(welcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
	  
	  driver.findElement(By.xpath("//a[text()='New Customer']")).click();
	  
	  driver.findElement(customerNameTextboxBy).sendKeys(customerName);
	  
	  removeAttributeInDOM("//input[@name='dob']", "type");
	  sleepInSeconds(2);
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
  }
  
  public void TC_03_Validation_Message() {
	  navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
	  
	  clickToElementByJS("//input[@class='btn']");
	  
	  String fnameMessage = getElementValidationMessage("//input[@id='fname']");
	  Assert.assertEquals(fnameMessage, "Please fill out this field.");
	  
	  sendkeyToElementByJS("//input[@id='fname']", "Nam Vo");
	  sleepInSeconds(2);
	  
	  String passMessage = getElementValidationMessage("//input[@id='pass']");
	  Assert.assertEquals(passMessage, "Please fill out this field.");
	  
	  sendkeyToElementByJS("//input[@id='pass']", "123456");
	  sleepInSeconds(2);
	  
	  String emMessage = getElementValidationMessage("//input[@id='em']");
	  Assert.assertEquals(emMessage, "Please fill out this field.");
	  
	  sendkeyToElementByJS("//input[@id='em']", "selenium@gmail.com");
	  sleepInSeconds(2);
	  
	  String addMessage = getElementValidationMessage("//select[@required]");
	  Assert.assertEquals(addMessage, "Please select an item in the list.");
	
  }
  
  @Test
  public void TC_04_OTP() {
	  driver.get("https://feature-qa.customer-frontend.staging.aspireapp.com/sg/login?entity=email&email=abcdeddf%40gmail.com&phone=%2B84545645676");
	  
	  clickToElementByJS("//div[@class='digit-input__input flex flex-center text-weight-medium cursor-pointer digit-input__input--highlight digit-input__input--blinking']");
	  sleepInSeconds(2);
	  highlightElement("//div[@class='digit-input__input flex flex-center text-weight-medium cursor-pointer digit-input__input--highlight digit-input__input--blinking']");
	  sleepInSeconds(2);
	  driver.findElement(By.xpath("//div[@class='digit-input__input flex flex-center text-weight-medium cursor-pointer digit-input__input--highlight digit-input__input--blinking']")).sendKeys("1");;
	  sleepInSeconds(2);
  }
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

  public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	 }

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		// Gốc
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSeconds(2);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}

	public String getElementValidationMessage(String locator) {
		element = driver.findElement(By.xpath(locator));
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
	}

	public boolean isImageLoaded(String locator) {
		element = driver.findElement(By.xpath(locator));
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", element);
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	  public boolean isElementDisplayed(String xpathLocator)
	  {
		  try
		  {
			  // 1 - Element hiển thị + có trong DOM
			  // 2 - Element không hiển thị + có trong DOM
			  // 3 - Element không hiển thị + không có trong DOM
			  WebElement element = driver.findElement(By.xpath(xpathLocator));
			  return element.isDisplayed();
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
			  return false;
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
	
	 public int randomNumber() {
		  Random rand = new Random();
		  return rand.nextInt(999999);
	  }
}
