package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItem6Test extends BaseTest {

    @Test
    public void testCreateNewFolder(){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        getDriver().findElement(By.id("name")).sendKeys("My folder");

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Creates a container')]")));

        getDriver().findElement(By.xpath("//div[contains(text(),'Creates a container')]")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='Submit']")));

        getDriver().findElement(By.xpath("//*[@name='Submit']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']/h1")));

        String actualFolderName = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();

        Assert.assertEquals(actualFolderName, "My folder");
    }

    @Test(dependsOnMethods = "testCreateNewFolder")
    public void testNewFolderOnDashboard(){
        getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'My folder')]")));

        String actualNewFolderName = getDriver().findElement(By.xpath("//span[contains(text(),'My folder')]")).getText();

        Assert.assertEquals(actualNewFolderName,"My folder" );
 }

    @Test(dependsOnMethods = "testNewFolderOnDashboard")
    public void testRenameFolder(){
        WebElement actualNewFolderName = getDriver().findElement(By.xpath("//span[contains(text(),'My folder')]"));

        Actions actions = new Actions(getDriver());
        actions.moveToElement(actualNewFolderName).perform();

        getDriver().findElement(By.xpath("(//button[@class='jenkins-menu-dropdown-chevron'])[3]")).click();

        getDriver().findElement(By.xpath("//a[@href='/job/My%20folder/confirm-rename']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        getDriver().findElement(By.name("newName")).clear();
        getDriver().findElement(By.name("newName")).sendKeys("Your folder");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        String actualFolderNewName = getDriver().findElement(By.cssSelector("h1")).getText();

        Assert.assertEquals(actualFolderNewName,"Your folder");
    }
}