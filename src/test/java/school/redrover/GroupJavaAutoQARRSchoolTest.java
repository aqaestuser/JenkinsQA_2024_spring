package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;

public class GroupJavaAutoQARRSchoolTest extends BaseTest {

    @Test
    public void testReturnToHomePageFromInventoryDetailsCard() {

        WebDriver driver = getDriver();
        driver.get("https://www.saucedemo.com");

        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.name("login-button")).click();

        WebElement inventoryName = driver.findElement(By.xpath("//div[text() = 'Sauce Labs Bike Light']"));
        inventoryName.click();

        WebElement backToProductBtn = driver.findElement(By.xpath("//button[@id = 'back-to-products']"));
        backToProductBtn.click();

        WebElement headerProductsOnTheHomePage = driver.findElement(By.xpath("//span[text() = 'Products']"));
        String value = headerProductsOnTheHomePage.getText();

        Assert.assertEquals(value, "Products");
    }
    @Test
    public void testDemoQAPracticeForm(){
        getDriver().get("https://demoqa.com/automation-practice-form");

        WebElement firstName = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='firstName']"))));
        firstName.sendKeys("Bob");

        getDriver().findElement(By.xpath("//input[@id='lastName']")).sendKeys("Boboc");

        getDriver().findElement(By.xpath("//input[@id='userEmail']")).sendKeys("test@gmail.com");

        getDriver().findElement(By.xpath("//input[@id='userNumber']")).sendKeys("1234567890");

        getDriver().findElement(By.xpath("//input[@id='gender-radio-1']/following-sibling::label['::after']")).click();

        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(0, 1600)");
        getDriver().findElement(By.id("submit")).click();

        WebElement title = (new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("example-modal-sizes-title-lg"))));
        String value = title.getText();
        Assert.assertEquals(value, "Thanks for submitting the form");
    }
}
