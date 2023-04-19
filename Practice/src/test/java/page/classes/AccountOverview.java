package page.classes;

import static org.testng.Assert.fail;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

public class AccountOverview {
	private static final Logger log = Logger.getLogger(AccountOverview.class.getName());
	WebDriver driver;
	ExtentTest test;
	
	@FindBy(xpath = "//a[text()='Open New Account']")
	WebElement openNewAccountLink;
	
	@FindBy(id = "type")
	WebElement accountType;
	
	@FindBy(id = "fromAccountId")
	WebElement accounts;
	
	@FindBy(xpath = "//p[text()='Congratulations, your account is now open.']")
	WebElement actSuccessMsg;
	
	@FindBy(xpath = "//b[text()='Your new account number:']//following-sibling::a")
	WebElement newAccountNo;
	
	@FindBy(xpath = "//a[text()='Accounts Overview']")
	WebElement accountsOverviewLink;
	
	@FindBy(xpath = "//h1[text()='Accounts Overview']")
	WebElement confirmActOverviewPage;
	
	@FindBy(xpath = "//a[text()='Transfer Funds']")
	WebElement transferFundsLink;
	
	@FindBy(id = "amount")
	WebElement amountToTransfer;
	
	@FindBy(id = "fromAccountId")
	WebElement fromAccounts;
	
	@FindBy(id = "toAccountId")
	WebElement toAccounts;
	
	@FindBy(xpath = "//input[@value='Transfer']")
	WebElement transferBtn;
	
	@FindBy(xpath = "//h1[text()='Transfer Complete!']//following-sibling::p")
	WebElement transferSuccessMsg;
	
	@FindBy(xpath = "//a[text()='Bill Pay']")
	WebElement billPayLink;
	
	@FindBy(name = "payee.name")
	WebElement payeeName;
	
	@FindBy(name = "payee.address.street")
	WebElement payeeAddress;
	
	@FindBy(name = "payee.address.city")
	WebElement payeeCity;
	
	@FindBy(name = "payee.address.state")
	WebElement payeeState;
	
	@FindBy(name = "payee.address.zipCode")
	WebElement payeeZipcode;
	
	@FindBy(name = "payee.phoneNumber")
	WebElement payeePhoneNumber;
	
	@FindBy(name = "payee.accountNumber")
	WebElement payeeAccountNo;
	
	@FindBy(name = "verifyAccount")
	WebElement verifyPayeeAccountNo;
	
	@FindBy(name = "amount")
	WebElement pAmount;
	
	@FindBy(name = "fromAccountId")
	WebElement pFromAccount;
	
	@FindBy(xpath = "//input[@value='Send Payment']")
	WebElement sendPaymentBtn;
	
	@FindBy(xpath = "//h1[text()='Bill Payment Complete']")
	WebElement billPaymentConfirmMsg;
	
	@FindBy(xpath = "//a[text()='Find Transactions']")
	WebElement findTransactionLink;
	
	@FindBy(id = "accountId")
	WebElement transFromAccount;
	
	@FindBy(id = "criteria.onDate")
	WebElement findByDate;
	
	@FindBy(xpath = "//button[@ng-click=\"criteria.searchType = 'DATE'\"]")
	WebElement findTransactionsBtn;
	
	@FindBy(xpath = "//h1[text()='Transaction Results']")
	WebElement transactionResultMsg;
	
	@FindBy(xpath = "//a[text()='Update Contact Info']")
	WebElement updateContactInfoLink;
	
	@FindBy(id = "customer.firstName")
	WebElement updateFName;
	
	@FindBy(id = "customer.lastName")
	WebElement updateLName;
	
	@FindBy(id = "customer.address.street")
	WebElement updateAddress;
	
	@FindBy(id = "customer.address.city")
	WebElement updateCity;
	
	@FindBy(id = "customer.address.state")
	WebElement updateState;
	
	@FindBy(id = "customer.address.zipCode")
	WebElement updateZipCode;
	
	@FindBy(id = "customer.phoneNumber")
	WebElement updatePhoneNumber;
	
	@FindBy(xpath = "//input[@value='Update Profile']")
	WebElement updateProfileBtn;
	
	@FindBy(xpath = "//h1[text()='Profile Updated']")
	WebElement profileUpdateMsg;
	
	@FindBy(xpath = "//a[text()='Request Loan']")
	WebElement requestLoanLink;
	
	@FindBy(id = "amount")
	WebElement loanAmount;
	
	@FindBy(id = "downPayment")
	WebElement downPayment;
	
	@FindBy(id = "fromAccountId")
	WebElement loanfromAccount;
	
	@FindBy(xpath = "//input[@value='Apply Now']")
	WebElement applyNowBtn;
	
	@FindBy(xpath = "//h1[text()='Loan Request Processed']")
	WebElement loanSuccessMsg;
	
	@FindBy(xpath = "//a[text()='Log Out']")
	WebElement logOutLink;
	
	public void newAccount(String aType) throws InterruptedException {
		openNewAccountLink.click();
		log.info("Creating new account");
		test.info("Creating new account");
		
		Select type = new Select(accountType);
		type.selectByVisibleText(aType);
		log.info("Selected the account type: "+ aType);
		test.info("Selected the account type: "+ aType);
		
		Select account = new Select(accounts);
		account.selectByIndex(0);
		Thread.sleep(2000);
		WebElement openNewAccountBtn = driver.findElement(By.xpath("//input[@value='Open New Account']")) ;
		openNewAccountBtn.click();
		try {
			if(actSuccessMsg.isDisplayed()) {
				log.info("New account created successfully");
				log.info("New account number: " + newAccountNo.getText());
				test.pass("New account created successfully");
				test.info("New account number: " + newAccountNo.getText());
			}
		}
		catch(NoSuchElementException e) {
			log.info("Please select correct account");
			test.fail("Please select correct account");
			Assert.fail();
		}
	}
	
	public void actsOverview() {
		accountsOverviewLink.click();
		try {
			if(confirmActOverviewPage.isDisplayed()) {
				log.info("Accounts Overview page opened");
				test.pass("Accounts Overview page opened");
			}
		}
		catch(NoSuchElementException e) {
			log.info("Failed to load Accounts Overview page");
			test.fail("Failed to load Accounts Overview page");
			Assert.fail();
		}
	}
	
	public void transferFund(String amount) throws InterruptedException {
		transferFundsLink.click();
		Thread.sleep(2000);
		amountToTransfer.sendKeys(amount);
		Select frmAccount = new Select(fromAccounts);
		frmAccount.selectByIndex(0);
		Select toAccount = new Select(toAccounts);
		toAccount.selectByIndex(1);
		transferBtn.click();
		try {
			if(transferSuccessMsg.isDisplayed()) {
				log.info("Fund Transfer Successful");
				log.info(transferSuccessMsg.getText());
				test.info("Fund Transfer Scussessful");
				test.pass(transferSuccessMsg.getText());
			}
		}
		catch(NoSuchElementException e) {
			log.info("Customer have only one account");
			test.fail("Customer have only one account");
			Assert.fail();
		}
	}
	
	public void payBill(String pName, String pAddress, String pCity, String pState, String pZipcode, String pPhoneNumber, String pAccount, String pVerifyAccount, String pamount) {
		billPayLink.click();
		log.info("Navigating to Bill Payment page");
		test.info("Navigating to Bill Payment pages");
		payeeName.sendKeys("pName");
		payeeAddress.sendKeys(pAddress);
		payeeCity.sendKeys(pCity);
		payeeState.sendKeys(pState);
		payeeZipcode.sendKeys(pZipcode);
		payeePhoneNumber.sendKeys(pPhoneNumber);
		payeeAccountNo.sendKeys(pAccount);
		verifyPayeeAccountNo.sendKeys(pVerifyAccount);
		pAmount.sendKeys(pamount);
		Select pfromAccount = new Select(pFromAccount);
		pfromAccount.selectByIndex(0);
		log.info("Entered all the details");
		test.info("Entered all the details");
		sendPaymentBtn.click();
		try {
			if(billPaymentConfirmMsg.getAttribute("innerText").trim().equalsIgnoreCase("Bill Payment Complete")) {
				log.info(billPaymentConfirmMsg.getAttribute("innerText").trim());
				test.pass(billPaymentConfirmMsg.getAttribute("innerText").trim());
			}
		}
		catch(NoSuchElementException e) {
			log.info("Enter all the mandatory details");
			test.fail("Enter all the mandatory details");
			Assert.fail();
		}
	}
	
	public void findTransactions(String date) {
		findTransactionLink.click();
		log.info("Trying to find the transactions by date: " + date);
		test.info("Trying to find the transactions by date: " + date);
		Select transactionFromAccount = new Select(transFromAccount);
		transactionFromAccount.selectByIndex(0);
		findByDate.sendKeys(date);
		findTransactionsBtn.click();
		try {
			if(transactionResultMsg.isDisplayed()) {
				log.info(transactionResultMsg.getText());
				test.pass(transactionResultMsg.getText());
			}
		}
		catch(NoSuchElementException e) {
			log.info("Invalid Account");
			test.fail("Invalid Account");
			Assert.fail();
		}
	}
	
	public void updateContactInfo(String ufName, String ulName, String uAddress, String uCity, String uState, String uZipCode, String uPhoneNumber) {
		updateContactInfoLink.click();
		log.info("Profile is getting updated");
		test.info("Profile is getting updated");
		updateFName.sendKeys(ufName);
		updateLName.sendKeys(ulName);
		updateAddress.sendKeys(uAddress);
		updateCity.sendKeys(uCity);
		updateState.sendKeys(uState);
		updateZipCode.sendKeys(uZipCode);
		updatePhoneNumber.sendKeys(uPhoneNumber);
		updateProfileBtn.click();
		try {
			if(profileUpdateMsg.isDisplayed()) {
				log.info(profileUpdateMsg.getText());
				test.pass(profileUpdateMsg.getText());
			}
		}
		catch(NoSuchElementException e) {
			log.info("Enter all the mandatory details");
			test.fail("Enter all the mandatory details");
			Assert.fail();
		}
	}
	
	public void loanRequest(String lAmount, String dPayment) {
		requestLoanLink.click();
		log.info("Requesting loan amount " + lAmount + "with down payment of "+ dPayment);
		test.info("Requesting loan amount " + lAmount + "with down payment of "+ dPayment);
		loanAmount.sendKeys(lAmount);
		downPayment.sendKeys(dPayment);
		Select loanFromAccount = new Select(loanfromAccount);
		loanFromAccount.selectByIndex(0);
		applyNowBtn.click();
		try {
			if(loanSuccessMsg.isDisplayed()) {
				log.info(loanSuccessMsg.getText());
				test.pass(loanSuccessMsg.getText());
			}
		}
		catch(NoSuchElementException e) {
			log.info("Enter all the mandatory details");
			test.fail("Enter all the mandatory details");
			Assert.fail();
		}
	}
	
	public void logOutUser() {
		logOutLink.click();
		if(driver.getCurrentUrl().equalsIgnoreCase("https://parabank.parasoft.com/parabank/index.htm?ConnType=JDBC")) {
			log.info("Log Out Successful");
			test.pass("Log Out Successful");
		}
	}
	
	public AccountOverview(WebDriver driver,ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

}
