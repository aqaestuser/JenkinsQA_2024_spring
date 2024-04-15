package school.redrover;

import org.openqa.selenium.By;
<<<<<<< HEAD
=======
import org.openqa.selenium.WebElement;
>>>>>>> main
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItem4Test extends BaseTest {
<<<<<<< HEAD
    @Test
    public void testCreateNewFolder(){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("My folder");
        getDriver().findElement(By.xpath("//div[contains(text(),'Creates a container')]")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.xpath("//*[@name='Submit']")).click();

        String actualFolderName = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();

        Assert.assertEquals(actualFolderName, "My folder");
    }
}
=======

    private static final By BUTTON_NEW_ITEM = By.linkText("New Item");

    @Test
    public void testGoToCreateItemPage() {
        getDriver().findElement(BUTTON_NEW_ITEM).click();
        WebElement title = getDriver().findElement(By.xpath("//*[text()='Enter an item name']"));

        Assert.assertTrue(title.isDisplayed());
    }
}

>>>>>>> main
