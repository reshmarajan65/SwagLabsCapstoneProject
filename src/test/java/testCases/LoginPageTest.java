package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import utils.ExcelUtility;

public class LoginPageTest extends BaseTest {

    LoginPage login;

    @BeforeMethod
    public void initializePage() {

        login = new LoginPage(driver);

    }

  /*  @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {

        ExcelUtility excel = new ExcelUtility();

        Object[][] data = excel.getLoginData();

        excel.closeExcel();

        return data;

    }*/
    
    @DataProvider(name="loginData")
    public Object[][] loginData() throws IOException {


        ExcelUtility excel =
            new ExcelUtility("LoginData.xlsx","LoginData");


        Object[][] data =
            excel.getExcelData(4);


        excel.closeExcel();


        return data;

    }

    @Test(dataProvider = "loginData")
    public void verifyLogin(int rowNo,
                            String username,
                            String password,
                            String expectedResult) throws IOException {

        ExcelUtility excel =  new ExcelUtility("LoginData.xlsx", "LoginData");

        login.login(username, password);

        String actualResult;

        try {

            if (expectedResult.equalsIgnoreCase("SUCCESS")) {

                Assert.assertEquals(driver.getCurrentUrl(),
                        "https://www.saucedemo.com/inventory.html");

                actualResult = "SUCCESS";

            } else {

                actualResult = login.getErrorMessage();

                Assert.assertEquals(actualResult, expectedResult);

            }

            excel.writeResult(rowNo, actualResult, "PASS");

        } catch (AssertionError e) {

            if (expectedResult.equalsIgnoreCase("SUCCESS")) {
                actualResult = driver.getCurrentUrl();
            } else {
                actualResult = login.getErrorMessage();
            }

            excel.writeResult(rowNo, actualResult, "FAIL");

            throw e;

        } finally {

            excel.closeExcel();

        }
    }

    @Test
    public void verifyLogoDisplayed() {

        Assert.assertTrue(login.isLogoDisplayed());

    }

    @Test
    public void verifyUsernamePlaceholder() {

        Assert.assertEquals(login.getUsernamePlaceholder(), "Username");

    }

    @Test
    public void verifyPasswordPlaceholder() {

        Assert.assertEquals(login.getPasswordPlaceholder(), "Password");

    }

    @Test
    public void verifyLoginButtonEnabled() {

        Assert.assertTrue(login.isLoginButtonEnabled());

    }

    @Test
    public void verifyLoginButtonText() {

        Assert.assertEquals(login.getLoginButtonText(), "Login");

    }
    @Test
    public void verifyPasswordMasking() {

        login.enterPassword("secret_sauce");

        Assert.assertEquals(login.getPasswordFieldType(), "password");

    }
    @Test
    public void verifyUsernameFieldAcceptsInput() {

        String username = "standard_user";

        login.enterUsername(username);

        Assert.assertEquals(login.getEnteredUsername(), username);

    }
    @Test
    public void verifyPasswordFieldAcceptsInput() {

        String password = "secret_sauce";

        login.enterPassword(password);

        Assert.assertEquals(login.getEnteredPassword(), password);

    }
    @Test
    public void verifyErrorMessageVisibility() {

        login.login("invalid_user", "wrong123");

        Assert.assertTrue(login.isErrorMessageDisplayed());

    }

}