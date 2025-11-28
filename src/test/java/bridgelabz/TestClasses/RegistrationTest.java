package bridgelabz.TestClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import bridgelabz.PageClasses.RegistrationPage;

public class RegistrationTest extends BaseTest {
	
	RegistrationPage registrationPage;
	
	
	
	@Test(groups = {"smoke","functional"})
	public void verify_Registeration_With_Valid_Data() {

		// ================= Creating Objects For Required Page Classes ================= 
		registrationPage=new RegistrationPage(DriverManager.getDriver());
		Assert.assertTrue(registrationPage.registerWithValidData());		
	}
	
	

}
