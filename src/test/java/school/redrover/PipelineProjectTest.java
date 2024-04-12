package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineProjectTest extends BaseTest {

    @Test
    public void testSameNamePipeline() {
        final String PROJECT_NAME = "Random pipeline";

        createNewJob(PROJECT_NAME);

        getDriver().findElement(By.xpath("//*[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();

        createNewJob(PROJECT_NAME);

        String warningMessage = getDriver().findElement(By.id("itemname-invalid")).getText();

        Assert.assertEquals(warningMessage, "» A job already exists with the name ‘" + PROJECT_NAME + "’");
    }


    @Test
    public void testCreationOfNewPipelineProject() {

        getDriver().findElement(By.linkText("Create a job")).click();
        String newJobUrl= getDriver().getCurrentUrl();
        Assert.assertTrue(newJobUrl.endsWith("/newJob"));

        Assert.assertTrue(getDriver().findElement(By.cssSelector("div#add-item-panel .h3")).isDisplayed());

        getDriver().findElement(By.id("name")).sendKeys("firstPipeline");
        getDriver().findElement(By.xpath("//*[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        newJobUrl= getDriver().getCurrentUrl();

        Assert.assertTrue(newJobUrl.endsWith("job/firstPipeline/configure"));

        getDriver().findElement(By.id("jenkins-home-link")).click();
        Assert.assertTrue(getDriver().findElement(By.id("job_firstPipeline")).isDisplayed());

        WebElement jobInTableName = getDriver().findElement(By.cssSelector("a[href='job/firstPipeline/']"));
        Assert.assertEquals(jobInTableName.getText(),"firstPipeline");

    }

    private void  createNewJob(String projectName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(projectName);
    }
}
