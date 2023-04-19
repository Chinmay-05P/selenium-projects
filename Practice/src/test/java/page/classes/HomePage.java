package page.classes;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class HomePage {
	private static final Logger log = Logger.getLogger(HomePage.class.getName());
	WebDriver driver;
	ExtentTest test;
	private JavascriptExecutor js;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement registerLink;
	
	@FindBy(id="customer.firstName")
	WebElement firstName;
	
	@FindBy(id="customer.lastName")
	WebElement lastName;
	
	@FindBy(id="customer.address.street")
	WebElement address;
	
	@FindBy(id="customer.address.city")
	WebElement city;
	
	@FindBy(id="customer.address.state")
	WebElement state;
	
	@FindBy(id="customer.address.zipCode")
	WebElement zipCode;
	
	@FindBy(id="customer.phoneNumber")
	WebElement phoneNumber;
	
	@FindBy(id="customer.ssn")
	WebElement ssn;
	
	@FindBy(id="customer.username")
	WebElement userName;
	
	@FindBy(id="customer.password")
	WebElement password;
	
	@FindBy(id="repeatedPassword")
	WebElement repeatedPassword;
	
	@FindBy(xpath="//input[@value='Register']")
	WebElement registerBtn;
	
	@FindBy(xpath="//h1[text()='Welcome test1']")
	WebElement welcomeText;
	
	@FindBy(xpath="//p[text()='Your account was created successfully. You are now logged in.']")
	WebElement successfulRegister;
	
	@FindBy(id = "customer.username.errors")
	WebElement userAlreadyExistMsg;
	
	@FindBy(name="username")
	WebElement loginUserName;
	
	@FindBy(name="password")
	WebElement loginPassword;
	
	@FindBy(xpath="//input[@value='Log In']")
	WebElement loginBtn;
	
	@FindBy(xpath="//p[@class='smallText']")
	WebElement loginSuccessful;
	
	@FindBy(xpath = "//p[text()='The username and password could not be verified.']")
	WebElement invalidUserMsg;
	
	public void clickOnRegister() {
		registerLink.click();
		log.info("Clicked on register link");
		test.info("Clicked on register link");
	}
	
	public void registerCustomer(String fName,String lName, String adrs, String cty,String st, String zc, String phone, String sn, String uName, String pw, String cpw) {
		clickOnRegister();
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		address.sendKeys(adrs);
		city.sendKeys(cty);
		state.sendKeys(st);
		zipCode.sendKeys(zc);
		phoneNumber.sendKeys(phone);
		ssn.sendKeys(sn);
		userName.sendKeys(uName);
		password.sendKeys(pw);
		repeatedPassword.sendKeys(cpw);
		registerBtn.click();
		log.info("Entered the data and clicked on register button");
		test.info("Entered the data and clicked on register button");
		try {
			if(loginSuccessful.isDisplayed()) {
				log.info("Registration successful");
				test.pass("Registration successful");
			}
		}
		catch(NoSuchElementException e) {
			js.executeScript("arguments[0].scrollIntoView(true)", userAlreadyExistMsg);
			if( userAlreadyExistMsg.isDisplayed()) {
				log.info(userAlreadyExistMsg.getText() + " Try LogIn");
				test.fail(userAlreadyExistMsg.getText() + " Try LogIn");
				Assert.fail();
			}
		}
	}
	
	public void loginCustomer(String uName, String pw) {
		loginUserName.sendKeys(uName);
		loginPassword.sendKeys(pw);
		loginBtn.click();
		try {
			if(loginSuccessful.isDisplayed()) {
				log.info("Login successful");
				test.pass("Login successful");
			}
		}
		catch(NoSuchElementException e) {
			if(invalidUserMsg.isDisplayed()) {
				log.info(invalidUserMsg.getText() + " Try Register");
				test.fail(invalidUserMsg.getText() + " Try Register");
				Assert.fail();
			}
		}
	}
	
	public HomePage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);
	}
	
}
