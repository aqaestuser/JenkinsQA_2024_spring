package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class OrganizationFolder1Test extends BaseTest {

    @Test
    public void testCreateOrganizationFolder() {
        getDriver().findElement(By.xpath("//div[@id='tasks']//div[1]//span[1]//a[1]")).click();
        getDriver().findElement(By.id("name")).sendKeys("organization folder");
        getDriver().findElement(By.className("jenkins_branch_OrganizationFolder")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.className("jenkins-breadcrumbs__list-item")).click();

        String actualCreatedFolder = getDriver().findElement(
                By.xpath("//span[contains(text(), 'organization folder')]")).getText();
        Assert.assertEquals(actualCreatedFolder, "organization folder");
    }
}

