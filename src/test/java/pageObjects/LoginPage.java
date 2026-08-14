package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    //locators
    By txtUsername = By.id("user-name");
    By txtPassword = By.id("password");
    By btnLogin = By.id("login-button");
    By txtError = By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]");
    By btnErrorClose = By.className("error-button");
    By logo = By.className("login_logo");
    By menuButton = By.id("react-burger-menu-btn");
    By logoutLink = By.id("logout_sidebar_link");
    
    // Enter Username
    public void enterUsername(String username) {
        driver.findElement(txtUsername).clear();
        driver.findElement(txtUsername).sendKeys(username);
    }
    
    // login
    public void login(String username, String password) {
        driver.findElement(txtUsername).sendKeys(username);
        driver.findElement(txtPassword).sendKeys(password);
        driver.findElement(btnLogin).click();
    }
    
    //Error message
	public String getErrorMessage() {
		return driver.findElement(txtError).getText();
	}
	
	//logo displayed?
	public boolean isLogoDisplayed() {
		return driver.findElement(logo).isDisplayed();
	}
	
	//placeholder-username
	public String getUsernamePlaceholder() {
		return driver.findElement(txtUsername).getAttribute("placeholder");
	}
	
	//placeholder-password
    public String getPasswordPlaceholder() {
        return driver.findElement(txtPassword).getAttribute("placeholder");
    }
    
    //password masking
    public String getPasswordFieldType() {
        return driver.findElement(txtPassword).getAttribute("type");

    }
    
    //button enabled?
    public boolean isLoginButtonEnabled() {
        return driver.findElement(btnLogin).isEnabled();
    }
    
    //button text
    public String getLoginButtonText() {
        return driver.findElement(btnLogin).getAttribute("value");
    }
    
    // Get entered username value
    public String getEnteredUsername() {
        return driver.findElement(txtUsername).getAttribute("value");
    }


    // Enter password only
    public void enterPassword(String password) {
        driver.findElement(txtPassword).clear();
        driver.findElement(txtPassword).sendKeys(password);
    }


    // Get entered password value
    public String getEnteredPassword() {
        return driver.findElement(txtPassword).getAttribute("value");
    }


    // Verify error message is displayed
    public boolean isErrorMessageDisplayed() {
        return driver.findElement(txtError).isDisplayed();
    }
}
