package bridgelabz.PageClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import bridgelabz.UtilityClases.CommonUtility;

public class RegistrationPage extends BasePage{
	
	public static Logger log = LogManager.getLogger(RegistrationPage.class);

	HomePage homePage;
	public RegistrationPage(WebDriver driver) {
		super(driver);
		homePage=new HomePage(driver);
	}

	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtFirstName;

	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtLastName;

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtTelephone;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;

	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtConfirmPassword;

	@FindBy(xpath = "//input[@type='checkbox']")
	WebElement btncheckbox;

	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//div[text()='Warning: E-Mail Address is already registered!']")
	WebElement headerEmailAlreadyExist;

	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement headerAccountCreationSuccessMessage;
	

	public boolean registerWithValidData()
	{
       
       log.info("Starting registration with valid data");

       homePage.clickOnMyAccountLink();

       homePage.clickOnRegisterLink();
       log.info("Clicked on Register link");

       String firstName = CommonUtility.randomString(5).toUpperCase();
       sendKeys(txtFirstName, firstName);
       log.info("Entered First Name: " + firstName);

       String lastName = CommonUtility.randomString(7).toUpperCase();
       sendKeys(txtLastName, lastName);
       log.info("Entered Last Name: " + lastName);

       String email = CommonUtility.randomString(6) + "@gmail.com";
       sendKeys(txtEmail, email);
       log.info("Entered Email: " + email);

       String mobile = CommonUtility.randomNumber(10);
       sendKeys(txtTelephone, mobile);
       log.info("Entered Telephone: " + mobile);

       String password = CommonUtility.randomPassword(10);
       sendKeys(txtPassword, password);
       sendKeys(txtConfirmPassword, password);
       log.info("Entered Password & Confirm Password");

       click(btncheckbox);
       log.info("Clicked on Privacy Policy checkbox");

       click(btnContinue);
       log.info("Clicked on Continue button");

       boolean status = isElementDisplayed(headerAccountCreationSuccessMessage);

       log.info("Registration successful status: " + status);
       
       return status;
        
	}
	




}
