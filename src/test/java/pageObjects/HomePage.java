package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {

		super(driver);
	}

	// //span[normalize-space()='My Account'] //i[@class='fa fa-user']
	@FindBy(xpath = "//span[@class='caret']")
	WebElement linkMyAccount;

	//// a[normalize-space()='Register']
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement linkLogin;

	public void clickMyAccount() 
	{
		linkMyAccount.click();
	}

	
	public void clickRegister()
	{
		linkRegister.click();
	}
	
	public void clickLogin()
	{
		linkLogin.click();
	}
}
