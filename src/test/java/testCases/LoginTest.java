package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class LoginTest extends BaseClass {
	
	@Test()
	public void verify_Login() throws InterruptedException 
	{
		try {
		//Home Page
		HomePage hp = new HomePage(driver);
		Thread.sleep(5000);
		hp.clickMyAccount();
		Thread.sleep(6000);
		hp.clickLogin();
		Thread.sleep(3000);
		
		//Login Page
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		Thread.sleep(3000);
		lp.setPassword(p.getProperty("password"));
		Thread.sleep(3000);
		lp.clickLogin();
		
		
		//MyAccount Page
		
		MyAccountPage accpage = new MyAccountPage(driver);
		boolean myaccountpage = accpage.isMyAccountPageExists();
		
		Thread.sleep(5000);
		Assert.assertEquals(myaccountpage, true);
		
		// Assert.assertTrue(myaccountpage);
		}
		catch(Exception e) {
			Assert.fail();
		} 
	}

}
