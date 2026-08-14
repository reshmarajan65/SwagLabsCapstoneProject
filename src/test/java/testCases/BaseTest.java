package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));  
    }
    // Reusable method to switch to new tab/window
    public void switchToNewWindow() {

        String currentWindow = driver.getWindowHandle();

        for(String window : driver.getWindowHandles()) {

            if(!window.equals(currentWindow)) {

                driver.switchTo().window(window);
                break;
            }
        }
    }
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

}
