package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils.*;
import school.redrover.runner.TestUtils;

public class PipelineProjectTest extends BaseTest {

    public static final String JOB_XPATH = "//*[text()='%s']";

    @Test
    public void testSameNamePipeline() {

        final String PROJECT_NAME = "Random pipeline";

        TestUtils.createJob(this, Job.PIPELINE, PROJECT_NAME);
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();

        TestUtils.goToJobPageAndEnterJobName(this, PROJECT_NAME);

        getDriver().findElement(By.xpath("//*[text()='Pipeline']")).click();
        // this line duplicates click on Pipeline, because of the Jenkins bug. Sometimes warning message doesn`t appear. Second click on Pipeline makes it happen.

        String warningMessage = getDriver().findElement(By.id("itemname-invalid")).getText();

        Assert.assertEquals(warningMessage, "» A job already exists with the name ‘" + PROJECT_NAME + "’");
    }

    @Test
    public void testCreationOfNewPipelineProject() throws InterruptedException {

        getDriver().findElement(By.linkText("Create a job")).click();
        String newJobUrl = getDriver().getCurrentUrl();
        Assert.assertTrue(newJobUrl.endsWith("/newJob"));
        Thread.sleep(500);
        Assert.assertTrue(getDriver().findElement(By.cssSelector("div#add-item-panel .h3")).isDisplayed());

        getDriver().findElement(By.id("name")).sendKeys("firstPipeline");
        getDriver().findElement(By.xpath("//*[text()='Pipeline']")).click();
        getDriver().findElement(By.id("ok-button")).click();
        newJobUrl = getDriver().getCurrentUrl();

        Assert.assertTrue(newJobUrl.endsWith("job/firstPipeline/configure"));

        getDriver().findElement(By.id("jenkins-home-link")).click();
        Assert.assertTrue(getDriver().findElement(By.id("job_firstPipeline")).isDisplayed());

        WebElement jobInTableName = getDriver().findElement(By.cssSelector("a[href='job/firstPipeline/']"));
        Assert.assertEquals(jobInTableName.getText(), "firstPipeline");
    }

    @Test
    public void testAddDescriptionPreview() {

        final String PIPELINE_NAME = "Pipeline project";
        final String description = "First";

        TestUtils.createJob(this, Job.PIPELINE, PIPELINE_NAME);

        getDriver().findElement(By.cssSelector("#breadcrumbs > li:nth-child(3)")).click();
        getDriver().findElement(By.xpath("//a[@id='description-link']")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
        getDriver().findElement(By.xpath("//a[@class='textarea-show-preview']")).click();

        WebElement previewDescription = getDriver().findElement(By.xpath("//div[@class='textarea-preview']"));

        Assert.assertEquals(previewDescription.getText(),description);
    }
}
