package testCases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import testBase.BaseClass;

public class AccountRegisterTest extends BaseClass {

	@Test
	public void verify_account_registration() throws InterruptedException {

		// try {
		HomePage hp = new HomePage(driver);
		Thread.sleep(5000);
		hp.clickMyAccount();
		Thread.sleep(6000);
		hp.clickRegister();
	}
}

			/*AccountRegisterPage accreg = new AccountRegisterPage(driver);
			accreg.setFirstName("Mirza");       // randomString().toUpperCase() --> randomly generated the first name
			// accreg.setFirstName(randomString().toUpperCase());
			accreg.setLastName("Raza");
			// accreg.setLastName(randomString().toUpperCase());
			accreg.setEmail("mirzaaqeeelraza11@gmail.com");
			// accreg.setEmail(randomString()+"@gmail.com");
			accreg.setTelephone("12121212112");
			// accreg.setTelephone(randomNumber());

			String password = randomAlphaNumeric();

			accreg.setPassword("Mirza@123");
			// accreg.setPassword(password);
			accreg.setConfirmPassword("Mirza@123");
			// accreg.setConfirmPassword(password);

			accreg.clickprivacyPolicy();
			accreg.clickContinue();

			String confmsg = accreg.setConfirmationMessage();

			Assert.assertEquals(confmsg, "Your Account Has Been Created!"); //Your Account Has Been Created!
		} catch (Exception e) {
			// logger.error("Test Failed");
			// logger.debud("Debug logs..");
			Assert.fail();
		}   
	}  
} */
