package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class NewItem16Test extends BaseTest {

    @Test
    public void testCreateNewItemFP () {
        getDriver().findElement(By.xpath("//a[@href='newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys("TestItemFor");
        getDriver().findElement(By.xpath("//li[contains(@class, '_FreeStyleProject')]")).click();
        getDriver().findElement(By.id("ok-button")).click();

        //String configurationHeaderH1 = getDriver().findElement(By.xpath("//hi[@class='jenkins-app-bar__content']")).getText();
        String configurationHeaderH1 = getDriver().findElement(By.tagName("h1")).getText();
        Assert.assertEquals("Configure", configurationHeaderH1);
    }
}
