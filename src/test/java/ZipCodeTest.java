import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ZipCodeTest {

    @Test
    public void checkZipCode4Digis() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("https://sharelane.com/cgi-bin/register.py");
        // <input type="text" name="zip_code" value="">
        browser.findElement(By.name("zip_code")).sendKeys("1234");
        //<input type="submit" value="Continue">
        browser.findElement(By.cssSelector("[value=Continue]")).click();
        //<span class="error_message">Oops, error on page. ZIP code should have 5 digits</span>
        String actualErrorMessage = browser.findElement(By.className("error_message")).getText();
        Assert.assertEquals(actualErrorMessage,"Oops, error on page. ZIP code should have 5 digits");
        browser.quit();
    }

    @Test
    public void checkZipCodeEmpty() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.get("https://sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("");
        browser.findElement(By.cssSelector("[value=Continue]")).click();
        String actualErrorMessage = browser.findElement(By.className("error_message")).getText();
        Assert.assertEquals(actualErrorMessage,"Oops, error on page. ZIP code should have 5 digits");
        browser.quit();
    }
}
