package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class FreestyleProjectTest extends BaseTest {
    @Test
        public void testFreestyleProjectCreate() {
            String newName = "Project8";
            getDriver().findElement(By.xpath("//a[@it='hudson.model.Hudson@72b00a0']")).click();
            getDriver().findElement(By.id("name")).sendKeys(newName);
            getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
            getDriver().findElement(By.id("ok-button")).click();
            getDriver().findElement(By.name("Submit")).click();

            WebElement nameOfProject = getDriver().findElement(
                    By.xpath("//h1[@class='job-index-headline page-headline']"));

            String actualResult = nameOfProject.getText();

            Assert.assertEquals(actualResult,newName);
        }
    }



