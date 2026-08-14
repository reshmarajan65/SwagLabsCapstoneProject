package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

   
    // Locators

    By cartIcon = By.className("shopping_cart_link");

    By cartTitle = By.className("title");

    By cartItems = By.className("cart_item");

    By cartQuantity = By.className("cart_quantity");

    By cartPrices = By.className("inventory_item_price");

    By continueShoppingButton = By.id("continue-shopping");

    By checkoutButton = By.id("checkout");

    By cartBadge = By.className("shopping_cart_badge");

   
    // Click Cart

    public void clickCart() {

        driver.findElement(cartIcon).click();

    }

    // Cart Page Title

    public String getCartPageTitle() {

        return driver.findElement(cartTitle).getText();

    }

    // Verify Product Displayed

    public boolean isProductDisplayed(String productName) {

        String xpath =
        "//div[text()='" + productName + "']";

        return driver.findElement(By.xpath(xpath))
                .isDisplayed();

    }

    // Cart Item Count

    public int getCartItemCount() {

        return driver.findElements(cartItems).size();

    }


    // Quantity

    public String getQuantity() {

        return driver.findElement(cartQuantity).getText();

    }

    // Price

    public String getPrice(String productName) {

        String xpath =
        "//div[text()='" + productName +
        "']/ancestor::div[@class='cart_item']//div[@class='inventory_item_price']";

        return driver.findElement(By.xpath(xpath)).getText();

    }


 // Remove product from cart
   /* public void removeProduct(String productName) {

        String buttonId = "remove-" + productName
                .toLowerCase()
                .replace(" ", "-");

        driver.findElement(By.id(buttonId)).click();
    } */
    
    public void removeProduct(String productName) {

        String xpath =
        "//div[text()='" + productName + "']" +
        "/ancestor::div[@class='cart_item']" +
        "//button[contains(text(),'Remove')]";

        driver.findElement(By.xpath(xpath)).click();
    }
    
   // Verify product is removed from cart
    public boolean isProductRemoved(String productName) {

        String xpath =
        "//div[text()='" + productName + "']";

        return driver.findElements(By.xpath(xpath)).size() == 0;
    }
 
    // Continue Shopping

    public void clickContinueShopping() {

        driver.findElement(continueShoppingButton).click();

    }

   
    // Checkout

    public void clickCheckout() {

        driver.findElement(checkoutButton).click();

    }
    
    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }

    public boolean isCartBadgeDisplayed() {
        return driver.findElements(cartBadge).size() > 0;
    }

}