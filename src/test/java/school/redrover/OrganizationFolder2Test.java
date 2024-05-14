package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.model.FolderProjectPage;
import school.redrover.model.HomePage;
import school.redrover.model.OrganizationFolderProjectPage;
import school.redrover.runner.BaseTest;

import java.util.List;

public class OrganizationFolder2Test extends BaseTest{

    private static final String ORGANIZATION_FOLDER_DESCRIPTION = "Some description of the organization folder.";

    @Test
    public void testCreateOrganizationFolder() {
        final String organizationFolderName = "New Organization Folder";

        List<String> itemList = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(organizationFolderName)
                .selectOrganizationFolderAndClickOk()
                .clickSaveButton()
                .clickLogo()
                .getItemList();

        Assert.assertTrue(itemList.contains(organizationFolderName));
    }

    @Test(dependsOnMethods = "testCreateOrganizationFolder")
    public void testRenameOrganizationFolder() {
        getDriver().findElement(By.xpath("//span[.='New Organization Folder']")).click();
        getDriver().findElement(By.xpath("//a[contains(.,'Rename')]")).click();
        getDriver().findElement(By.xpath("//input[@name='newName']")).clear();
        getDriver().findElement(By.xpath("//input[@name='newName']")).sendKeys(
                "Organization Folder");
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(By.xpath("//a[.='Dashboard']")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath(
                "//span[.='Organization Folder']")).isDisplayed());
    }

    @Test(dependsOnMethods = {"testCreateOrganizationFolder", "testRenameOrganizationFolder"})
    public void testAddDescription(){

        String textInDescription = new OrganizationFolderProjectPage(getDriver())
                .clickAddOrEditDescription()
                .setDescription(ORGANIZATION_FOLDER_DESCRIPTION)
                .clickSaveButton()
                .getDescriptionText();

        Assert.assertEquals(textInDescription, ORGANIZATION_FOLDER_DESCRIPTION);
    }

    @Ignore
    @Test(dependsOnMethods = "testOrganizationFolderAddDescription")
    public void testDeleteOrganizationFolder() {

        getDriver().findElement(By.xpath("//span[.='Organization Folder']")).click();

        getDriver().findElement(By.xpath("//a[@data-title='Delete Organization Folder']")).click();

        getDriver().findElement(By.xpath("//button[@data-id='ok']")).click();

        List<WebElement> jobList = getDriver().findElements(
                By.xpath("//span[.='Organization Folder']"));

        Assert.assertTrue(jobList.isEmpty());
    }
}
