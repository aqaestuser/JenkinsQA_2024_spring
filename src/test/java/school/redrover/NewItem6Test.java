package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItem6Test extends BaseTest {
     String folderName = "MyFolder";
     String newFolderName = "Your name";

    @Test
    public void testCreateNewFolder(){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));

        getDriver().findElement(By.id("name")).sendKeys(folderName);

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Creates a container')]")));

        getDriver().findElement(By.xpath("//div[contains(text(),'Creates a container')]")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='Submit']")));

        getDriver().findElement(By.xpath("//*[@name='Submit']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-panel']/h1")));

        String actualFolderName = getDriver().findElement(By.xpath("//div[@id='main-panel']/h1")).getText();

        Assert.assertEquals(actualFolderName, folderName);
    }

    @Test(dependsOnMethods = "testCreateNewFolder")
    public void testNewFolderOnDashboard(){
        getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='jenkins-table__link model-link inside']")));

        String actualNewFolderName = getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']")).getText();

        Assert.assertEquals(actualNewFolderName,folderName );
    }

    @Test(dependsOnMethods = "testNewFolderOnDashboard")
    public void testRenameFolder(){
        getDriver().findElement(By.xpath("//a[@class='jenkins-table__link model-link inside']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='task-link-wrapper '])[7]")));

        getDriver().findElement(By.xpath("(//span[@class='task-link-wrapper '])[7]")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("newName")));

        getDriver().findElement(By.name("newName")).clear();

        getDriver().findElement(By.name("newName")).sendKeys(newFolderName);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        String actualFolderNewName = getDriver().findElement(By.cssSelector("h1")).getText();

        Assert.assertEquals(actualFolderNewName,newFolderName);
    }
}