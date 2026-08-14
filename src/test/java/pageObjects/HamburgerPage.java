package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HamburgerPage {

    WebDriver driver;

    public HamburgerPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators

    By hamburgerMenu = By.id("react-burger-menu-btn");

    By closeIcon = By.id("react-burger-cross-btn");

    By allItems = By.id("inventory_sidebar_link");

    By about = By.id("about_sidebar_link");

    By logout = By.id("logout_sidebar_link");

    By resetAppState = By.id("reset_sidebar_link");


    // Actions

    public void clickHamburgerMenu() {
        driver.findElement(hamburgerMenu).click();
    }

    public void clickCloseIcon() {
        driver.findElement(closeIcon).click();
    }

    public void clickAllItems() {
        driver.findElement(allItems).click();
    }

    public void clickAbout() {
        driver.findElement(about).click();
    }

    public void clickLogout() {
        driver.findElement(logout).click();
    }

    public void clickResetAppState() {
        driver.findElement(resetAppState).click();
    }
}