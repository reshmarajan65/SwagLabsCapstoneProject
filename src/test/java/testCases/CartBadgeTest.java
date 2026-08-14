package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CartBadgePage;
import pageObjects.LoginPage;

public class CartBadgeTest extends BaseTest {


	@Test
	public void verifyCartBadgeCount() {

	    LoginPage login = new LoginPage(driver);

	    login.login("standard_user", "secret_sauce");


	    CartBadgePage cart = new CartBadgePage(driver);


	    // Add product
	    cart.addProduct();


	    // Check badge displayed after adding
	    boolean badgeAfterAdd = cart.isCartBadgeDisplayed();

	    System.out.println("Cart badge displayed after adding product: " + badgeAfterAdd);


	    Assert.assertTrue(badgeAfterAdd);



	    // Check cart count
	    String countAfterAdd = cart.getCartCount();

	    System.out.println("Cart count after adding product: " + countAfterAdd);


	    Assert.assertEquals(countAfterAdd, "1");



	    // Remove product
	    cart.removeProduct();



	    // Check badge after removing
	    boolean badgeAfterRemove = cart.isCartBadgeDisplayed();

	    System.out.println("Cart badge displayed after removing product: " + badgeAfterRemove);


	    Assert.assertFalse(badgeAfterRemove);

	}

}