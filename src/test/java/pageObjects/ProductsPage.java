package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class ProductsPage {

    WebDriver driver;


    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }


    // Locators

    By productNames = By.className("inventory_item_name");

    By productPrices = By.className("inventory_item_price");

    By sortDropdown = By.className("product_sort_container");

    By cartBadge = By.className("shopping_cart_badge");
    
    By pageTitle = By.className("title");

    By productCards = By.className("inventory_item");

    By productImages = By.className("inventory_item_img");

    By productDescription = By.className("inventory_item_desc");

    By productLinks = By.className("inventory_item_name");

    By cartIcon = By.className("shopping_cart_link");

    By addToCartButtons = By.xpath("//button[contains(text(),'Add to cart')]");

    By menuButton = By.id("react-burger-menu-btn");

    By logoutLink = By.id("logout_sidebar_link");

    By footerLinks = By.className("social");

    By productImage = By.className("inventory_item_img");
    
    // Verify project page heading
    public String getPageTitle() {

        return driver.findElement(pageTitle).getText();

    }
    
    // verify product details displayed
    public int getProductDetailsCount(){

        return driver.findElements(productDescription).size();

    }
    
    public int getProductImageCount() {

        return driver.findElements(
                By.cssSelector("img.inventory_item_img")
        ).size();

    }
    //verify product images displayed 
    public boolean areProductImagesDisplayed() {

        List<WebElement> images = driver.findElements(productImages);

        if (images.size() != 6) {
            System.out.println("Images found: " + images.size());
            return false;
        }

        for (WebElement image : images) {

            System.out.println(image.getAttribute("alt") + " : " + image.isDisplayed());

            if (!image.isDisplayed()) {
                return false;
            }
        }

        return true;
    }
    
    //verify cart icon id displayed 
    public boolean isCartIconDisplayed(){

        return driver.findElement(cartIcon).isDisplayed();

    }
    
    //verify add to cart displayed 
    public int getAddToCartButtonCount(){

        return driver.findElements(addToCartButtons).size();

    }
    
    //click cart
    public void clickCart(){

        driver.findElement(cartIcon).click();

    }
    
    //product name navigation
    public void clickProductName(String productName){

    	String xpath =
    	"//div[text()='"+productName+"']";

    	driver.findElement(By.xpath(xpath)).click();

    	}
    
    //product image navigation
    public void clickProductImage(String productName){

    	String xpath =
    	"//div[text()='"+productName+"']/ancestor::div[@class='inventory_item']//img";

    	driver.findElement(By.xpath(xpath)).click();

    	}
    
    //logout
    public void logout(){

    	driver.findElement(menuButton).click();

    	driver.findElement(logoutLink).click();

    	}
    
    // Add product to cart

    public void addProduct(String productName) {

        String xpath =
                "//div[text()='" + productName
                + "']/ancestor::div[@class='inventory_item']//button";


        driver.findElement(By.xpath(xpath)).click();

    }



    // Remove product from cart

    public void removeProduct(String productName) {

        String xpath =
                "//div[text()='" + productName
                + "']/ancestor::div[@class='inventory_item']//button";


        driver.findElement(By.xpath(xpath)).click();

    }



    // Select sorting option

    public void selectSorting(String option) {

        Select select =
                new Select(driver.findElement(sortDropdown));


        select.selectByVisibleText(option);

    }



    // Get all product names

    public List<String> getAllProductNames() {

        List<String> names = new ArrayList<>();


        List<WebElement> products =
                driver.findElements(productNames);


        for(WebElement product : products) {

            names.add(product.getText());

        }


        return names;

    }



    // Get all product prices

    public List<Double> getAllProductPrices() {

        List<Double> prices = new ArrayList<>();


        List<WebElement> products =
                driver.findElements(productPrices);


        for(WebElement product : products) {

            String price =
                    product.getText().replace("$", "");


            prices.add(Double.parseDouble(price));

        }


        return prices;

    }



    // Get cart count

    public int getCartCount() {

        List<WebElement> badge =
                driver.findElements(cartBadge);


        if(badge.size() > 0) {

            return Integer.parseInt(
                    badge.get(0).getText()
            );

        }
        else {

            return 0;

        }

    }



    // Verify product displayed

    public boolean isProductDisplayed(String productName) {


        String xpath =
                "//div[text()='" + productName + "']";


        return driver.findElement(By.xpath(xpath))
                .isDisplayed();

    }



    // Verify number of products

    public int getProductCount() {

        return driver.findElements(productNames).size();

    }
    

}