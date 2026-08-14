package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import utils.ExcelUtility;

public class CartTest extends BaseTest {

    LoginPage login;

    ProductsPage products;

    CartPage cart;

    @BeforeMethod
    public void initializePage() {

        login = new LoginPage(driver);

        products = new ProductsPage(driver);

        cart = new CartPage(driver);

        login.login(
                "standard_user",
                "secret_sauce");

    }

    @DataProvider(name="cartData")
    public Object[][] cartData() throws IOException {

        ExcelUtility excel =
                new ExcelUtility(
                        "CartData.xlsx",
                        "CartData");

        Object[][] data =
                excel.getExcelData(5);

        excel.closeExcel();

        return data;

    }
    
    @Test(dataProvider="cartData")
    public void verifyProductQuantity(
            int rowNo,
            String productName,
            String expectedPrice,
            String expectedQty,
            String expectedResult) {

        products.addProduct(productName);

        cart.clickCart();

        Assert.assertEquals(
                cart.getQuantity(),
                expectedQty);

    }
    
    @Test(dataProvider="cartData")
    public void verifyProductPrice(
            int rowNo,
            String productName,
            String expectedPrice,
            String expectedQty,
            String expectedResult) {

        products.addProduct(productName);

        cart.clickCart();

        Assert.assertEquals(
                cart.getPrice(productName),
                expectedPrice);

    }
    
    @Test(dataProvider="cartData")
    public void verifyRemoveProduct(
            int rowNo,
            String productName,
            String expectedPrice,
            String expectedQty,
            String expectedResult) throws InterruptedException {

        products.addProduct(productName);

        cart.clickCart();

        cart.removeProduct(productName);

        Thread.sleep(1000);

        Assert.assertEquals(
                cart.getCartItemCount(),
                0,
                "Product was not removed from cart");
    }
    
    
    @Test
    public void verifyRemoveProductFromMultipleProducts() throws InterruptedException {


        // Add multiple products
        products.addProduct("Sauce Labs Backpack");
        products.addProduct("Sauce Labs Bike Light");
        products.addProduct("Sauce Labs Onesie");
        products.addProduct("Sauce Labs Fleece Jacket");


        // Open cart
        cart.clickCart();


        // Remove one product
        cart.removeProduct("Sauce Labs Backpack");


        Thread.sleep(1000);


        // Verify removed product is not displayed
        Assert.assertTrue(
                cart.isProductRemoved("Sauce Labs Backpack"),
                "Product was not removed from cart");

    }
    
    @Test
    public void verifyContinueShopping() {

        cart.clickCart();

        cart.clickContinueShopping();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");

    }
    
    @Test
    public void verifyCheckoutNavigation() {

        products.addProduct("Sauce Labs Backpack");

        cart.clickCart();

        cart.clickCheckout();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html");

    }
    
    @Test
    public void verifyCartPageDisplayed() {

        cart.clickCart();

        Assert.assertEquals(
                cart.getCartPageTitle(),
                "Your Cart");

    }
    
    @Test
    public void verifyMultipleProducts() {

        products.addProduct("Sauce Labs Backpack");

        products.addProduct("Sauce Labs Bike Light");

        products.addProduct("Sauce Labs Onesie");

        cart.clickCart();

        Assert.assertEquals(
                cart.getCartItemCount(),
                3);

    }
}