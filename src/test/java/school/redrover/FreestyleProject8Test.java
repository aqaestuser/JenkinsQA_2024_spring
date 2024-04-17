package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;
import school.redrover.runner.TestUtils;

public class FreestyleProject8Test extends BaseTest {

    @Test
    public void testAddDescriptionInFreestyleProjectFromProject() {
        String description = "Testing adding a project description from the project.";

        TestUtils.createItem("Freestyle project", "Freestyle project test 1", getDriver());
        getDriver().findElement(By.xpath("//*[@class=\"model-link\" and text()='Dashboard']")).click();

        getDriver().findElement(By.xpath("//*[@id=\"job_Freestyle project test 1\"]/descendant::a")).click();
        getDriver().findElement(By.xpath("//*[@id=\"description-link\"]")).click();
        getDriver().findElement(By.xpath("//*[@name=\"description\"]")).sendKeys(description);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        String text = getDriver().findElement(By.xpath("//*[@id='description']/div")).getText();
        System.out.println(text);

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='description']/div")).getText(),
                description);
    }

    @Test
    public void testAddDescriptionInFreestyleProjectFromDashboardWithDropdown() {
        String description = "Testing adding a project description from Dashboard.";

        TestUtils.createItem("Freestyle project", "Freestyle project test 2", getDriver());
        TestUtils.returnToDashBoard(this);

        WebElement elementDropdown = getDriver().findElement(By.xpath("//span[text()='Freestyle project test 2']/ancestor::a"));

        TestUtils.openElementDropdown(this, elementDropdown);

        getDriver().findElement(By.xpath("//*[contains(@href, 'configure')]")).click();
        getDriver().findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id='description']/div")).getText(),
                description);
    }

}
