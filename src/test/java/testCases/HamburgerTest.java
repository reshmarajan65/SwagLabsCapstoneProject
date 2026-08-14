package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.HamburgerPage;
import pageObjects.LoginPage;

public class HamburgerTest extends BaseTest {

    LoginPage login;
    HamburgerPage hamburger;
    CartPage cart;


    @BeforeMethod
    public void initializePage() {

        login = new LoginPage(driver);

        hamburger = new HamburgerPage(driver);

        cart = new CartPage(driver);

        // Login
        login.login(
                "standard_user",
                "secret_sauce");
    }


    @Test
    public void verifyAllItems() {

        hamburger.clickHamburgerMenu();

        hamburger.clickAllItems();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "All Items page is not displayed"
        );
    }


    @Test
    public void verifyAbout() {

        hamburger.clickHamburgerMenu();

        hamburger.clickAbout();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("saucelabs.com"),
                "About page is not displayed"
        );
    }


    @Test
    public void verifyCloseIcon() {

        hamburger.clickHamburgerMenu();

        hamburger.clickCloseIcon();

        Assert.assertTrue(
                driver.findElement(
                        By.id("react-burger-menu-btn")
                ).isDisplayed(),
                "Hamburger menu is not closed"
        );
    }


    @Test
    public void verifyLogout() {

        hamburger.clickHamburgerMenu();

        hamburger.clickLogout();

        Assert.assertTrue(
                driver.findElement(
                        By.id("user-name")
                ).isDisplayed(),
                "Login page is not displayed after logout"
        );
    }


    @Test
    public void verifyResetAppState() {

        // Add product to cart
        driver.findElement(
                By.id("add-to-cart-sauce-labs-backpack")
        ).click();

        // Verify cart badge count is 1
        Assert.assertEquals(
                cart.getCartBadgeCount(),
                "1",
                "Cart badge count is not updated to 1"
        );

        // Open hamburger menu
        hamburger.clickHamburgerMenu();

        // Reset App State
        hamburger.clickResetAppState();

        // Verify cart badge is removed
        Assert.assertFalse(
                cart.isCartBadgeDisplayed(),
                "Cart badge is still displayed after Reset App State"
        );
    }
}