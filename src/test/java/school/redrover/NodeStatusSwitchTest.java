package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.util.List;

public class NodeStatusSwitchTest extends BaseTest {

    private void clickOn(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();
    }

    @Test
    public void testNumberOfItems() {

        String text = getDriver().findElement(By.id("executors")).getText();

        if(text.contains("( offline)")) {
            WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
            manageJenkins.click();

            WebElement nodesLink = getDriver().findElement(By.xpath("//a[@href='computer']"));
            nodesLink.click();

            getDriver().findElement(By.cssSelector("#node_ > td:nth-child(9) > div > a > svg")).click();
            WebElement numberOfNodes = getDriver().findElement(By.xpath("//input[@type='number']"));
            numberOfNodes.clear();
            numberOfNodes.sendKeys("2");

            getDriver().findElement(By.name("Submit")).click();

            getDriver().findElement(By.name("Submit")).click();
            List<WebElement> buildExecutors = getDriver().findElements(By.xpath("//td[text()='Idle']"));
            int number = buildExecutors.size();

            Assert.assertEquals(number, 2);

        } else {
            WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
            manageJenkins.click();

            WebElement nodesLink = getDriver().findElement(By.xpath("//a[@href='computer']"));
            nodesLink.click();

            getDriver().findElement(By.cssSelector("#node_ > td:nth-child(9) > div > a > svg")).click();
            WebElement numberOfNodes = getDriver().findElement(By.xpath("//input[@type='number']"));
            numberOfNodes.clear();
            numberOfNodes.sendKeys("2");

            getDriver().findElement(By.name("Submit")).click();

            List<WebElement> buildExecutors = getDriver().findElements(By.xpath("//td[text()='Idle']"));
            int number = buildExecutors.size();

            Assert.assertEquals(number, 2);
        }

    }

    @Test(dependsOnMethods = "testNumberOfItems")
    public void testSwitchToOfflineStatus() {
        clickOn("//a[normalize-space()='Build Executor Status']");
        clickOn("//a[@href='../computer/(built-in)/']");
        clickOn("//div[@class='jenkins-app-bar__controls']");
        clickOn("//button[@name='Submit']");

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//div[@class='message']")).getText(),
                "Disconnected by admin");
    }
}
