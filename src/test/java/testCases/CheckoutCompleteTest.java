package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

public class CheckoutCompleteTest extends BaseTest {

    LoginPage login;

    ProductsPage products;

    CartPage cart;

    CheckoutPage checkout;


    @BeforeMethod
    public void initializePage() {

        login = new LoginPage(driver);

        products = new ProductsPage(driver);

        cart = new CartPage(driver);

        checkout = new CheckoutPage(driver);

    }


  
    // complete checkout

    public void completeCheckout() {

        login.login(
                "standard_user",
                "secret_sauce"
        );

        products.addProduct(
                "Sauce Labs Backpack"
        );

        products.clickCart();

        cart.clickCheckout();

        checkout.enterFirstName("Reshma");

        checkout.enterLastName("Rajan");

        checkout.enterPostalCode("695001");

        checkout.clickContinue();

        checkout.clickFinish();

    }


    // verify checkout complete
   
    @Test
    public void verifyCheckoutCompletePage() {

        completeCheckout();

        Assert.assertTrue(
                checkout.isCheckoutCompleteDisplayed(),
                "Checkout Complete page is not displayed"
        );

    }


   
    // verify confirmation message
   

    @Test
    public void verifyOrderConfirmationMessage() {

        completeCheckout();

        Assert.assertEquals(
                checkout.getCompleteMessage(),
                "Thank you for your order!",
                "Incorrect order confirmation message"
        );

    }


    
    // verify back home
   
    @Test
    public void verifyBackHomeButton() {

        completeCheckout();

        checkout.clickBackHome();

        Assert.assertTrue(
                driver.getCurrentUrl()
                     .contains("inventory"),
                "User was not redirected to Products page"
        );

    }

}