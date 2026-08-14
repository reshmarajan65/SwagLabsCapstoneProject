package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

public class CheckoutOverviewTest extends BaseTest {

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


    // open overview page

    public void openOverviewPage() {

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

    }


	  // verify overview page

    @Test
    public void verifyCheckoutOverviewPage() {

        openOverviewPage();

        Assert.assertTrue(
                checkout.isCheckoutOverviewDisplayed(),
                "Checkout Overview page is not displayed"
        );

    }


    // verify product name

    @Test
    public void verifyProductName() {

        openOverviewPage();

        Assert.assertEquals(
                checkout.getItemName(),
                "Sauce Labs Backpack",
                "Incorrect product name"
        );

    }


   // verify product price

    @Test
    public void verifyProductPrice() {

        openOverviewPage();

        Assert.assertEquals(
                checkout.getItemPrice(),
                "$29.99",
                "Incorrect product price"
        );

    }


   
    // verify product quantity
    

    @Test
    public void verifyProductQuantity() {

        openOverviewPage();

        Assert.assertEquals(
                checkout.getItemQuantity(),
                "1",
                "Incorrect product quantity"
        );

    }


   
    // verify subtotal
  

    @Test
    public void verifySubtotalDisplayed() {

        openOverviewPage();

        Assert.assertTrue(
                checkout.getSubtotal()
                        .contains("Item total"),
                "Subtotal is not displayed"
        );

    }


   
    // verify tax

    @Test
    public void verifyTaxDisplayed() {

        openOverviewPage();

        Assert.assertTrue(
                checkout.getTax()
                        .contains("Tax"),
                "Tax is not displayed"
        );

    }


 
    // verify total
   

    @Test
    public void verifyTotalDisplayed() {

        openOverviewPage();

        Assert.assertTrue(
                checkout.getTotal()
                        .contains("Total"),
                "Total is not displayed"
        );

    }


   
    // verify finish button
  

    @Test
    public void verifyFinishButton() {

        openOverviewPage();

        checkout.clickFinish();

        Assert.assertTrue(
                checkout.isCheckoutCompleteDisplayed(),
                "Checkout Complete page is not displayed"
        );

    }

}