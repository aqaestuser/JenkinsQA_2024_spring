package school.redrover;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.*;
import org.testng.annotations.*;
import school.redrover.model.FolderProjectPage;
import school.redrover.model.HomePage;
import school.redrover.runner.*;

import java.util.List;

public class Folder2Test extends BaseTest {

    private String folderName = "MyFolder";

    @Test
    public void testCreate() {
        getDriver().findElement(By.xpath("//*[text()='New Item']/ancestor::div[contains(@class,'task')]")).click();
        getDriver().findElement(By.xpath("//input[@id=\"name\"]")).sendKeys(folderName);
        getDriver().findElement(By.xpath("//*[text()='Folder']/ancestor::li")).click();
        getDriver().findElement(By.xpath("//*[@id=\"ok-button\"]")).click();
        getWait2().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@name=\"Submit\"]"))).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/h1")).getText(),
                folderName);
    }

    @Test(dependsOnMethods = "testCreate")
    public void testCreateFreestyleProjectInFolder(){
        final String FREESTYLE_NAME = "InternalFreestyleProject";

        FolderProjectPage folderProjectPage = new HomePage(getDriver())
                .clickFolder(folderName)
                .clickNewItemInsideFolder()
                .setItemName(FREESTYLE_NAME)
                .selectFreestyleAndClickOk()
                .clickLogo()
                .clickFolder(folderName);

        Assert.assertTrue(folderProjectPage.isItemExistsInsideFolder(FREESTYLE_NAME));
    }

    @Test(dependsOnMethods = "testCreateFreestyleProjectInFolder")
    public void testRenameFolder(){
        final String newFolderName = "NewProjectFolder";

        getDriver().findElement(By.xpath("//span[text()='" + folderName + "']")).click();
        getDriver().findElement(By.linkText("Rename")).click();

        WebElement renameInput = getDriver().findElement((By.name("newName")));
        renameInput.clear();
        renameInput.sendKeys(newFolderName);
        getDriver().findElement((By.name("Submit"))).click();

        getDriver().findElement(By.id("jenkins-head-icon")).click();

        folderName = newFolderName;
        final String actualFolderName = getDriver().findElement(By.xpath("//table//a[@href='job/NewProjectFolder/']")).getText();

        Assert.assertEquals(actualFolderName, newFolderName);
    }
}
