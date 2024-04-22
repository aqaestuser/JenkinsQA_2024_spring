package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class OrganizationFolder6Test extends BaseTest {

    @Test
    public void testCreateOrganization6Folder() {
        getDriver().findElement(By.xpath("//a[@it='hudson.model.Hudson@41cfd365']")).click();
        getDriver().findElement(By.id("name")).sendKeys("organization folder");
        getDriver().findElement(By.className("jenkins_branch_OrganizationFolder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.className("jenkins-breadcrumbs__list-item")).click();

        String actualCreatedFolder = getDriver().findElement(By.xpath(
                "//tr[@id='job_organization folder']//a[@class='jenkins-table__link model-link inside']")).getText();
        Assert.assertEquals(actualCreatedFolder, "organization folder");
    }
}

