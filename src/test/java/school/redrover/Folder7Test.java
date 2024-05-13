package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.DeleteDialog;
import school.redrover.model.HomePage;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

public class Folder7Test extends BaseTest {

    private static final String NAME = "19April";
    private static final String OLD_NAME = "Random Folder";
    private static final String NEW_NAME = "Renamed Folder";

    @Test
    public void testCreateNewFolder() {
        String name = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(NAME)
                .selectFolderAndClickOk()
                .clickSaveButton()
                .getPageHeading();

        Assert.assertEquals(name, NAME);
    }

    @Test
    public void testAddFolderDescription() {

        final String newText = "Hello";

        String description = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(NAME)
                .selectFolderAndClickOk()
                .clickSaveButton()
                .clickAddOrEditDescription()
                .setDescription(newText)
                .clickSaveButton()
                .getDescriptionText();

        Assert.assertEquals(description, newText);
    }

    @Test
    public void testRenameFolder() {

        List<String> folderList = new HomePage(getDriver())
                .createNewFolder(OLD_NAME)
                .clickFolder(OLD_NAME)
                .clickOnRenameButton()
                .setNewName(NEW_NAME)
                .clickRename()
                .clickLogo()
                .getItemList();

        Assert.assertListContainsObject(folderList, NEW_NAME, "Folder is not renamed!");

    }

    @Test(dependsOnMethods = "testCreateNewFolder")
    public void testCreateFreestyleProjectInsideFolder() {

        final String freeStyleProjectName = "23 april";

        String name = new HomePage(getDriver())
                .clickFolder(NAME)
                .clickNewItemInsideFolder()
                .setItemName(freeStyleProjectName)
                .selectFreestyleAndClickOk()
                .clickSaveButton()
                .getProjectName();

        Assert.assertEquals(name, freeStyleProjectName);
    }

    @Test(dependsOnMethods = {"testCreateNewFolder", "testCreateFreestyleProjectInsideFolder"})
    public void testDeleteFolderViaDropdown() {

        TestUtils.goToMainPage(getDriver());

        HomePage homePage = new HomePage(getDriver());

        homePage.openItemDropdown(NAME)
                .clickDeleteInDropdown(new DeleteDialog(getDriver()))
                .clickYes(homePage);

        Assert.assertTrue(homePage.isItemDeleted(NAME));
    }
}