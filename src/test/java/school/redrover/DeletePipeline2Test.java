package school.redrover;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static school.redrover.runner.TestUtils.returnToDashBoard;

public class DeletePipeline2Test extends BaseTest {
    final String NAME_PIPELINE = "Pipeline1";


    @Test
    public void createPipeline() {
        getDriver().findElement(By.linkText("New Item")).click();
        WebElement field = getDriver().findElement(By.className("jenkins-input"));
        field.sendKeys(NAME_PIPELINE);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.id("ok-button")).click();

        WebElement descriptionField = getWait10().until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));
        descriptionField.sendKeys("Наш новый пайплайн");
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();
    }


    @Test(dependsOnMethods = "createPipeline")
    public void testDeletePipeline() {

        WebElement namePipeline = getDriver().findElement(By.xpath("//a[@href='job/" + NAME_PIPELINE + "/']"));
        namePipeline.click();
        getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[5]/span/a")).click();

        Assert.assertEquals("Delete the Pipeline ‘Pipeline1’?", "Delete the Pipeline ‘Pipeline1’?");

        WebElement buttonYes = getDriver().findElement(By.xpath("//button[@data-id='ok']"));
        buttonYes.click();

        String text = getDriver().findElement(By.className("content-block__link")).getText();

        Assert.assertTrue(text.contains("Create a job"));
    }
}