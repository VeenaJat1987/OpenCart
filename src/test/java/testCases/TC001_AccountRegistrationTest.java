package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
		
	@Test(groups= {"Regression", "Master"})
	public void verify_accountRegistration() {
		try {
	logger.info("----TC001_AccountRegistrationTest started--------");
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	hp.clickRegister();
	
	logger.info("----From Home Page to Register Page--------");
	
	AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
	regPage.setFirstname(randomString().toUpperCase());
	regPage.setLastname(randomString().toUpperCase());
	regPage.setEmail(randomString()+"@gmail.com");
	regPage.setTelephone(randomNumber());
	
	String pwd = randomAlphaNumber();
	regPage.setPassword(pwd);
	regPage.setConfPassword(pwd);
	
	regPage.selectPrivatePolicy();
	regPage.clickContinue();
	
	//logger.info("----Account Registration Confirmation--------");
	
	String confMsg = regPage.getConfirmationMsg();
	
	if(confMsg.equals("Your Account Has Been Created!")) {
		Assert.assertTrue(true);
	}
	else {
		logger.error("confirmation message not matching");
		logger.debug("Debug Logs");
		Assert.assertTrue(false);
	}
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("--------------TC001_AccountRegistrationTest finished-----------");
}
}