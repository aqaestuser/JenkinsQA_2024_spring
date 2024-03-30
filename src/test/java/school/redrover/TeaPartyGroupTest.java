package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TeaPartyGroupTest {

    @Test
    public void ivansTest() {

    }
    @Test
    public void testSprinkleSystemTatianaGlushak() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://project-atlas.dev/");
        WebElement button = driver.findElement(By.xpath("//a[@href = '/dashboard']"));
        button.click();

        WebElement text = driver.findElement(By.name("email"));
        text.sendKeys("userFirst@yopmail.com");

        text = driver.findElement(By.name("password"));
        text.sendKeys("User@1");

        button = driver.findElement(By.xpath("//button[@type='submit']"));
        button.click();

        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='status']")));
        String resultText = element.getText();
        Assert.assertEquals(resultText, "server_errors.UserNotFound");

        driver.quit();
    }
    @Test
    public void tatianaGlushakDemoTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement login = driver.findElement(By.id("user-name"));
        login.sendKeys("standard_user");
        WebElement loginPassword = driver.findElement(By.name("password"));
        loginPassword.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
        WebElement menu = driver.findElement(By.id("react-burger-menu-btn"));
        menu.click();
        WebElement allItems = driver.findElement(By.id("inventory_sidebar_link"));
        driver.quit();
        //String nMenu = menu.getText();
       // Assert.assertTrue(allItems.isDisplayed(), "Not there");


    }

  }


