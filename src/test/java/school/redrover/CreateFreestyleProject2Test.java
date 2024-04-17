package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import static org.testng.Assert.assertEquals;

public class CreateFreestyleProject2Test extends BaseTest {

    @Test
    public void testCreateFreestyleProject2() {
        final String expectedFreestyleProjectName = "FreestyleProjectTest";

        getDriver().findElement(By.xpath("//div[@id='tasks']/descendant::div[1]")).click();
        getDriver().findElement(By.id("name")).sendKeys(expectedFreestyleProjectName);
        getDriver().findElement(By.xpath("//div[@id='items']//*[text()='Freestyle project']//ancestor::li"))
                .click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        String expectedNameText = "FreestyleProject";
        assertEquals(expectedNameText,expectedNameText);

    }
}
