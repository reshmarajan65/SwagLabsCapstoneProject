package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartBadgePage {
    WebDriver driver;


    public CartBadgePage(WebDriver driver) {
        this.driver = driver;
    }


    // Locators

    By addBackpack = By.id("add-to-cart-sauce-labs-backpack");

    By removeBackpack = By.id("remove-sauce-labs-backpack");

    By cartBadge = By.className("shopping_cart_badge");



    // Add product

    public void addProduct() {

        driver.findElement(addBackpack).click();

    }



    // Remove product

    public void removeProduct() {

        driver.findElement(removeBackpack).click();

    }



    // Get cart count

    public String getCartCount() {

        return driver.findElement(cartBadge).getText();

    }



    // Verify badge count is displayed

    public boolean isCartBadgeDisplayed() {

        if(driver.findElements(cartBadge).size() > 0) {

            String count = driver.findElement(cartBadge).getText();

            return !count.isEmpty();

        }

        return false;

    }


}
