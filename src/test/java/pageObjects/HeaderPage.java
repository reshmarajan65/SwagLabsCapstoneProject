package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage {
    WebDriver driver;

    public HeaderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator
    By header = By.className("app_logo");

    // Verify header is displayed
    public boolean isHeaderDisplayed() {
        return driver.findElement(header).isDisplayed();
    }

    // Get header text
    public String getHeaderText() {
        return driver.findElement(header).getText();
    }
}
