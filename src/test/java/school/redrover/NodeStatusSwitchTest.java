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
        WebElement manageJenkins = getDriver().findElement(By.xpath("//a[@href='/manage']"));
        manageJenkins.click();

        WebElement nodesLink = getDriver().findElement(By.xpath("//a[@href='computer']"));
        nodesLink.click();

        List<WebElement> buildExecutors = getDriver().findElements(By.xpath("//td[text()='Idle']"));
        int number = buildExecutors.size();

        Assert.assertEquals(number, 2);
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
