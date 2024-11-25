package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	
	public MyAccountPage(WebDriver driver) {   //constructor
		super(driver);
	}
	
	@FindBy(xpath = "//h2[normalize-space()='My Account']")  //
	WebElement msgHeading;
	
	public boolean isMyAccountPageExists() {
		try {
			return (msgHeading.isDisplayed());
		} catch (Exception e) {
			return false;
		}
	}
	
	/*
	   public String setConfirmationMessage() {
		try {
			return (msgConfirmation.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
	}
	 */
	
}
