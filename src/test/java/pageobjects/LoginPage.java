package pageobjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	
	public static WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver) {
		ldriver=rdriver;
		
		PageFactory.initElements(rdriver, this);
	}
	
	
	//Identify WebElements
	
	@FindBy(id="user-name")
	WebElement usernameField;
	
	@FindBy(id="password")
	WebElement passwordField;
	
	@FindBy(id="login-button")
	WebElement loginButton;
	
	
	//Identify actions on WebElement
	
	public void enterUsername(String username){
		usernameField.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
}
