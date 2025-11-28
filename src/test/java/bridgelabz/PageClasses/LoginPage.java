package bridgelabz.PageClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import bridgelabz.UtilityClases.DataProviderUtility;

public class LoginPage extends BasePage{


	public static Logger log = LogManager.getLogger(HomePage.class);

	HomePage homePage;
	MyAccountPage accountPage;

	public LoginPage(WebDriver driver) {
		super(driver);
		homePage=new HomePage(driver);
		accountPage=new MyAccountPage(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	WebElement inputEmail;

	@FindBy(xpath = "//input[@id='input-password']")
	WebElement inputPassword;

	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement headerMyAccount;




	public boolean loginWithValidCredentials(String email,String password)
	{

		log.info("Starting login process with valid credentials");

		log.info("Clicking on My Account link");
		homePage.clickOnMyAccountLink();

		log.info("Clicking on Login link");
		homePage.clickOnLoginLink();

		log.info("Entering email: " + email);
		sendKeys(inputEmail, email);

		log.info("Entering password");
		sendKeys(inputPassword, password);

		log.info("Clicking Login button");
		click(btnLogin);

		boolean status = accountPage.is_MyAccount_Header_Is_Displayed();

		if (status) {
			log.info("Login successful — My Account header is displayed");
		} else {
			log.error("Login failed — My Account header is NOT displayed");
		}

		return status;

	}


	public boolean login_With_Valid_And_Invalid_Credentials(String email,String password)
	{

		log.info("Starting login process with valid credentials");

		log.info("Clicking on My Account link");
		homePage.clickOnMyAccountLink();

		log.info("Clicking on Login link");
		homePage.clickOnLoginLink();

		log.info("Entering email: " + email);
		sendKeys(inputEmail, email);

		log.info("Entering password");
		sendKeys(inputPassword, password);

		log.info("Clicking Login button");
		click(btnLogin);

		boolean status = accountPage.is_MyAccount_Header_Is_Displayed();

		return status;
	}



}
