package testclasses;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import extentreport.ExtentFactory;
import page.classes.AccountOverview;
import page.classes.HomePage;
import utilities.TakeScreenshot;

public class Test1 {
	private static final Logger log = Logger.getLogger(Test1.class.getName()); //LogManager.getLogger(Test1.class);
	
	public WebDriver driver;
	public String baseURL;
	private HomePage homepage;
	private TakeScreenshot screenshot;
	private AccountOverview accountOverview;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void beforeClass() {
		report = ExtentFactory.getInstance();
		
	}
	
	@BeforeMethod
	public void beforeMethod(ITestResult result) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\chpa1122\\Documents\\Courses\\Softwares\\Drivers\\chromedriver_win32\\chromedriver.exe");
		DOMConfigurator.configure(System.getProperty("user.dir") + "/resources/Log4j.xml");
		
		
		//test = report.startTest(result.getMethod().getMethodName());
		test = report.createTest(result.getMethod().getMethodName());
		driver = new ChromeDriver(options);
		baseURL = "https://parabank.parasoft.com/parabank/index.htm";
		screenshot = new TakeScreenshot(driver);
		homepage = new HomePage(driver, test);
		accountOverview = new AccountOverview(driver, test);
		
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		log.info("Chrome Browser launched successfully...");
		test.info("Chrome Browser launched successfully...");
		System.out.println();
	}
	
	@Test
	public void register() {
		homepage.registerCustomer("test", "data1", "test address", "bgl", "kar", "590062", "982320932", "12345", "test1", "Test@123", "Test@123");
		
	}
	
	@Test
	public void login() {
		homepage.loginCustomer("test1", "Test@123");
	}
	
	@Test
	public void openNewAccount() throws InterruptedException {
		homepage.loginCustomer("test1", "Test@123");
		accountOverview.newAccount("SAVINGS");
	}
	
	@Test
	public void accountsOverview() {
		homepage.loginCustomer("test1", "Test@123");
		accountOverview.actsOverview();
	}
	
	@Test
	public void transferFunds() throws InterruptedException {
		homepage.loginCustomer("test1", "Test@123");
		accountOverview.transferFund("10");
	}
	
	@Test
	public void billPayment() {
		homepage.loginCustomer("test1", "Test@123");
		accountOverview.payBill("test", "test address", "bgl", "Kar", "590062", "9812092309", "4152", "4152", "10");
	}
	
	@Test
	public void findTransaction() {
		homepage.loginCustomer("test1", "Test@123");
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date date = new Date();
		String date1  = dateFormat.format(date);
		accountOverview.findTransactions(date1);
	}
	
	@Test
	public void updateProfile() {
		homepage.loginCustomer("test1", "Test@123");
		accountOverview.updateContactInfo("test1U", "dateU", "Test AddressU", "bglU", "karU", "590060", "9319488239");
	}
	
	@Test
	public void requestLoan() {
		homepage.loginCustomer("test1", "Test@123");
		accountOverview.loanRequest("100", "20");
	}
	
	@Test
	public void logOut() {
		homepage.loginCustomer("test1", "Test@123");
		accountOverview.logOutUser();
	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) throws IOException, InterruptedException {
		if(testResult.getStatus() == ITestResult.FAILURE) {
			log.info("Failed " + testResult.getMethod().getMethodName());
			String testName = testResult.getMethod().getMethodName();
			String path = screenshot.getScreenshot();
			ExtentTest imagePath = test.addScreenCaptureFromPath(path);
			test.fail("Test "+ testName +" Failed " );
		}
		/* Take screenshot for passed Test cases 
		 * else {
			String path = screenshot.getScreenshot();
			ExtentTest imagePath = test.addScreenCaptureFromPath(path);
		}*/
		Thread.sleep(3000);
		driver.quit();
	}
	
	@AfterClass
	public void afterClass() {
		report.flush();
	}
}
