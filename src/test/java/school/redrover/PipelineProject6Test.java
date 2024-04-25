package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineProject6Test extends BaseTest {
    private static final String PIPELINE_NAME = "Pipeline";
    private Actions actions;
    public Actions getActions() {
        if (actions==null){
            actions=new Actions(getDriver());
        }
        return actions;
    }
    public void createNewPipeline(String pipelineName){
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/view/all/newJob']"))).click();
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.id("name"))).sendKeys(pipelineName);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
    }
    public void goHomePage(){
        getWait5().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']"))).click();
    }

    @Test
    public void testFullStageViewDropDownMenu() throws InterruptedException {
        createNewPipeline(PIPELINE_NAME);
        goHomePage();

        WebElement dropDown = getWait60().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[@id='job_" + PIPELINE_NAME + "']//a[@href='job/" + PIPELINE_NAME + "/']")));
        getActions().moveToElement(dropDown).perform();

        WebElement dropDownMenu =  getWait60().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[@href='job/" + PIPELINE_NAME + "/']//button")));

        int attempts = 0;
        while(attempts < 3) {
            try {
                dropDownMenu.click();
                break; // Выход из цикла, если клик успешен
            } catch (ElementClickInterceptedException e) {
                Thread.sleep(1000); // Ожидание перед следующей попыткой
                attempts++;
            }
        }
        getWait60().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@href='/job/" + PIPELINE_NAME + "/workflow-stage']"))).click();

        String expectedText = PIPELINE_NAME + " - Stage View";
        Assert.assertEquals(getWait5().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@id='pipeline-box']/h2"))).getText(),expectedText);
    }
}
