import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class ShoppingCartTest {
    @Test
    public void checkDiscount0() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://sharelane.com/cgi-bin/register.py?page=2&zip_code=12345&first_name=sda&" +
                "last_name=d2das&email=54535543543%40yt.com&password1=34234a&password2=34234a");
        String email = driver.findElement(By.xpath("/html/body/center/table/tbody/" +
                "tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
        driver.get("https://sharelane.com/cgi-bin/main.py");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("1111");
        driver.findElement(By.cssSelector("[value=Login]")).click();
        driver.get("https://sharelane.com/cgi-bin/add_to_cart.py?book_id=4");
        Thread.sleep(5000);
        driver.get("https://sharelane.com/cgi-bin/shopping_cart.py");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("19");
        driver.findElement(By.cssSelector("[value=Update]")).click();
        Thread.sleep(5000);
        String discountUSD = driver.findElement(By.xpath("//td[contains(text(),'Discount')]/following-sibling::td")).getText();
        String totalUSD = driver.findElement(By.xpath("//table/" +
                "tbody/tr[5]/td/table/tbody/tr[2]/td[7]")).getText();
        String discountPercent = driver.findElement(By.xpath(
                "//table/tbody/tr[5]/td/table/tbody/tr[2]/td[5]/p/b")).getText();
        softAssert.assertEquals(discountPercent, "6");
        softAssert.assertEquals(discountUSD, "600");
        softAssert.assertEquals(totalUSD, "10600");
        softAssert.assertAll();
        driver.quit();
    }
}
