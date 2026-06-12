import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SignUpTest {

    /*
    1. Ввести валидное значение zip code
    2. Проверить, что мы оказались на странице с формой регистрации
    3. Заполнить форму регистрации
    4. Нажать кропнку "Register"
    5. Проверить, что регистрация выполнена успешно
     */

    @Test
    public void checkSingUpValidData(){
        WebDriver browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        browser.get("https://sharelane.com/cgi-bin/register.py");
        browser.findElement(By.name("zip_code")).sendKeys("12345");
        browser.findElement(By.cssSelector("[value=Continue]")).click();
        String actualErrorMessage = browser.findElement(By.name("first_name")).getText();
        Assert.assertEquals(actualErrorMessage, "");
        browser.findElement(By.name("first_name")).sendKeys("Adam");
        browser.findElement(By.name("last_name")).sendKeys("Profi");
        browser.findElement(By.name("email")).sendKeys("5453543543@yt.com");
        browser.findElement(By.name("password1")).sendKeys("1234a");
        browser.findElement(By.name("password2")).sendKeys("1234a");
        browser.findElement(By.cssSelector("[value=Register]")).click();
        actualErrorMessage = browser.findElement(By.className("confirmation_message")).getText();
        Assert.assertEquals(actualErrorMessage,"Account is created!");
        browser.quit();
    }
}
