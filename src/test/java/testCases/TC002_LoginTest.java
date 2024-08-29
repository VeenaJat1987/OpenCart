package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	@Test(groups= {"Sanity", "Master"})
	public void verify_accountRegistration() {
		try {
	logger.info("----TC002_LoginTest started--------");
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	logger.info("----From Home Page to Login Page--------");
	
	LoginPage lp = new LoginPage(driver);
	lp.setEmail(prop.getProperty("username"));
	lp.setPassword(prop.getProperty("password"));
	lp.clickLogin();
	
	logger.info("----From Login is successfull and from login page to MyAccount Page--------");
	
	MyAccountPage myacc = new MyAccountPage(driver);
	boolean targetpage = myacc.isMyAccountPageExist();
	
	Assert.assertEquals(targetpage, true, "Login failed");
	
		}
		catch(Exception e ) {
			Assert.fail();
		}
		
		logger.info("--------------TC002_LoginTest finished-----------");
		}
}
