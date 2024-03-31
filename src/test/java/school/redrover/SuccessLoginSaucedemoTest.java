package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SuccessLoginSaucedemoTest {
    @Test
    public void testSuccessLoginSaucedemo(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginButton =  driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement logoText = driver.findElement(By.xpath("//div[@class='app_logo']"));

        Assert.assertEquals(logoText.getText(), "Swag Labs");

        driver.quit();
    }
}