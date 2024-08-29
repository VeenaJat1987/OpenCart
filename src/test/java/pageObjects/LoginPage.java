package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	WebElement errorMsg;
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}

	public void setEmail(String email) {
		txtEmail.sendKeys(email);
		
	}
	
	public String geterrorMsg() {
		try {
		return errorMsg.getText();
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}
}
