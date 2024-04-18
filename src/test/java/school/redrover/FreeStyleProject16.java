package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreeStyleProject16 extends BaseTest {

    @Test
    public void freeStyleProjectExists() {
        String projectName = "New FreeStyle Project";
        WebElement newItemButton = getDriver().findElement(By.xpath("//*[@href='/view/all/newJob']"));
        newItemButton.click();
        WebElement newProjectInputField = getDriver().findElement(By.id("name"));
        newProjectInputField.sendKeys(projectName);
        WebElement freeStyleProjectSelector = getDriver().findElement(By.xpath("//*[@class=\"hudson_model_FreeStyleProject\"]"));
        freeStyleProjectSelector.click();
        WebElement okButton = getDriver().findElement(By.xpath("//*[@type=\"submit\"]"));
        okButton.click();
        WebElement saveButton = getDriver().findElement(By.xpath("//button[contains(@class, 'jenkins-button--primary')]"));
        saveButton.click();
        WebElement returnToDashboad = getDriver().findElement(By.xpath("//a[text()='Dashboard']"));
        returnToDashboad.click();
        Assert.assertEquals(getDriver().findElement(By.xpath("//span[text()='" + projectName + "']")).getText(), projectName);
    }
}
