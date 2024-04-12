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

        createJob(Job.PIPELINE,PROJECT_NAME);
        getDriver().findElement(By.name("Submit")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();

        goToJobPageAndEnterJobName(PROJECT_NAME);

        getDriver().findElement(By.xpath("//*[text()='Pipeline']")).click();
        // this line duplicates click on Pipeline, because of the Jenkins bug. Sometimes warning message doesn`t appear. Second click on Pipeline makes it happen.

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

    private void goToJobPageAndEnterJobName(String jobName) {
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(jobName);
    }
    public void createJob(Job job, String jobName ) {
        goToJobPageAndEnterJobName(jobName);
        if(job==Job.PIPELINE) {
            getDriver().findElement(By.xpath("//*[text()='Pipeline']")).click();
        } else if (job==Job.FREESTYLE) {
            getDriver().findElement(By.xpath("//*[text()='Freestyle project']")).click();
        }else if (job==Job.MULTI_CONFIGURATION) {
            getDriver().findElement(By.xpath("//*[text()='Multi-configuration project']")).click();
        }
        getDriver().findElement(By.id("ok-button")).click();
    }

    enum Job{
        FREESTYLE,
        PIPELINE,
        MULTI_CONFIGURATION,
        FOLDER,
        MULTI_BRUNCH_PIPELINE,
        ORGANIZATION_FOLDER

    }
}
