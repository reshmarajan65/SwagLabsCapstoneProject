package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }


    // Checkout:Your Information

    By txtFirstName = By.id("first-name");

    By txtLastName = By.id("last-name");

    By txtPostalCode = By.id("postal-code");

    By btnContinue = By.id("continue");

    By btnCancel = By.id("cancel");

    By txtError = By.cssSelector("[data-test='error']");

    // Checkout:Overview

    //By checkoutOverviewTitle =
            //By.xpath("//span[text()='Checkout: Overview']");
    By checkoutOverviewTitle =
            By.className("title");

    By itemName =
            By.className("inventory_item_name");

    By itemPrice =
            By.className("inventory_item_price");

    By itemQuantity =
            By.className("cart_quantity");


    By subtotal =
            By.className("summary_subtotal_label");

    By tax =
            By.className("summary_tax_label");

    By total =
            By.className("summary_total_label");

    By btnFinish = By.id("finish");


    // Checkout:Complete
    
    By checkoutCompleteTitle =
            By.xpath("//span[text()='Checkout: Complete!']");

    By completeMessage =
            By.className("complete-header");

    By btnBackHome =
            By.id("back-to-products");


    // checkout information page

    // Enter First Name
    public void enterFirstName(String firstName) {

        driver.findElement(txtFirstName).clear();

        if (firstName != null && !firstName.isEmpty()) {
            driver.findElement(txtFirstName).sendKeys(firstName);
        }
    }



    // Enter Last Name
    public void enterLastName(String lastName) {

        driver.findElement(txtLastName).clear();

        if (lastName != null && !lastName.isEmpty()) {
            driver.findElement(txtLastName).sendKeys(lastName);
        }
    }


    // Enter Postal Code
    public void enterPostalCode(String postalCode) {

        driver.findElement(txtPostalCode).clear();

        if (postalCode != null && !postalCode.isEmpty()) {
            driver.findElement(txtPostalCode).sendKeys(postalCode);
        }
    }

    // Click Continue
    public void clickContinue() {

        driver.findElement(btnContinue).click();
    }


    // Click Cancel
    public void clickCancel() {

        driver.findElement(btnCancel).click();
    }


    // Get Error Message
  /*  public String getErrorMessage() {

        if (driver.findElements(By.cssSelector("[data-test='error']")).size() > 0) {

            return driver.findElement(
                    By.cssSelector("[data-test='error']")
            ).getText();

        }

        return "";
    }*/
    public String getErrorMessage() {

        if (driver.findElements(txtError).size() > 0) {

            return driver.findElement(txtError).getText();

        }

        return "";
    }


    // Verify Error Message Displayed
    public boolean isErrorMessageDisplayed() {

        return driver.findElement(txtError).isDisplayed();
    }


   // checkout overview page

    // Verify Checkout Overview page

    public boolean isCheckoutOverviewDisplayed() {

        return driver.findElement(checkoutOverviewTitle)
                     .isDisplayed();
    }


    // Get Product Name
    public String getItemName() {

        return driver.findElement(itemName).getText();
    }


    // Get Product Price
    public String getItemPrice() {

        return driver.findElement(itemPrice).getText();
    }


    // Get Product Quantity
    public String getItemQuantity() {

        return driver.findElement(itemQuantity).getText();
    }


    // Get Subtotal
    public String getSubtotal() {

        return driver.findElement(subtotal).getText();
    }


    // Get Tax
    public String getTax() {

        return driver.findElement(tax).getText();
    }


    // Get Total
    public String getTotal() {

        return driver.findElement(total).getText();
    }


    // Click Finish
    public void clickFinish() {

        driver.findElement(btnFinish).click();
    }


    // checkout complete page

    // Verify Checkout Complete page
    public boolean isCheckoutCompleteDisplayed() {

        return driver.findElement(checkoutCompleteTitle)
                     .isDisplayed();
    }


    // Get Order Confirmation Message
    public String getCompleteMessage() {

        return driver.findElement(completeMessage)
                     .getText();
    }


    // Click Back Home
    public void clickBackHome() {

        driver.findElement(btnBackHome).click();
    }

}