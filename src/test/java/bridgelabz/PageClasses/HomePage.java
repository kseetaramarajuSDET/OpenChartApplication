package bridgelabz.PageClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public static Logger log = LogManager.getLogger(HomePage.class);
	
	public HomePage(WebDriver driver) {
		super(driver);
	}


	@FindBy(xpath = "//a[@title='My Account']")
	WebElement myAccountLink;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement registerLink;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement loginLink;
	
	
	public void clickOnMyAccountLink() {
	    log.info("Clicking on 'My Account' link");
	    click(myAccountLink);
	}

	public void clickOnRegisterLink() {
	    log.info("Clicking on 'Register' link");
	    click(registerLink);
	}

	public void clickOnLoginLink() {
	    log.info("Clicking on 'Login' link");
	    click(loginLink);
	}
	

}
