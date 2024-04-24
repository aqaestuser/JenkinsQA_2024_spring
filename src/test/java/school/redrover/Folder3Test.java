package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class Folder3Test extends BaseTest {

    private static final String FOLDER_NAME_FIRST = "Folder_1";
    private static final String FOLDER_NAME_NEW = "Folder_1_New";
    private static final String FOLDER_DESCRIPTION_FIRST = "Some description of the folder.";


    public void clickSaveButton() {
        getDriver().findElement(By.xpath("//*[@name='Submit']")).click();
    }

    public void clickFolderName(String folderName) {
        getDriver().findElement(By.xpath("//td/a[@href='job/" + folderName + "/']")).click();
    }
    
    public boolean isFolderExists(String folderName) {
        return !getDriver().findElements(By.xpath("//*[@id='job_" + folderName + "']")).isEmpty();
    }

    @Test
    public void testCreate() {
        getDriver().findElement(By.xpath("//*[text()='New Item']/ancestor::div[contains(@class,'task')]")).click();
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys(FOLDER_NAME_FIRST);
        getDriver().findElement(By.xpath("//*[text()='Folder']/ancestor::li")).click();
        getDriver().findElement(By.xpath("//*[@id='ok-button']")).click();
        clickSaveButton();

        TestUtils.returnToDashBoard(this);

        Assert.assertTrue(isFolderExists(FOLDER_NAME_FIRST));
    }

    @Test (dependsOnMethods = "testCreate")
    public void testAddDescription() {
        clickFolderName(FOLDER_NAME_FIRST);
        getDriver().findElement(By.xpath("//*[@id='description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(FOLDER_DESCRIPTION_FIRST);
        clickSaveButton();

        String textInDescription = getDriver().findElement(By.xpath("//*[@id='description']/div")).getText();

        Assert.assertEquals(textInDescription, FOLDER_DESCRIPTION_FIRST);
    }

    @Test (dependsOnMethods = "testAddDescription")
    public void testRename() {

        clickFolderName(FOLDER_NAME_FIRST);
        getDriver().findElement(By.xpath("//*[text()='Rename']/parent::a")).click();

        getDriver().findElement(By.xpath("//input[@name='newName']")).clear();
        getDriver().findElement(By.xpath("//input[@name='newName']")).sendKeys(FOLDER_NAME_NEW);
        clickSaveButton();

        TestUtils.returnToDashBoard(this);

        boolean isFolderWithNewNameExists = isFolderExists(FOLDER_NAME_NEW);
        boolean isFolderWithOldNameNotExists = !isFolderExists(FOLDER_NAME_FIRST);

        Assert.assertTrue(isFolderWithNewNameExists && isFolderWithOldNameNotExists);
    }

}
