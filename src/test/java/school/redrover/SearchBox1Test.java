package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class SearchBox1Test extends BaseTest {

    private WebElement checkbox() {
        return getDriver().findElement(By.xpath("//div[2]/span/input"));
    }

    private Actions getActions() {
        return new Actions(getDriver());
    }

    private void goToConfigure() {
        getDriver().findElement(By.xpath("//div[3]/a[1]")).click();
        getDriver().findElement(By.cssSelector("[href$='configure']")).click();
    }

    private void checkboxClick() {
        getActions().scrollToElement(getDriver().findElement(By.xpath("//div[3]/div[2]/span")))
                .sendKeys(Keys.TAB, Keys.SPACE)
                .perform();
        getDriver().findElement(By.cssSelector("[class$='primary ']")).click();
    }

    private void turnOffInsensitiveSearch() {
        goToConfigure();
        if(checkbox().isSelected()) {
            checkboxClick();
        }
    }

    private void turnOnInsensitiveSearch() {
        goToConfigure();
        if(!checkbox().isSelected()) {
            checkboxClick();
        }
    }

    @Test
    public void testCaseSensitiveOnUppercaseInput() {
        turnOffInsensitiveSearch();

        getDriver().findElement(By.id("search-box")).sendKeys("Log");
        getDriver().findElement(By.id("search-box")).sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().findElement(By.cssSelector("[class='error']")).getText(),
                "Nothing seems to match.");
    }

    @Test
    public void testCaseSensitiveOnLowercaseInput() {
        turnOffInsensitiveSearch();

        getDriver().findElement(By.id("search-box")).sendKeys("log");
        getDriver().findElement(By.id("search-box")).sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().findElement(By.cssSelector("[class$='__content']")).getText(),
                "Log Recorders");
    }

    @Test
    public void testCaseSensitiveOffLowercaseInput() {
        turnOnInsensitiveSearch();

        getDriver().findElement(By.id("search-box")).sendKeys("log");
        getDriver().findElement(By.id("search-box")).sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().findElement(By.cssSelector("[class$='__content']")).getText(),
                "Log Recorders");
    }

    @Test
    public void testCaseSensitiveOffUppercaseInput() {
        turnOnInsensitiveSearch();

        getDriver().findElement(By.id("search-box")).sendKeys("loG");
        getDriver().findElement(By.id("search-box")).sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().findElement(By.cssSelector("[class$='__content']")).getText(),
                "Log Recorders");
    }
}


