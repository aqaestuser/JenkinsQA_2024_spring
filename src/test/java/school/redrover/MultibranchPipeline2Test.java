package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;


public class MultibranchPipeline2Test extends BaseTest {
    @Test
    public void testDisablingAndEnablingMultibranchPipeline() {

        getDriver().findElement(By.xpath("//span[.='Create a job']")).click();
        getDriver().findElement(By.className("jenkins-input")).sendKeys("TextName");
        getDriver().findElement(By.xpath("//span[.='Multibranch Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.xpath("//label[@data-title='Disabled']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        WebElement titleText = getDriver().findElement(By.id("enable-project"));

        Assert.assertEquals(titleText.getText(),"This Multibranch Pipeline is currently disabled \n" +
                "Enable");

        getDriver().findElement(By.xpath("//span[.='Configure the project']")).click();
        getDriver().findElement(By.xpath("//label[@data-title='Disabled']")).click();
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        WebElement disabledButton = getDriver().findElement(By.xpath("//button[@formnovalidate='formNoValidate']"));

        Assert.assertTrue(disabledButton.isEnabled());
    }
}
