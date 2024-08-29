package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
		}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")
	WebElement msgheading;
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	WebElement lnkLogout;
	
	public boolean isMyAccountPageExist() {
		try {
			return msgheading.isDisplayed();
		} 
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickLogout() {
		lnkLogout.click();
	}

}
