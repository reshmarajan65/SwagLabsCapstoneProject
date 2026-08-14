package testCases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.ProductsPage;
import utils.ExcelUtility;



public class ProductsTest extends BaseTest {


    LoginPage login;

    ProductsPage products;



    @BeforeMethod
    public void initializePage() {


        login = new LoginPage(driver);

        products = new ProductsPage(driver);


        // Login before every product test

        login.login(
                "standard_user",
                "secret_sauce"
        );

    }



    @DataProvider(name="productData")
    public Object[][] productData() throws IOException {


        ExcelUtility excel =
                new ExcelUtility(
                        "ProductData.xlsx",
                        "ProductData"
                );


        Object[][] data =
                excel.getExcelData(3);


        excel.closeExcel();


        return data;

    }




    @Test(dataProvider="productData")
    public void verifyAddProductToCart(
            int rowNo,
            String productName,
            String expectedResult) {


        products.addProduct(productName);



        Assert.assertTrue(
                products.getCartCount() > 0,
                "Product was not added to cart"
        );


    }




    @Test
    public void verifyAllProductsDisplayed() {


        Assert.assertEquals(
                products.getProductCount(),
                6,
                "All products are not displayed"
        );

    }




    @DataProvider(name="sortingData")
    public Object[][] sortingData() throws IOException {


        ExcelUtility excel =
                new ExcelUtility(
                        "SortData.xlsx",
                        "SortingData"
                );


        Object[][] data =
                excel.getExcelData(3);


        excel.closeExcel();


        return data;

    }




    @Test(dataProvider="sortingData")
    public void verifyProductSorting(
            int rowNo,
            String sortingOption,
            String expectedResult) {


        products.selectSorting(sortingOption);



        Assert.assertEquals(
                products.getProductCount(),
                6,
                "Sorting failed"
        );


    }
    
    @Test
    public void verifyProductsPageDisplayed() {

        Assert.assertEquals(
                products.getPageTitle(),
                "Products",
                "Products page is not displayed"
        );

    }

    @Test
    public void verifyProductsURL() {

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "Incorrect Products page URL"
        );

    }
    
    @Test
    public void verifyProductDetailsDisplayed() {

        Assert.assertEquals(
                products.getProductDetailsCount(),
                6,
                "Product details are not displayed"
        );

    }
    
    @Test
    public void verifyCartIconDisplayed() {

        Assert.assertTrue(
                products.isCartIconDisplayed(),
                "Shopping cart icon is not displayed"
        );

    }
    
    @Test
    public void verifyAddToCartButtonsDisplayed() {

        Assert.assertEquals(
                products.getAddToCartButtonCount(),
                6,
                "Add to Cart buttons are not displayed"
        );

    }
    
    @Test
    public void verifyProductImagesDisplayed() {

        Assert.assertEquals(
                products.getProductImageCount(),
                6,
                "Product images are not displayed correctly"
        );

    }
    
    @Test
    public void verifyNavigationToCartPage() {

        products.clickCart();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/cart.html",
                "Navigation to Cart page failed"
        );

    }
    
    @Test
    public void verifyProductNameNavigation() {

        products.clickProductName("Sauce Labs Backpack");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory-item"),
                "Product details page did not open"
        );

    }
}