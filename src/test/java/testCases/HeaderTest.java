package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HeaderPage;
import pageObjects.LoginPage;

public class HeaderTest extends BaseTest {

    @Test
    public void verifyHeader() {

        LoginPage login = new LoginPage(driver);

        login.login("standard_user", "secret_sauce");

        HeaderPage header = new HeaderPage(driver);

        Assert.assertTrue(header.isHeaderDisplayed());

        Assert.assertEquals(header.getHeaderText(), "Swag Labs");
        
        System.out.println(header.getHeaderText());
    }
}
