package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageobjects.LoginPage;

public class LoginPageTest extends BaseClass {

	@Test
	public void verifyLogin() {
		
	logger.info("/******Verify Logging Started*********/");
	LoginPage lp=new LoginPage(driver);
	lp.enterUsername(username);
	logger.info("Username entered successfully");
	lp.enterPassword(password);
	logger.info("Password entered successfully");
	lp.clickLoginButton();
	logger.info("clicked on loginbutton successfully");

	}
	
}
