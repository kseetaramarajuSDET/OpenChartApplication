package bridgelabz.PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	

	@FindBy(xpath = "//h2[normalize-space()='My Account']")
	WebElement headerMyAccount;


	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement linklogout;

	
	public boolean is_MyAccount_Header_Is_Displayed() {
		return isElementDisplayed(headerMyAccount);
	}
	
	public void clickOnLogout() {
		click(linklogout);
	}
	
	
	
}
