package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviderMethods;

public class TC003_LoginDDT extends BaseClass{
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviderMethods.class, groups= "DataDriven")
	public void verify_loginDDT(String username, String password, String result) throws InterruptedException {
		
		try {

			logger.info("----TC003_LoginDDTest started--------");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			logger.info("----From Home Page to Login Page--------");
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(username);
			lp.setPassword(password);
			lp.clickLogin();
			
			logger.info("----From Login is successfull and from login page to MyAccount Page--------");
			
			MyAccountPage myacc = new MyAccountPage(driver);
			boolean targetpage = myacc.isMyAccountPageExist();
			
			if(result.equalsIgnoreCase("Valid")) {
				if(targetpage == true) {
					myacc.clickMyAccount();
					myacc.clickLogout();
					Assert.assertTrue(true);
				}
				else {
						Assert.assertTrue(false);
				}
			}
			
			else {
				if(targetpage==true) {
					myacc.clickMyAccount();
					myacc.clickLogout();
					Assert.assertTrue(false);
				}
				else {
					String errorMsg = lp.geterrorMsg();
					if(errorMsg.equals("Warning: No match for E-Mail Address and/or Password.")) {
						logger.info("----Error message successfully displayed--------");
					}
					Assert.assertTrue(true);
				}
			}
			
		}
		
		catch(Exception e) {
			Assert.fail();
		}
		Thread.sleep(2000);
		logger.info("----TC003_LoginDDTest finished--------");
		
	}

}
