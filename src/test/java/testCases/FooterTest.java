package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.FooterPage;
import pageObjects.LoginPage;

public class FooterTest extends BaseTest {



    @Test
    public void verifyTwitterLink() {


        LoginPage login = new LoginPage(driver);

        login.login("standard_user", "secret_sauce");


        FooterPage footer = new FooterPage(driver);



        // Verify Twitter icon visible

        Assert.assertTrue(footer.isTwitterDisplayed());



        // Click Twitter

        footer.clickTwitter();



        // Switch to new tab

        switchToNewWindow();



        // Verify URL

        String url = driver.getCurrentUrl();

        System.out.println(url);


        Assert.assertTrue(url.contains("x.com") || url.contains("twitter"));

    }




    @Test
    public void verifyFacebookLink() {


        LoginPage login = new LoginPage(driver);

        login.login("standard_user", "secret_sauce");


        FooterPage footer = new FooterPage(driver);



        // Verify Facebook icon visible

        Assert.assertTrue(footer.isFacebookDisplayed());



        // Click Facebook

        footer.clickFacebook();



        // Switch to new tab

        switchToNewWindow();



        // Verify URL

        String url = driver.getCurrentUrl();

        System.out.println(url);


        Assert.assertTrue(url.contains("facebook"));

    }





    @Test
    public void verifyLinkedinLink() {


        LoginPage login = new LoginPage(driver);

        login.login("standard_user", "secret_sauce");


        FooterPage footer = new FooterPage(driver);



        // Verify LinkedIn icon visible

        Assert.assertTrue(footer.isLinkedinDisplayed());



        // Click LinkedIn

        footer.clickLinkedin();



        // Switch to new tab

        switchToNewWindow();



        // Verify URL

        String url = driver.getCurrentUrl();

        System.out.println(url);


        Assert.assertTrue(url.contains("linkedin"));

    }
}