package testCases;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;

public class HappyFlowTestSwagLabs extends BaseTest {

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

        // Login
        login.login(
                "standard_user",
                "secret_sauce"
        );
       
    }


    @Test
	public void verifyHappyFlowSwagLabs() {
	
	
	    // 1. Verify Products Page
	
	    Assert.assertEquals(
	            products.getPageTitle(),
	            "Products",
	            "Products page is not displayed"
	    );
	 
	
	
	    // 2. Add Sauce Labs Backpack to Cart
	
	    String productName = "Sauce Labs Backpack";
	
	    products.addProduct(productName);
	
	
	    // 3. Verify Cart Badge Count
	
	    Assert.assertEquals(
	            products.getCartCount(),
	            1,
	            "Cart badge count is not 1 after adding product"
	    );
	
	
	    // 4. Open Cart
	    
	   
	    products.clickCart();

	
	
	    // 5. Verify Cart Page
	
	    Assert.assertEquals(
	            cart.getCartPageTitle(),
	            "Your Cart",
	            "Cart page is not displayed"
	    );
	
	
	    // 6. Verify Product in Cart
	
	    Assert.assertTrue(
	            cart.isProductDisplayed(productName),
	            "Product is not displayed in cart"
	    );
	
	
	
	    // 7. Verify Cart Item Count
	
	    Assert.assertEquals(
	            cart.getCartItemCount(),
	            1,
	            "Cart should contain exactly one product"
	    );
	
	
	    // 8. Verify Quantity
	
	    Assert.assertEquals(
	            cart.getQuantity(),
	            "1",
	            "Product quantity is not 1"
	    );
	
	
	    // 9. Verify Product Price
	
	    Assert.assertEquals(
	            cart.getPrice(productName),
	            "$29.99",
	            "Product price is incorrect"
	    );
	
	
	
	    // 10. Checkout
	    
	    
	    cart.clickCheckout();
	  
	
	    // 11. Enter Checkout Information
	    
	    
	    checkout.enterFirstName("John");
	   
	    checkout.enterLastName("Doe");
	
	    checkout.enterPostalCode("695001");
	 
	    checkout.clickContinue();
	    
	
	    // 12. Wait for Checkout Overview Page
	
	    WebDriverWait wait =
	            new WebDriverWait(driver, Duration.ofSeconds(10));
	
	    wait.until(
	            ExpectedConditions.urlContains("checkout-step-two.html")
	    );
	
	
	    // 13. Verify Checkout Overview
	    
	    Assert.assertTrue(
	            checkout.isCheckoutOverviewDisplayed(),
	            "Checkout Overview page is not displayed"
	    );
	
	
	    // 14. Verify Product in Overview
	
	    Assert.assertEquals(
	            checkout.getItemName(),
	            productName,
	            "Product name in Checkout Overview is incorrect"
	    );
	
	
	    // 15. Verify Product Price in Overview
	
	    Assert.assertEquals(
	            checkout.getItemPrice(),
	            "$29.99",
	            "Product price in Checkout Overview is incorrect"
	    );
	
	
	    // 16. Verify Product Quantity
	
	    Assert.assertEquals(
	            checkout.getItemQuantity(),
	            "1",
	            "Product quantity in Checkout Overview is incorrect"
	    );
	
	    
	    // 17. Verify Subtotal
	
	    Assert.assertTrue(
	            checkout.getSubtotal().contains("29.99"),
	            "Subtotal is incorrect"
	    );
	
	
	    // 18. Verify Tax
	
	    Assert.assertTrue(
	            checkout.getTax().contains("2.40"),
	            "Tax is incorrect"
	    );
	
	
	    // 19. Verify Total
	
	    Assert.assertTrue(
	            checkout.getTotal().contains("32.39"),
	            "Total is incorrect"
	    );
	
	
	    // 20. Finish Order
	    
	   
	    checkout.clickFinish();
	

	    
	    // 21. Verify Checkout Complete Page
	
	    Assert.assertTrue(
	            checkout.isCheckoutCompleteDisplayed(),
	            "Checkout Complete page is not displayed"
	    );
	
	
	    // 22. Verify Order Confirmation Message
	
	    Assert.assertEquals(
	            checkout.getCompleteMessage(),
	            "Thank you for your order!",
	            "Order confirmation message is incorrect"
	    );
	
	
	    // 23. Back Home
	    
	   
	    checkout.clickBackHome();
	  
	
	
	    // 24. Verify Products Page After Back Home
	
	    Assert.assertEquals(
	            products.getPageTitle(),
	            "Products",
	            "Products page is not displayed after clicking Back Home"
	    );
	}
}