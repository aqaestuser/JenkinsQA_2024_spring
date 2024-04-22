package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class SearchBoxTest2 extends BaseTest {
    private static final String PIPELINE_NAME = "Pipeline";

    public void createNewPipeline(String pipelineName){
        getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();
        getDriver().findElement(By.id("name")).sendKeys(pipelineName);
        getDriver().findElement(By.xpath("//span[text()='Pipeline']")).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.id("ok-button"))).click();
        getWait5().until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@name='Submit']"))).click();
    }
    public void goHomePage(){
        getDriver().findElement(By.xpath("//li[@class='jenkins-breadcrumbs__list-item']")).click();
    }
    @Test
    public void testSearchBox(){
        createNewPipeline(PIPELINE_NAME);
        goHomePage();

        WebElement searchBox = getDriver().findElement(By.id("search-box"));
        searchBox.sendKeys(PIPELINE_NAME);
        searchBox.sendKeys(Keys.ENTER);

        Assert.assertEquals(getDriver().findElement(By.xpath("//h1[@class='job-index-headline page-headline']")).getText(), PIPELINE_NAME);
    }
}
