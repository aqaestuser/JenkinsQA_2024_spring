package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ArtComplexTest {
    @Test
    public void testMainTest() throws InterruptedException {


        WebDriver driver = new ChromeDriver();
        driver.get("https://art-complex.ru/brand/kl-acoustics/");
        Thread.sleep(3000);

        WebElement input = driver.findElement(By.id("mod_search_searchword"));
        input.sendKeys("INVOTONE H829");
        Thread.sleep(5000);

        WebElement search = driver.findElement(By.id("sr0"));
        search.click();

        WebElement buy = driver.findElement(By.id("tocart_3341"));
        buy.click();

        WebElement count = driver.findElement(By.id("count_3341"));
        count.sendKeys("2");

        WebElement text = driver.findElement(By.className("shop-cart-div-1"));
        String resultText = text.getText();

        Assert.assertEquals(resultText, "Минимальный заказ в нашем магазине 10 000 руб.");

        driver.quit();
    }
}
