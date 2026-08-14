package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import utils.ExcelUtility;

public class CheckoutInformationTest extends BaseTest {

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


   
    // Data provider
   
    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() throws IOException {

        ExcelUtility excel =
                new ExcelUtility(
                        "CheckoutData.xlsx",
                        "CheckoutData"
                );

        Object[][] data =
                excel.getExcelData(5);

        excel.closeExcel();

        return data;
    }


    
    // Open checkout page
    
    public void openCheckoutPage() {

        login.login(
                "standard_user",
                "secret_sauce"
        );

        products.addProduct(
                "Sauce Labs Backpack"
        );

        products.clickCart();

        cart.clickCheckout();

    }


    // datadriven checkout test
    
    @Test(dataProvider = "checkoutData")
    public void verifyCheckoutInformation(
    int rowNo,
    String firstName,
    String lastName,
    String postalCode,
    String expectedResult)
    throws IOException {
    ExcelUtility excel =
            new ExcelUtility(
                    "CheckoutData.xlsx",
                    "CheckoutData"
            );

    openCheckoutPage();

    checkout.enterFirstName(firstName);
    checkout.enterLastName(lastName);
    checkout.enterPostalCode(postalCode);
    checkout.clickContinue();

    String actualResult = "";

    try {

        // SUCCESS scenario
        if (expectedResult.equalsIgnoreCase("SUCCESS")) {

            if (checkout.isCheckoutOverviewDisplayed()) {

                actualResult = "SUCCESS";

            } else {

                actualResult = "Checkout Overview page is not displayed";
            }

        }

        // ERROR scenario
        else {

            actualResult = checkout.getErrorMessage();

            if (actualResult.isEmpty()) {

                actualResult = "Error message is not displayed";
            }
        }

        // Compare Expected Result with Actual Result
        Assert.assertEquals(
                actualResult,
                expectedResult
        );

        // Write PASS
        excel.writeResult(
                rowNo,
                actualResult,
                "PASS"
        );

    } catch (AssertionError e) {

        // Write FAIL
        excel.writeResult(
                rowNo,
                actualResult,
                "FAIL"
        );

        throw e;

    } finally {

        excel.closeExcel();
    }

    }

 
    // Verify cancel button
  

    @Test
    public void verifyCancelButton() {

        openCheckoutPage();

        checkout.clickCancel();

        Assert.assertTrue(
                driver.getCurrentUrl()
                     .contains("cart"),
                "User was not returned to Cart page"
        );

    }


    // Verify continue button

    @Test
    public void verifyContinueButton() {

        openCheckoutPage();

        checkout.enterFirstName("Reshma");

        checkout.enterLastName("Rajan");

        checkout.enterPostalCode("695001");

        checkout.clickContinue();

        Assert.assertTrue(
                checkout.isCheckoutOverviewDisplayed(),
                "Checkout Overview page is not displayed"
        );

    }

}