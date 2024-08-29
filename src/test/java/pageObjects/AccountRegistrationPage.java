package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);		
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement numTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfPassword;
		
	@FindBy(xpath="//input[@name='agree']")
	WebElement checkBoxPolicy;
		
	@FindBy(xpath="//input[@value='Continue']")
	WebElement buttonContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmMessage;
	
	
	public void setFirstname(String fName) {
		txtFirstname.sendKeys(fName);
	}
	
	public void setLastname(String lName) {
		txtLastname.sendKeys(lName);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String PhNumber) {
		numTelephone.sendKeys(PhNumber);
	}
	
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	
	public void setConfPassword(String confPassword) {
		txtConfPassword.sendKeys(confPassword);
	}
	
	public void selectPrivatePolicy() {
		checkBoxPolicy.click();
	}
	
	public void clickContinue() {
		buttonContinue.click();
		
//		buttonContinue.submit();
//		
//		Actions act = new Actions(driver);
//		act.moveToElement(buttonContinue).click().perform();
//		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", buttonContinue);
//		
//		buttonContinue.sendKeys(Keys.RETURN);
//		
//		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		mywait.until(ExpectedConditions.elementToBeClickable(buttonContinue)).click();
		
	}
	
	public String getConfirmationMsg() {
		try {
		return confirmMessage.getText();
		}
		catch(Exception e) {
			return e.getMessage();
		}
	}

}
