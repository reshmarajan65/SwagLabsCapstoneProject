package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FooterPage {



    WebDriver driver;


    public FooterPage(WebDriver driver) {

        this.driver = driver;

    }


    // Locators

    By twitter = By.className("social_twitter");

    By facebook = By.className("social_facebook");

    By linkedin = By.className("social_linkedin");



    // Visibility methods

    public boolean isTwitterDisplayed() {

        return driver.findElement(twitter).isDisplayed();

    }


    public boolean isFacebookDisplayed() {

        return driver.findElement(facebook).isDisplayed();

    }


    public boolean isLinkedinDisplayed() {

        return driver.findElement(linkedin).isDisplayed();

    }



    // Click methods


    public void clickTwitter() {

        driver.findElement(twitter).click();

    }


    public void clickFacebook() {

        driver.findElement(facebook).click();

    }


    public void clickLinkedin() {

        driver.findElement(linkedin).click();

    }
}