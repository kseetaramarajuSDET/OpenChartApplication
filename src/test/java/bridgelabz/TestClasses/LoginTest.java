package bridgelabz.TestClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import bridgelabz.PageClasses.LoginPage;
import bridgelabz.PageClasses.MyAccountPage;
import bridgelabz.UtilityClases.DataProviderUtility;

public class LoginTest extends BaseTest{

	LoginPage loginPage;
	MyAccountPage accountPage;


	@Test(groups = {"sanity","functional"})
	public void verify_Login_With_Valid_Credentials() {
		loginPage=new LoginPage(DriverManager.getDriver());

		String username=properties.getProperty("email");
		String password=properties.getProperty("password");

		Assert.assertTrue(loginPage.loginWithValidCredentials(username,password));
	}
	
	
	@Test(dataProvider = "logindata",dataProviderClass = DataProviderUtility.class,groups ="datadriven")
	public void verify_Login_With_Valid_And_InValid_Credentials(String email,String password,String expected) {
		loginPage=new LoginPage(DriverManager.getDriver());
        accountPage=new MyAccountPage(DriverManager.getDriver());
		
		boolean status =loginPage.login_With_Valid_And_Invalid_Credentials(email,password);
		
		if (expected.equalsIgnoreCase("valid"))
	    {
	    	if (status) {
		        accountPage.clickOnLogout();
		        Assert.assertTrue(true);
		    } else {
		        Assert.assertTrue(false);
		    }
	    }
		else if(expected.equalsIgnoreCase("invalid"))
	    {
	    	if (status) {
		        accountPage.clickOnLogout();
		        Assert.assertTrue(false);
		    } else {
		        Assert.assertTrue(true);
		    }
	    }

	}


}
